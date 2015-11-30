package de.b4sh.yais.mdl;

import com.mongodb.client.MongoCollection;
import de.b4sh.yais.iface.Storable;
import org.bson.Document;

public class Room implements Storable{

    public static String mongoDBident = "yais.room";

    private int id;
    private String location;

    public Room(int id, String locationData){
        this.id = id;
        this.location = locationData;
    }

    public void store(MongoCollection collection) {
        Document document = new Document("id", this.id)
                                    .append("location", this.location);
        collection.insertOne(document);
    }

    public static Room restore(Document document){
        int id = Integer.parseInt(document.get("id").toString());
        String location = document.get("location").toString();

        return new Room(id,location);
    }

    public int getID(){
        return this.id;
    }

}

