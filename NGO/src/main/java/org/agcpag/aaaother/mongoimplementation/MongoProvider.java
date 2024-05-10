package org.agcpag.aaaother.mongoimplementation;

import org.agcpag.providers.DataProvider;

public class MongoProvider implements DataProvider {
    @Override
    public <T> T find(Class<T> entityClass, long id) {
        return null;
    }
}
