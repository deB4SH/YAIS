import de.b4sh.yais.mdl.Room;
import org.junit.Assert;
import org.junit.Test;

public class UnitTest_Model_Room {

    @Test
    public void testRoom(){
        ModelRoomTest mrt = new ModelRoomTest();
        mrt.setVal(new Room(0,"Raum 301, Haus 21"),0, "Raum 301, Haus 21");
        Assert.assertEquals(mrt.internalTestRoom(),true);
    }




}
