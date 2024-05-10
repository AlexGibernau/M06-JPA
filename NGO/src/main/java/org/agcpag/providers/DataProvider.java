package org.agcpag.providers;

public interface DataProvider {
    <T> T find(Class<T> entityClass, long id);
}
