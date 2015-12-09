package de.b4sh.yais.net;


import com.mongodb.client.MongoDatabase;

import com.mongodb.util.JSON;
import de.b4sh.yais.YAIS;
import de.b4sh.yais.mdl.Room;
import de.b4sh.yais.misc.LogType;
import de.b4sh.yais.misc.LogWriter;
import org.bson.Document;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

public class MessagePacker {

    public static String createCompleteMessage(String messageID, String reason){
        if(YAIS.DEBUG){
            LogWriter.logToConsole(LogType.debug, "Messagepacker: ERROR MESSAGE");
        }

        JSONObject response = new JSONObject();
        response.put("messageID", messageID);
        response.put("done",reason);

        return response.toString();
    }

    public static String createErrorMessage(String messageID,String reason){
        if(YAIS.DEBUG){
            LogWriter.logToConsole(LogType.debug, "Messagepacker: ERROR MESSAGE");
        }

        JSONObject response = new JSONObject();
        response.put("messageID", messageID);
        response.put("error",reason);

        return response.toString();
    }

    public static String createAllRoomMessage(MongoDatabase db){
        if(YAIS.DEBUG){
            LogWriter.logToConsole(LogType.debug, "Messagepacker creates packages for all rooms");
        }

        JSONObject response = new JSONObject();
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

}
