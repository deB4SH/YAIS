package mdl;

import misc.LogType;
import misc.LogWriter;

import java.util.ArrayList;

public class InstanceHandler {

    ArrayList<Room> roomList;
    ArrayList<Cabinet> cabinetList;
    ArrayList<CabinetRow> cabinetRowList;
    ArrayList<Dossier> dossierList;

    public InstanceHandler(){
        this.roomList = new ArrayList<Room>();
        this.cabinetList = new ArrayList<Cabinet>();
        this.cabinetRowList = new ArrayList<CabinetRow>();
        this.dossierList = new ArrayList<Dossier>();
    }

    public void addRoom(Room r) {
        if(!this.roomList.contains(r)){
            //TODO: check if entity is in mongodb
            //TODO: if yes add to list from mongodb
            //TODO: if not add to list and to mongodb
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
