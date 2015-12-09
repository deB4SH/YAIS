package de.b4sh.yais.net;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import de.b4sh.yais.YAIS;
import de.b4sh.yais.mdl.InstanceHandler;
import de.b4sh.yais.mdl.Room;
import de.b4sh.yais.mdl.User;
import de.b4sh.yais.misc.LogType;
import de.b4sh.yais.misc.LogWriter;
import org.java_websocket.WebSocket;
import org.json.JSONObject;

public class MessageInterpreter {

    private InstanceHandler instanceHandler;
    private MongoDatabase db;

    public MessageInterpreter(InstanceHandler instanceHandler, MongoDatabase db) {
        this.instanceHandler = instanceHandler;
        this.db = db;
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
                    ws.send("something with your username is wrong");
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
                this.instanceHandler.addUser(newUser,ws,message.get("messageID").toString());

            }
            //SOMETHING WENT WRONG HERE
            else{
                LogWriter.logToConsole(LogType.error, "Something went wrong here with this request");
                LogWriter.logToConsole(LogType.debug, message.toString());
            }
        }
        //DATA RELATED
        else if(((String) message.get("messageType")).equalsIgnoreCase(MessageType.DATA.getValue())) {
            //DATA ROOM
            if(((String) message.get("messageSubType")).equalsIgnoreCase(MessageSubType.DATAROOM.getValue())) {
                if(YAIS.DEBUG){
                    LogWriter.logToConsole(LogType.debug, "Datarequest on Room");
                }
                //create new object
                if(message.get("messageActionType").toString().equalsIgnoreCase("new")){
                    BasicDBObject messageContent = (BasicDBObject)JSON.parse(message.get("message").toString());
                    BasicDBObject newObj = (BasicDBObject)JSON.parse(messageContent.get("obj").toString());

                    int id = this.instanceHandler.getNextRoomId();
                    String location = newObj.get("location").toString();
                    Room r = new Room(id,location);

                    this.instanceHandler.addRoom(r,ws,message.get("messageID").toString());
                }
                //get all objects and send via ws
                if(message.get("messageActionType").toString().equalsIgnoreCase("load")){
                    String request = MessagePacker.createAllRoomMessage(this.db);
                    JSONObject response = new JSONObject();
                    response.put("messageID",message.get("messageID"));
                    response.put("response",request);
                    ws.send(response.toString());
                }
                //remove one/list from database
                if(message.get("messageActionType").toString().equalsIgnoreCase("remove")){
                    //TODO: remove code
                    BasicDBObject messageContent = (BasicDBObject)JSON.parse(message.get("message").toString());
                }
            }
            //DATA CABINET
            else if(message.get("messageSubType") == MessageSubType.DATACABINET.getValue()) {
                if(YAIS.DEBUG){
                    LogWriter.logToConsole(LogType.debug, "Datarequest on Cabinet");
                }
                BasicDBObject messageContent = (BasicDBObject)JSON.parse(message.get("message").toString());
                //create new object
                if(messageContent.get("messageActionType").toString().equalsIgnoreCase("new")){
                    //TODO: new code
                }
                //get all objects and send via ws
                if(messageContent.get("messageActionType").toString().equalsIgnoreCase("load")){
                    //TODO: load code
                }
                //remove one/list from database
                if(messageContent.get("messageActionType").toString().equalsIgnoreCase("remove")){
                    //TODO: remove code
                }
            }
            //DATA CABINETROW
            else if(message.get("messageSubType") == MessageSubType.DATACABINETROW.getValue()) {
                if(YAIS.DEBUG){
                    LogWriter.logToConsole(LogType.debug, "Datarequest on CabinetRow");
                }
                BasicDBObject messageContent = (BasicDBObject)JSON.parse(message.get("message").toString());
                //create new object
                if(messageContent.get("messageActionType").toString().equalsIgnoreCase("new")){
                    //TODO: new code
                }
                //get all objects and send via ws
                if(messageContent.get("messageActionType").toString().equalsIgnoreCase("load")){
                    //TODO: load code
                }
                //remove one/list from database
                if(messageContent.get("messageActionType").toString().equalsIgnoreCase("remove")){
                    //TODO: remove code
                }
            }
            //DATA DOSSIER
            else if(message.get("messageSubType") == MessageSubType.DATADOSSIER.getValue()) {
                if(YAIS.DEBUG){
                    LogWriter.logToConsole(LogType.debug, "Datarequest on Dossier");
                }
                BasicDBObject messageContent = (BasicDBObject)JSON.parse(message.get("message").toString());
                //create new object
                if(messageContent.get("messageActionType").toString().equalsIgnoreCase("new")){
                    //TODO: new code
                }
                //get all objects and send via ws
                if(messageContent.get("messageActionType").toString().equalsIgnoreCase("load")){
                    //TODO: load code
                }
                //remove one/list from database
                if(messageContent.get("messageActionType").toString().equalsIgnoreCase("remove")){
                    //TODO: remove code
                }
            }
            else{
                LogWriter.logToConsole(LogType.error, "Something went wrong here with this request");
                LogWriter.logToConsole(LogType.debug, message.toString());
            }
        }
    }
}
