package org.agcpag;

import org.agcpag.providers.DataProvider;
import org.agcpag.providers.DataProviderFactory;
import org.agcpag.providers.MongoProvider;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        DataProvider provider = DataProviderFactory.getDataProvider();

        System.out.println("Successfully created DataProvider: " + provider.getClass().getName());

        System.out.println(provider.find(1L));
    }
}