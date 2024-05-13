package org.agcpag.providers;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.agcpag.annotations.Column;
import org.agcpag.annotations.Entity;
import org.bson.Document;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Properties;

import static com.mongodb.client.model.Filters.*;

/**
 * A data provider implementation for MongoDB.
 */
public class MongoProvider implements DataProvider {

    private MongoDatabase db; // The MongoDB database instance.

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

    @Override
    public void config(Properties properties) {
        MongoClient client = MongoClients.create(properties.getProperty("MONGODB_URI"));
        this.db = client.getDatabase(properties.getProperty("DATABASE_NAME"));
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
