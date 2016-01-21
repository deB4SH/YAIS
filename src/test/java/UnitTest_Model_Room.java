import de.b4sh.yais.mdl.Room;
import org.junit.Assert;
import org.junit.Test;

public class UnitTest_Model_Room {

    @Test
    public void testRoom(){
        int id = 0;
        String val = "Room 301, Haus 21";
        Room testRoom = new Room(id,val);

        Assert.assertEquals(internalTestRoom(testRoom,id,val),true);

    }


    private boolean internalTestRoom(Room tr,int id,String val){
        //check if values are equals
        if(tr.getLocation().equals(val)){
            if(tr.getId() == id){
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
