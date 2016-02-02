package de.b4sh.yais.mdl;

import com.mongodb.client.MongoCollection;
import de.b4sh.yais.iface.Storable;
import org.bson.Document;

/**
 * Basemodel for the cabinet
 */
public class Cabinet implements Storable{

    public static String mongoDBident = "yais.cabinet";

    //reference values
    private int id;
    private char idLetter;
    private int roomID;

    //cabinet values
    private int rowCount;

    public Cabinet(int id, char idLetter, int rowCount, int roomID){
        this.id = id;
        this.idLetter = idLetter;
        this.rowCount = rowCount;
        this.roomID = roomID;
    }

    public void store(MongoCollection collection) {
        Document document = new Document("id", this.id)
                .append("idLetter", this.idLetter)
                .append("roomID", this.roomID)
                .append("rowCount", this.rowCount);

        collection.insertOne(document);
    }

    public static Cabinet restore(Document document){

        int id = Integer.parseInt(document.get("id").toString());
        char idLetter = document.get("idLetter").toString().charAt(0);
        int roomID = Integer.parseInt(document.get("roomID").toString());
        int rowCount = Integer.parseInt(document.get("rowCount").toString());

        return new Cabinet(id,idLetter,rowCount,roomID);
    }

    public int getId() {
        return id;
    }

    public char getIdLetter() {
        return idLetter;
    }

    public int getRoomID() {
        return roomID;
    }

    public int getRowCount() {
        return rowCount;
    }
}
