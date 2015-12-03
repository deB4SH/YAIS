package de.b4sh.yais.net;

import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;
import de.b4sh.yais.mdl.InstanceHandler;
import de.b4sh.yais.misc.LogType;
import de.b4sh.yais.misc.LogWriter;

public class MessageInterpreter {

    private InstanceHandler instanceHandler;

    public MessageInterpreter(InstanceHandler instanceHandler) {
        this.instanceHandler = instanceHandler;
    }

    public void renderIncommingMessage(Object json){
        BasicDBObject message = (BasicDBObject)json;
        //USER/ACCOUNT RELATED
        if(message.get("messageType") == MessageType.USER){
            //USER LOGON
            if(message.get("messageSubType") == MessageSubType.USERLOGON) {
                //TODO: user logon code
            }
            //USER LOGOFF
            else if(message.get("messageSubType") == MessageSubType.USERLOGOFF) {
                //TODO: user logoff code
            }
            else{
                LogWriter.logToConsole(LogType.error, "Something went wrong here with this request");
                LogWriter.logToConsole(LogType.debug, message.toString());
            }
        }
        //DATA RELATED
        else if(message.get("messageType") == MessageType.DATA) {
            //DATA ROOM
            if(message.get("messageSubType") == MessageSubType.DATAROOM) {
                //TODO: data room code
            }
            //DATA CABINET
            else if(message.get("messageSubType") == MessageSubType.DATACABINET) {
                //TODO: data cabinet code
            }
            //DATA CABINETROW
            else if(message.get("messageSubType") == MessageSubType.DATACABINETROW) {
                //TODO: data cabinet code
            }
            //DATA DOSSIER
            else if(message.get("messageSubType") == MessageSubType.DATADOSSIER) {
                    //TODO: data dossier code
            }
            else{
                LogWriter.logToConsole(LogType.error, "Something went wrong here with this request");
                LogWriter.logToConsole(LogType.debug, message.toString());
            }
        }
    }
}
