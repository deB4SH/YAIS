package de.b4sh.yais.net;

import com.mongodb.BasicDBObject;
import com.mongodb.util.JSON;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import de.b4sh.yais.YAIS;
import de.b4sh.yais.mdl.InstanceHandler;
import de.b4sh.yais.mdl.User;
import de.b4sh.yais.misc.LogType;
import de.b4sh.yais.misc.LogWriter;
import org.java_websocket.WebSocket;

public class MessageInterpreter {

    private InstanceHandler instanceHandler;

    public MessageInterpreter(InstanceHandler instanceHandler) {
        this.instanceHandler = instanceHandler;
    }

    public void renderIncommingMessage(Object json, WebSocket ws){
        BasicDBObject message = (BasicDBObject)json;
        String messageType = (String)message.get("messageType");
        String messageSubType = (String)message.get("messageSubType");
        //USER/ACCOUNT RELATED
        if(messageType.equalsIgnoreCase(MessageType.USER.getValue())){
            //USER LOGON
            if(messageSubType.equalsIgnoreCase(MessageSubType.USERLOGON.getValue())) {
                if(YAIS.DEBUG){
                    LogWriter.logToConsole(LogType.debug, "socketreq. on user logon data");
                }
                BasicDBObject messageContent = (BasicDBObject)JSON.parse(message.get("message").toString());
                User u = this.instanceHandler.getUser(messageContent.get("username").toString());
                if(u == null){
                    if(u.getPassword() == messageContent.get("password").toString()){
                        u.setUserLoggedOn(true);
                        //TODO: send some "now you are logged in bla bla"
                    }
                    else{
                        //TODO: send client something with the pw is wrong
                        ws.send("something with your password is wrong");
                    }
                }
                else{
                    //TODO: send the client something with the username is wrong
                    ws.send("something with your username is wrong")
                }
            }
            //USER LOGOFF
            else if(messageSubType.equalsIgnoreCase(MessageSubType.USERLOGOFF.getValue())) {
                if(YAIS.DEBUG){
                    LogWriter.logToConsole(LogType.debug, "user logoff");
                }
                BasicDBObject messageContent = (BasicDBObject)JSON.parse(message.get("message").toString());
                if(this.instanceHandler.getUser(messageContent.get("username").toString()).isUserLoggedOn()){
                    this.instanceHandler.getUser(messageContent.get("username").toString()).setUserLoggedOn(false);
                }
                //TODO: send client some logoff message
            }
            //USER REGISTER
            else if(messageSubType.equalsIgnoreCase(MessageSubType.USERREGISTER.getValue())) {
                if(YAIS.DEBUG){
                    LogWriter.logToConsole(LogType.debug, "user registration");
                }
                BasicDBObject messageContent = (BasicDBObject)JSON.parse(message.get("message").toString());
                //create from messageContent
                User newUser = new User(this.instanceHandler.getNextUserId(),messageContent.get("username").toString(), messageContent.get("password").toString());
                this.instanceHandler.addUser(newUser);
                ws.send("Register complete");
            }
            //SOMETHING WENT WRONG HERE
            else{
                LogWriter.logToConsole(LogType.error, "Something went wrong here with this request");
                LogWriter.logToConsole(LogType.debug, message.toString());
            }
        }
        //DATA RELATED
        else if(message.get("messageType") == MessageType.DATA.getValue()) {
            //DATA ROOM
            if(message.get("messageSubType") == MessageSubType.DATAROOM.getValue()) {
                //TODO: data room code
                LogWriter.logToConsole(LogType.debug,"Request for data of rooms");
            }
            //DATA CABINET
            else if(message.get("messageSubType") == MessageSubType.DATACABINET.getValue()) {
                //TODO: data cabinet code
            }
            //DATA CABINETROW
            else if(message.get("messageSubType") == MessageSubType.DATACABINETROW.getValue()) {
                //TODO: data cabinet code
            }
            //DATA DOSSIER
            else if(message.get("messageSubType") == MessageSubType.DATADOSSIER.getValue()) {
                    //TODO: data dossier code
            }
            else{
                LogWriter.logToConsole(LogType.error, "Something went wrong here with this request");
                LogWriter.logToConsole(LogType.debug, message.toString());
            }
        }
    }
}
