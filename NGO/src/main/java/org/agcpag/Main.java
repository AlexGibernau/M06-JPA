package org.agcpag;

import org.agcpag.pojos.Department;
import org.agcpag.providers.DataProvider;
import org.agcpag.providers.DataProviderFactory;

public class Main {
    public static void main(String[] args) {

        DataProvider provider = DataProviderFactory.getDataProvider();
        System.out.println(provider.find(1L, Department.class));
    }
}