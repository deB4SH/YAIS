import de.b4sh.yais.mdl.Room;

public class ModelRoomTest {

    static int id;
    static String location;
    static Room tR;

    public static void main(String args[]){

    }

    public static void setVal(Room _tr, int _id, String _location){
        tR = _tr;
        id = _id;
        location= _location;
    }

    public static boolean internalTestRoom(){
        //check if values are equals
        if(tR.getLocation().equals(location)){
            if(tR.getId() == id){
                return true;
            }
            else{
                return false;
            }
        }
        else {
            return false;
        }
    }

}
