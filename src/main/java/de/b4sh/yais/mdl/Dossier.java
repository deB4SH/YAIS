package de.b4sh.yais.mdl;

import com.mongodb.client.MongoCollection;
import de.b4sh.yais.iface.Storable;
import org.bson.Document;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Dossier implements Storable{

    public static String mongoDBident = "yais.dossier";

    //references
    private int id;

    //dossier specific data
    private String name;
    private String archiveObject;
    private String lastUse;
    private String createdOn;


    /**
     * Creates the dossier object and assumes the dossier is created this day
     * @param id
     * @param name
     * @param archiveObject
     * @param lastUse
     */
    public Dossier(int id, String name, String archiveObject, String lastUse){
        this.id = id;
        this.name = name;
        this.archiveObject = archiveObject;
        this.lastUse = lastUse;

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date today = new Date();
        this.createdOn = dateFormat.format(today);
    }

    /**
     * Creates the dossier object and allows different days for last use and created (legacy usage - old folder in archive)
     * @param id
     * @param name
     * @param archiveObject
     * @param lastUse
     * @param createdOn
     */
    public Dossier(int id, String name, String archiveObject, String lastUse, String createdOn){
        this.id = id;
        this.name = name;
        this.archiveObject = archiveObject;
        this.lastUse = lastUse;
        this.createdOn = createdOn;
    }

    public void store(MongoCollection collection) {
        Document document = new Document("id", this.id)
                .append("name", this.name)
                .append("archiveObject", this.archiveObject)
                .append("lastUse", this.lastUse)
                .append("createdOn", this.createdOn);

        collection.insertOne(document);
    }

    public static Dossier restore(Document  document){
        int id = Integer.parseInt(document.get("id").toString());
        String name = document.get("name").toString();
        String archiveObject = document.get("archiveObject").toString();
        String lastUse = document.get("lastUse").toString();
        String createdOn = document.get("createdOn").toString();

        return new Dossier(id,name,archiveObject,lastUse,createdOn);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getArchiveObject() {
        return archiveObject;
    }

    public String getLastUse() {
        return lastUse;
    }

    public String getCreatedOn() {
        return createdOn;
    }
}
