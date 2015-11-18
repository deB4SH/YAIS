package de.b4sh.yais.mdl;

import com.mongodb.client.MongoCollection;
import de.b4sh.yais.iface.Storable;
import org.bson.Document;

public class CabinetRow implements Storable {

    public static String mongoDBident = "yais.cabinetrow";

    //reference values
    private int id;
    private char idLetter;
    private int cabinetID;

    //cabinetrow specific values
    private int placeInRow;


    public CabinetRow(int cabinetID, int placeInRow){

        //TODO: create id;

        //TODO: create idLetter

        //TODO: create base object

        this.cabinetID = cabinetID;
        this.placeInRow = placeInRow;
    }

    public void store(MongoCollection collection) {
        Document document = new Document("id", this.id)
                .append("idLetter", this.idLetter)
                .append("cabinetID", this.cabinetID)
                .append("placeInRow", this.placeInRow);

        collection.insertOne(document);
    }

}
