import org.junit.Assert;
import org.junit.Test;

public class TestMain {

    @Test
    public void testSum() {
        Main random = new Main();
        Assert.assertEquals(2, random.sum(1,1));
    }

    // some assert type methods that will be useful
    // assertTrue
    // assertFalse
    // assertEquals
    // assertArrayEquals
    // assertNull

}