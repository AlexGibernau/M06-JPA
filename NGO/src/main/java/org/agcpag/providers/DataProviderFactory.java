package org.agcpag.providers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * A factory class responsible for creating instances of DataProvider based on configuration.
 */
public class DataProviderFactory {

    /**
     * Creates and returns a DataProvider instance based on configuration.
     *
     * @return a DataProvider instance
     * @throws IOException if an I/O error occurs
     */
    public static DataProvider getDataProvider() throws IOException {
        Properties props = new Properties();
        try (InputStream in = DataProviderFactory.class.getClassLoader().getResourceAsStream("config/ngo.properties")) {
            props.load(in);
            String provider = props.getProperty("PROVIDER");

            if (provider.equals("org.agcpag.providers.MongoProvider")) {
                String connectionString = props.getProperty("MONGODB_URI");
                String dbName = props.getProperty("DATABASE_NAME");

                return new MongoProvider(connectionString, dbName);
            }

            throw new IllegalArgumentException("Unsupported provider: " + provider);
        }
    }
}
