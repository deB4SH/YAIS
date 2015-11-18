package de.b4sh.yais.mdl;

import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.MongoDatabase;
import de.b4sh.yais.misc.LogType;
import de.b4sh.yais.misc.LogWriter;
import org.bson.Document;

import java.util.ArrayList;

public class InstanceHandler {

    private MongoDatabase db;

    private ArrayList<Room> roomList;
    private ArrayList<Cabinet> cabinetList;
    private ArrayList<CabinetRow> cabinetRowList;
    private ArrayList<Dossier> dossierList;

    public InstanceHandler(MongoDatabase db){
        this.roomList = new ArrayList<Room>();
        this.cabinetList = new ArrayList<Cabinet>();
        this.cabinetRowList = new ArrayList<CabinetRow>();
        this.dossierList = new ArrayList<Dossier>();
        this.db = db;
    }

    public void addRoom(Room r) {
        if(!this.roomList.contains(r)){
            //check if entity is in mongodb
            Document searchDoc = null;
            searchDoc = db.getCollection(Room.mongoDBident).find(eq("id",r.getID())).first();
            //TODO: if yes add to list from mongodb
            if(searchDoc != null){
                //check if difference between objects

            }
            //TODO: if not add to list and to mongodb
            else{

            }

            this.roomList.add(r);
        }
        else{
            LogWriter.logToConsole(LogType.error,"Room already exists in list");
        }
    }

    public void addCabinet(Cabinet c){
        if(!this.cabinetList.contains(c)){
            //TODO: check if entity is in mongodb
            //TODO: if yes add to list from mongodb
            //TODO: if not add to list and to mongodb
            this.cabinetList.add(c);
        }
        else{
            LogWriter.logToConsole(LogType.error,"Cabinet already exists in list");
        }
    }

    public void addCabinetRow(CabinetRow cr){
        if(!this.cabinetRowList.contains(cr)){
            //TODO: check if entity is in mongodb
            //TODO: if yes add to list from mongodb
            //TODO: if not add to list and to mongodb
            this.cabinetRowList.add(cr);
        }
        else{
            LogWriter.logToConsole(LogType.error,"CabinetRow already exists in list");
        }
    }

    public void addDossier(Dossier d){
        if(!this.dossierList.contains(d)){
            //TODO: check if entity is in mongodb
            //TODO: if yes add to list from mongodb
            //TODO: if not add to list and to mongodb
            this.dossierList.add(d);
        }
        else{
            LogWriter.logToConsole(LogType.error,"Dossier already exists in list");
        }
    }


}
