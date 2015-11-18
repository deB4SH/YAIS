package de.b4sh.yais;

import de.b4sh.yais.db.MongoAPI;
import de.b4sh.yais.mdl.InstanceHandler;
import de.b4sh.yais.misc.LogWriter;
import de.b4sh.yais.net.WebAPI;

import java.net.UnknownHostException;

public class YAIS {

    private static WebAPI webAPI;
    private static MongoAPI mongoAPI;
    private static LogWriter logWriter;
    private static InstanceHandler instanceHandler;

    public static void main(String args[]){
        try{
            logWriter = new LogWriter();

            webAPI = new WebAPI(60000);
            webAPI.start();
            System.out.println("ChatServer started on port: " + webAPI.getPort());

            mongoAPI = new MongoAPI("127.0.0.1",27017,"yaisDB");

            instanceHandler = new InstanceHandler(mongoAPI.getDB());
        }
        catch (UnknownHostException uhe){
            uhe.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}
