package de.b4sh.yais.mdl;

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

    public InstanceHandler(MongoDatabase db) {
        this.db = db;

        this.roomList = new ArrayList<Room>();
        for (Document current : db.getCollection(Room.mongoDBident).find()) {
            this.roomList.add(Room.restore(current));
        }
        LogWriter.logToConsole(LogType.debug, "Roomlist loaded from MongoDB");

        this.cabinetList = new ArrayList<Cabinet>();
        for (Document current : db.getCollection(Cabinet.mongoDBident).find()) {
            this.cabinetList.add(Cabinet.restore(current));
        }
        LogWriter.logToConsole(LogType.debug, "Cabinetlist loaded from MongoDB");

        this.cabinetRowList = new ArrayList<CabinetRow>();
        for (Document current : db.getCollection(CabinetRow.mongoDBident).find()) {
            this.cabinetRowList.add(CabinetRow.restore(current));
        }
        LogWriter.logToConsole(LogType.debug, "CabinetRowlist loaded from MongoDB");

        this.dossierList = new ArrayList<Dossier>();
        for (Document current : db.getCollection(Dossier.mongoDBident).find()) {
            this.dossierList.add(Dossier.restore(current));
        }
        LogWriter.logToConsole(LogType.debug, "Dossierlist loaded from MongoDB");
    }

    public void addRoom(Room r) {
        boolean inList = false;
        for(Room e: this.roomList){
            if(r.getLocation() == e.getLocation())
            {
                inList = true;
            }
        }
        //add if not in the instanceList , add it to and store all values in mongodb
        if(!inList){
            this.roomList.add(r);
            r.store(this.db.getCollection(Room.mongoDBident));
        }
        else{
            LogWriter.logToConsole(LogType.error,"Room already exists in list");
        }
    }

    public void addCabinet(Cabinet c){
        boolean inList = false;
        for(Cabinet e: this.cabinetList){
            if(c.getIdLetter() == e.getIdLetter() && c.getRoomID() == e.getRoomID() && c.getRowCount() == e.getRowCount()){
                inList = true;
            }
        }
        //add if not in the instanceList , add it to and store all values in mongodb
        if(!inList){
            this.cabinetList.add(c);
            c.store(this.db.getCollection(Cabinet.mongoDBident));
        }
        else{
            LogWriter.logToConsole(LogType.error,"Cabinet already exists in list");
        }
    }

    public void addCabinetRow(CabinetRow cr){
        boolean inList = false;
        for(CabinetRow  e: this.cabinetRowList){
            if(e.getIdLetter() == cr.getIdLetter() && e.getCabinetID() == cr.getCabinetID() && e.getPlaceInRow() == e.getPlaceInRow()){
                inList = true;
            }
        }
        //add if not in the instanceList , add it to and store all values in mongodb
        if(!inList){
            this.cabinetRowList.add(cr);
            cr.store(this.db.getCollection(CabinetRow.mongoDBident));
        }
        else{
            LogWriter.logToConsole(LogType.error,"CabinetRow already exists in list");
        }
    }

    public void addDossier(Dossier d){
        boolean inList = false;
        for(Dossier e: this.dossierList){
            if(e.getCreatedOn() == d.getCreatedOn() && e.getArchiveObject() == d.getArchiveObject() && e.getLastUse() == d.getLastUse() && e.getName() == d.getName()){
                inList = true;
            }
        }
        //add if not in the instanceList , add it to and store all values in mongodb
        if(!inList){
            this.dossierList.add(d);
            d.store(this.db.getCollection(Dossier.mongoDBident));
        }
        else{
            LogWriter.logToConsole(LogType.error,"Dossier already exists in list");
        }
    }

}
