package org.agcpag.providers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataProviderFactory {
    public static DataProvider getDataProvider() throws IOException {
        Properties props = new Properties();
        try (InputStream in = DataProviderFactory.class.getClassLoader().getResourceAsStream("config/ngo.properties")) {
            props.load(in);
            String provider = props.getProperty("PROVIDER");

            if (provider.equals("org.agcpag.providers.MongoProvider")) {
                String connectionString = props.getProperty("MONGODB_URI");
                String dbName = props.getProperty("DATABASE_NAME");
                String collectionName = props.getProperty("COLLECTION_NAME");

                return new MongoProvider(connectionString, dbName, collectionName);
            }

            throw new IllegalArgumentException("Unsupported provider: " + provider);
        }
    }
}
