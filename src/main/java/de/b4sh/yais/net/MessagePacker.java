package de.b4sh.yais.net;


import com.mongodb.client.MongoDatabase;

import com.mongodb.util.JSON;
import de.b4sh.yais.YAIS;
import de.b4sh.yais.mdl.Cabinet;
import de.b4sh.yais.mdl.CabinetRow;
import de.b4sh.yais.mdl.Dossier;
import de.b4sh.yais.mdl.Room;
import de.b4sh.yais.misc.LogType;
import de.b4sh.yais.misc.LogWriter;
import org.bson.Document;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

public class MessagePacker {

    /**
     * Generic complete Message to Client
     * @param messageID
     * @param reason
     * @return
     */
    public static String createCompleteMessage(String messageID, String reason){
        if(YAIS.DEBUG){
            LogWriter.logToConsole(LogType.debug, "Messagepacker: COMPLETE MESSAGE");
        }

        JSONObject response = new JSONObject();
        response.put("messageID", messageID);
        response.put("done",reason);

        return response.toString();
    }

    /**
     * Generic error Message to Client
     * @param messageID
     * @param reason
     * @return
     */
    public static String createErrorMessage(String messageID,String reason){
        if(YAIS.DEBUG){
            LogWriter.logToConsole(LogType.debug, "Messagepacker: ERROR MESSAGE");
        }

        JSONObject response = new JSONObject();
        response.put("messageID", messageID);
        response.put("error",reason);

        return response.toString();
    }

    public static String createLoginMessage(String messageID, String reason, String sessionID){
        if(YAIS.DEBUG){
            LogWriter.logToConsole(LogType.debug, "Messagepacker: Login Message");
        }

        JSONObject response = new JSONObject();
        response.put("messageID", messageID);
        response.put("done",reason);
        response.put("sessionID", sessionID);

        return response.toString();
    }

    public static String createLogoffMessage(String messageID, String reason){
        if(YAIS.DEBUG){
            LogWriter.logToConsole(LogType.debug, "Messagepacker: Logoff Message");
        }

        JSONObject response = new JSONObject();
        response.put("messageID", messageID);
        response.put("done",reason);

        return response.toString();
    }


    /**
     * Creates JSON object for transfer via ws
     * @param db
     * @return
     */
    public static String createAllRoomMessage(MongoDatabase db){
        if(YAIS.DEBUG){
            LogWriter.logToConsole(LogType.debug, "Messagepacker creates packages for all rooms");
        }

        JSONObject response = new JSONObject();
        response.put("classType","room");
        int docID = 0;
        for (Document current : db.getCollection(Room.mongoDBident).find()){
            JSONObject cR = new JSONObject();
            cR.put("id",current.get("id"));
            cR.put("location", current.get("location"));
            response.put(""+docID,cR.toString());
            docID++;
        }

        return response.toString();
    }

    /**
     * Creates JSON object for transfer via ws
     * @param db
     * @return
     */
    public static String createAllCabinetMessage(MongoDatabase db) {
        if(YAIS.DEBUG){
            LogWriter.logToConsole(LogType.debug, "Messagepacker creates packages for all cabinet");
        }

        JSONObject response = new JSONObject();
        response.put("classType","cabinet");
        int docID = 0;
        for (Document current : db.getCollection(Cabinet.mongoDBident).find()){
            JSONObject cR = new JSONObject();
            cR.put("id",current.get("id"));
            cR.put("idLetter", current.get("idLetter"));
            cR.put("roomID",current.get("roomID"));
            cR.put("rowCount", current.get("rowCount"));
            response.put(""+docID,cR.toString());
            docID++;
        }

        return response.toString();
    }

    /**
     * Creates JSON object for transfer via ws
     * @param db
     * @return
     */
    public static String createAllCabinetRowMessage(MongoDatabase db) {
        if(YAIS.DEBUG){
            LogWriter.logToConsole(LogType.debug, "Messagepacker creates packages for all cabinet");
        }

        JSONObject response = new JSONObject();
        response.put("classType","cabinetrow");
        int docID = 0;
        for (Document current : db.getCollection(CabinetRow.mongoDBident).find()){
            JSONObject cR = new JSONObject();
            cR.put("id",current.get("id"));
            cR.put("idLetter", current.get("idLetter"));
            cR.put("cabinetID",current.get("cabinetID"));
            cR.put("rowCount", current.get("placeInRow"));
            response.put(""+docID,cR.toString());
            docID++;
        }

        return response.toString();
    }

    /**
     * Creates JSON object for transfer via ws
     * @param db
     * @return
     */
    public static String createAllDossierMessage(MongoDatabase db) {
        if(YAIS.DEBUG){
            LogWriter.logToConsole(LogType.debug, "Messagepacker creates packages for all dossier");
        }

        JSONObject response = new JSONObject();
        response.put("classType","dossier");
        int docID = 0;
        for (Document current : db.getCollection(Dossier.mongoDBident).find()){
            JSONObject cR = new JSONObject();
            cR.put("id",current.get("id"));
            cR.put("name", current.get("name"));
            cR.put("archive",current.get("archiveObject"));
            cR.put("created", current.get("createdOn"));
            cR.put("cabinetrowid", current.get("cabinetRowID"));
            response.put(""+docID,cR.toString());
            docID++;
        }

        return response.toString();
    }
}
