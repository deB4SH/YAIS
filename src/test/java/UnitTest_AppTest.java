import junit.framework.Assert;
import org.junit.Test;

public class UnitTest_AppTest {

    @Test
    public void testAppTestHelloWorld(){
        Assert.assertEquals(AppTest.getHelloWorld(), "Hello World");
    }



}
