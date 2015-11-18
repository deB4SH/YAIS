package de.b4sh.yais.iface;

import com.mongodb.client.MongoCollection;

public interface Storable {

    public void store(MongoCollection collection);


}
