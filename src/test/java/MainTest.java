import org.junit.Test;
import test.framework.Main;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    @Test
    public void testString1() {
        String str = "12-xk-vv999";

        String actualOutput = Main.getMatchingString(str);

        assertEquals("12 vv", actualOutput);
    }

    @Test
    public void testString2() {
        String str = "12-xk-vvv278mm";

        String actualOutput = Main.getMatchingString(str);

        assertEquals("12 vvv", actualOutput);
    }

    @Test
    public void testString3() {
        String str = "k2k-xk-23zz";

        String actualOutput = Main.getMatchingString(str);

        assertEquals("k2k", actualOutput);
    }

    @Test
    public void testString4() {
        String str = "bb-xk-";

        String actualOutput = Main.getMatchingString(str);

        assertEquals("bb", actualOutput);
    }

    @Test
    public void testString5() {
        String str = "_xk_bb";

        String actualOutput = Main.getMatchingString(str);

        assertEquals("bb", actualOutput);
    }

}