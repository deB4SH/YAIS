package de.b4sh.yais.mdl;

import com.mongodb.client.MongoCollection;
import de.b4sh.yais.iface.Storable;
import org.bson.Document;

public class Room implements Storable{

    public static String mongoDBident = "yais.room";

    private int id;
    private String location;

    public Room(String locationData){
        //TODO: generate ID

        //TODO: create base objects
    }

    public void store(MongoCollection collection) {
        Document document = new Document("id", this.id)
                                    .append("location", location);

        collection.insertOne(document);
    }

    public int getID(){
        return this.id;
    }
}

