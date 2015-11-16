package mdl;

import com.mongodb.client.MongoCollection;
import iface.Storable;
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

    public Cabinet(int rowCount, int roomID){
        //TODO: generate id

        //TODO: generate idLetter

        //TODO: create base object
    }

    public void store(MongoCollection collection) {
        Document document = new Document("id", this.id)
                .append("idLetter", this.idLetter)
                .append("roomID", this.roomID)
                .append("rowCount", this.rowCount);

        collection.insertOne(document);
    }

}
