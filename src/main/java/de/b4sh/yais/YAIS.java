package de.b4sh.yais;

import de.b4sh.yais.db.MongoAPI;
import de.b4sh.yais.mdl.InstanceHandler;
import de.b4sh.yais.misc.LogType;
import de.b4sh.yais.misc.LogWriter;
import de.b4sh.yais.net.MessageInterpreter;
import de.b4sh.yais.net.WebAPI;

import java.net.UnknownHostException;

public class YAIS {

    public static boolean DEBUG = true;

    private static MessageInterpreter messageInterpreter;
    private static WebAPI webAPI;
    private static MongoAPI mongoAPI;
    private static LogWriter logWriter;
    private static InstanceHandler instanceHandler;

    public static void main(String args[]){
        try{
            //Loggin purposes
            logWriter = new LogWriter();
            //Data backend init
            mongoAPI = new MongoAPI("127.0.0.1",27017,"yaisDB");
            instanceHandler = new InstanceHandler(mongoAPI.getDB());
            //Networking init
            messageInterpreter = new MessageInterpreter(instanceHandler);
            webAPI = new WebAPI(60000, messageInterpreter);
            webAPI.start();
            LogWriter.logToConsole(LogType.system,"Websocket started on port: " + webAPI.getPort());
        }
        catch (UnknownHostException uhe){
            uhe.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}
