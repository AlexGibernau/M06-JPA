package org.agcpag.providers;

/**
 * A generic interface for data providers.
 */
public interface DataProvider {

    /**
     * Finds and returns an object of the specified type by its ID.
     *
     * @param id   the ID of the object to find
     * @param type the class of the object to find
     * @param <T>  the type of the object
     * @return the object found
     */
    <T> T find(long id, Class<T> type);
}
