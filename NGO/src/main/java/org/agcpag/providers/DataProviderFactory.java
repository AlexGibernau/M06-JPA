package org.agcpag.providers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * A factory class responsible for creating instances of DataProvider based on configuration.
 */
public class DataProviderFactory {

    public static final String propertiesFileName = "config/ngo.properties";

    /**
     * Creates and returns a DataProvider instance based on configuration.
     *
     * @return a DataProvider instance
     */
    public static DataProvider getDataProvider() {
        Properties props = new Properties();
        try (InputStream in = DataProviderFactory.class.getClassLoader().getResourceAsStream(propertiesFileName)) {
            props.load(in);
            String providerName = props.getProperty("PROVIDER");

            Class<?> providerClass = Class.forName(providerName);
            DataProvider provider = (DataProvider) providerClass.getConstructor().newInstance();
            provider.config(props);
            return provider;

        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Provider not found.", e);
        } catch (IOException e) {
            throw new RuntimeException("File \"" + propertiesFileName + "\" not found", e);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Class provided is not a DataProvider instance.", e);
        }
    }
}
