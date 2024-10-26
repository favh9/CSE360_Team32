
import org.junit.Test;
import static org.junit.Assert.*;

public class TestMain {

    @Test
    public void testSum() {
        Main random = new Main();
        assertEquals(2, random.sum(1,1));
    }

    // some assert type methods that will be useful
    // assertTrue
    // assertFalse
    // assertEquals
    // assertArrayEquals

}