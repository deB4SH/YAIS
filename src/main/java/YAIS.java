import db.MongoAPI;
import net.WebAPI;

import java.net.UnknownHostException;

public class YAIS {

    private static WebAPI webAPI;
    private static MongoAPI mongoAPI;

    public static void main(String args[]){
        try{
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
