package org.agcpag.providers;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.agcpag.annotations.Column;
import org.agcpag.annotations.Entity;
import org.bson.Document;

import java.lang.reflect.Field;

import static com.mongodb.client.model.Filters.*;

/**
 * A data provider implementation for MongoDB.
 */
public class MongoProvider implements DataProvider {

    private MongoDatabase db; // The MongoDB database instance.

    /**
     * Constructs a new MongoProvider instance with the given host and database names.
     *
     * @param host the MongoDB server host
     * @param bbdd the name of the MongoDB database
     */
    public MongoProvider(String host, String bbdd) {
        System.out.println("Creating MongoProvider");
        MongoClient client = MongoClients.create(host);
        this.db = client.getDatabase(bbdd);
    }

    /**
     * Gets the MongoDB database instance associated with this provider.
     *
     * @return the MongoDB database instance
     */
    public MongoDatabase getDb() {
        return db;
    }

    /**
     * Sets the MongoDB database instance associated with this provider.
     *
     * @param db the MongoDB database instance
     */
    public void setDb(MongoDatabase db) {
        this.db = db;
    }

    /**
     * Finds and returns an object of the specified type by its ID.
     *
     * @param id   the ID of the object to find
     * @param type the class of the object to find
     * @param <T>  the type of the object
     * @return the object found, or null if not found
     */
    @Override
    public <T> T find(long id, Class<T> type) {
        MongoCollection collection = db.getCollection(type.getAnnotation(Entity.class).name());
        if (collection == null) {
            return null;
        }

        Document doc = (Document) collection.find(eq("_id", id)).first();
        if (doc == null) {
            return null;
        }
        try {
            T obj = type.getConstructor().newInstance();
            for (Field field : type.getDeclaredFields()) {
                if (field.isAnnotationPresent(Column.class)) {
                    field.setAccessible(true);
                    field.set(obj, doc.get(field.getAnnotation(Column.class).name()));
                }
            }
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to map document to type: " + type.getName(), e);
        }
    }

    /**
     * Returns a string representation of this MongoProvider.
     *
     * @return a string representation of this MongoProvider
     */
    @Override
    public String toString() {
        return "MongoProvider{" +
                "db=" + db +
                '}';
    }
}
