import de.b4sh.yais.mdl.Room;
import junit.framework.Assert;
import org.junit.Test;

public class UnitRoomTest {
    @Test
    public void testRoomModel(){
        ModelRoomTest mrt = new ModelRoomTest();
        mrt.setVal(new Room(0,"Raum 301, Haus 21"),0, "Raum 301, Haus 21");
        Assert.assertEquals(mrt.internalTestRoom(),true);
    }
}



