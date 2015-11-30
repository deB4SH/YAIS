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


    public CabinetRow(int id, char idLetter, int cabinetID, int placeInRow){

        this.id = id;
        this.idLetter = idLetter;
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

    public static CabinetRow restore(Document document){
        int id = Integer.parseInt(document.get("id").toString());
        char idLetter = document.get("idLetter").toString().charAt(0);
        int cabinetID = Integer.parseInt(document.get("cabinetID").toString());
        int placeInRow = Integer.parseInt(document.get("placeInRow").toString());

        return new CabinetRow(id,idLetter,cabinetID,placeInRow);
    }

}
