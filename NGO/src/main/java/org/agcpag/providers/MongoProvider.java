package org.agcpag.providers;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.*;

public class MongoProvider implements DataProvider {
    private MongoDatabase db;
    private MongoCollection collection;

    public MongoDatabase getDb() {
        return db;
    }

    public void setDb(MongoDatabase db) {
        this.db = db;
    }

    public MongoProvider(String host, String bbdd, String collection) {
        System.out.println("Creating MongoProvider");

        MongoClient client = MongoClients.create(host);
        this.db = client.getDatabase(bbdd);
        this.collection = db.getCollection(collection);
    }

    public MongoCollection getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = db.getCollection(collection);
    }

    @Override
    public Object find(long id) {
        return collection.find(eq("_id", id)).first();
    }

    @Override
    public String toString() {
        return "MongoProvider{" +
                "db=" + db +
                ", collection=" + collection +
                '}';
    }

    public static void main(String[] args) {
        MongoProvider mongoProvider = new MongoProvider(
                "mongodb+srv://ngo:ngo@cluster0.l5x1yxh.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0",
                "M06",
                "departments"
        );

        mongoProvider.collection.find().forEach(System.out::println);
    }
}
