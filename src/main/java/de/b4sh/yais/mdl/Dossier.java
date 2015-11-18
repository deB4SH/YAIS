package de.b4sh.yais.mdl;

import com.mongodb.client.MongoCollection;
import de.b4sh.yais.iface.Storable;
import org.bson.Document;

import java.util.Date;

public class Dossier implements Storable{

    public static String mongoDBident = "yais.dossier";

    //references
    private int id;

    //dossier specific data
    private String name;
    private String archiveObject;
    private Date lastUse;
    private Date createdOn;


    public Dossier(String name, String archiveObject, Date lastUse){

    }


    public void store(MongoCollection collection) {
        Document document = new Document("id", this.id)
                .append("name", this.name)
                .append("archiveObject", this.archiveObject)
                .append("lastUse", this.lastUse)
                .append("createdOn", this.createdOn);

        collection.insertOne(document);
    }
}
