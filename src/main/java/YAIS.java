import net.WebAPI;

import java.net.UnknownHostException;

public class YAIS {

    private static WebAPI webAPI;

    public static void main(String args[]){
        try{
            webAPI = new WebAPI(60000);
            webAPI.start();
            System.out.println( "ChatServer started on port: " + webAPI.getPort() );
        }
        catch (UnknownHostException uhe){
            uhe.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}
