import db.MongoAPI;
import misc.LogWriter;
import net.WebAPI;

import java.net.UnknownHostException;

public class YAIS {

    private static WebAPI webAPI;
    private static MongoAPI mongoAPI;
    private static LogWriter logWriter;

    public static void main(String args[]){
        try{
            logWriter = new LogWriter();

            webAPI = new WebAPI(60000);
            webAPI.start();
            System.out.println("ChatServer started on port: " + webAPI.getPort());

            mongoAPI = new MongoAPI("127.0.0.1",27017,"yaisDB");
        }
        catch (UnknownHostException uhe){
            uhe.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}
