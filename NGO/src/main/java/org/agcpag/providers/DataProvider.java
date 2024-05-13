package org.agcpag.providers;

import org.bson.Document;

public interface DataProvider {
    Object find(long id);
}
