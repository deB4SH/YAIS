package de.b4sh.yais.net;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import de.b4sh.yais.YAIS;
import de.b4sh.yais.mdl.*;
import de.b4sh.yais.misc.LogType;
import de.b4sh.yais.misc.LogWriter;
import jdk.nashorn.internal.parser.JSONParser;
import org.bson.BSONObject;
import org.java_websocket.WebSocket;
import org.json.JSONObject;

import java.util.UUID;

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
                    LogWriter.logToConsole(LogType.debug, "Client-Request on User Login");
                    System.out.println(message);
                }
                //get user object
                BasicDBObject messageContent = (BasicDBObject)JSON.parse(message.get("message").toString());
                String userName = messageContent.get("username").toString();
                String pwHash = messageContent.get("password").toString();
                User tmpUser = this.instanceHandler.getUser(userName);

                //username and password clear
                if(tmpUser != null && tmpUser.getPassword().equals(pwHash)){
                    if(YAIS.DEBUG){
                        LogWriter.logToConsole(LogType.debug, "User with name: " + userName + " successfully logged in");
                    }
                    this.instanceHandler.getUser(userName).setSessionid(UUID.randomUUID());
                    ws.send(MessagePacker.createLoginMessage(message.get("messageID").toString(),"User is now logged in",this.instanceHandler.getUser(userName).getSessionid().toString()));
                }
                //user found but password wrong
                else if(tmpUser != null && tmpUser.getPassword().equals(pwHash) == false){
                    if(YAIS.DEBUG){
                        LogWriter.logToConsole(LogType.debug, "User with name: " + userName + " found but password is wrong");
                    }
                    ws.send(MessagePacker.createErrorMessage(message.get("messageID").toString(),"The password for User: " + userName + " is wrong."));
                }
                //user not found
                else{
                    if(YAIS.DEBUG){
                        LogWriter.logToConsole(LogType.debug, "User with name: " + userName + " not found");
                    }
                    ws.send(MessagePacker.createErrorMessage(message.get("messageID").toString(),"The username: " + userName + " is not available in the Database."));
                }
            }
            //USER LOGOFF
            else if(messageSubType.equalsIgnoreCase(MessageSubType.USERLOGOFF.getValue())) {
                if(YAIS.DEBUG){
                    LogWriter.logToConsole(LogType.debug, "Client-Request User Logoff");
                }
                BasicDBObject messageContent = (BasicDBObject)JSON.parse(message.get("message").toString());
                if(this.instanceHandler.getUser(messageContent.get("username").toString()).isUserLoggedOn()){
                    this.instanceHandler.getUser(messageContent.get("username").toString()).setUserLoggedOn(false);
                }
                //TODO: send client some logoff message
                ws.send(MessagePacker.createLogoffMessage(message.get("messageID").toString(),"User: " + messageContent.get("username") + " is now logged off"));
            }
            //USER REGISTER
            else if(messageSubType.equalsIgnoreCase(MessageSubType.USERREGISTER.getValue())) {
                if(YAIS.DEBUG){
                    LogWriter.logToConsole(LogType.debug, "user registration");
                }

                BasicDBObject messageContent = (BasicDBObject)JSON.parse(message.get("message").toString());
                String userName = messageContent.get("username").toString();
                String pwHash = messageContent.get("password").toString();
                User newUser = new User(this.instanceHandler.getNextUserId(),userName, pwHash);
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

                    int id = this.instanceHandler.getNextRoomId();
                    String location = messageContent.get("location").toString();
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
                    BasicDBObject messageContent = (BasicDBObject)JSON.parse(message.get("message").toString());
                    int roomID = Integer.parseInt(messageContent.get("id").toString());
                    this.instanceHandler.removeRoom(roomID,ws,(String)message.get("messageID"));

                }
            }
            //DATA CABINET
            else if(((String) message.get("messageSubType")).equalsIgnoreCase(MessageSubType.DATACABINET.getValue())) {
                if(YAIS.DEBUG){
                    LogWriter.logToConsole(LogType.debug, "Datarequest on Cabinet");
                }
                //create new object
                if(message.get("messageActionType").toString().equalsIgnoreCase("new")){
                    BasicDBObject messageContent = (BasicDBObject)JSON.parse(message.get("message").toString());

                    int id = this.instanceHandler.getNextCabinetId();
                    char idLetter = messageContent.get("cabinetLetter").toString().charAt(0);
                    int roomid = Integer.parseInt(messageContent.get("cabinetRoomID").toString());
                    int rowCount = Integer.parseInt(messageContent.get("cabinetRowCount").toString());

                    Cabinet newCabinet = new Cabinet(id,idLetter,rowCount,roomid);
                    this.instanceHandler.addCabinet(newCabinet,ws,message.get("messageID").toString());
                }
                //get all objects and send via ws
                if(message.get("messageActionType").toString().equalsIgnoreCase("load")){
                    String request = MessagePacker.createAllCabinetMessage(this.db);
                    JSONObject response = new JSONObject();
                    response.put("messageID", message.get("messageID"));
                    response.put("response", request);
                    ws.send(response.toString());
                }
                //remove one/list from database
                if(message.get("messageActionType").toString().equalsIgnoreCase("remove")){
                    BasicDBObject messageContent = (BasicDBObject)JSON.parse(message.get("message").toString());
                    int cabinetID = Integer.parseInt(messageContent.get("id").toString());
                    this.instanceHandler.removeCabinet(cabinetID,ws,(String)message.get("messageID"));
                }
            }
            //DATA CABINETROW
            else if(((String) message.get("messageSubType")).equalsIgnoreCase(MessageSubType.DATACABINETROW.getValue())) {
                if(YAIS.DEBUG){
                    LogWriter.logToConsole(LogType.debug, "Datarequest on CabinetRow");
                }

                //create new object
                if(message.get("messageActionType").toString().equalsIgnoreCase("new")){
                    BasicDBObject messageContent = (BasicDBObject)JSON.parse(message.get("message").toString());

                    int id = this.instanceHandler.getNextCabinetRowId();
                    char idLetter = messageContent.get("cabinetrowLetter").toString().charAt(0);
                    int roomid = Integer.parseInt(messageContent.get("cabinetrowCabinetID").toString());
                    int rowCount = Integer.parseInt(messageContent.get("cabinetrowRowCount").toString());

                    CabinetRow newCabinet = new CabinetRow(id,idLetter,rowCount,roomid);
                    this.instanceHandler.addCabinetRow(newCabinet,ws,message.get("messageID").toString());
                }
                //get all objects and send via ws
                if(message.get("messageActionType").toString().equalsIgnoreCase("load")){
                    String request = MessagePacker.createAllCabinetRowMessage(this.db);
                    JSONObject response = new JSONObject();
                    response.put("messageID", message.get("messageID"));
                    response.put("response", request);
                    ws.send(response.toString());
                }
                //remove one/list from database
                if(message.get("messageActionType").toString().equalsIgnoreCase("remove")){
                    BasicDBObject messageContent = (BasicDBObject)JSON.parse(message.get("message").toString());
                    int cabinetrowID = Integer.parseInt(messageContent.get("id").toString());
                    this.instanceHandler.removeCabinetRow(cabinetrowID,ws,(String)message.get("messageID"));
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
