package Test.testng;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.GregorianCalendar;

public class ParameterTest2 {
    @Test
    @Parameters({"systemPort", "udid"})
    public void testTestNGParams(String teo, String ti) {
        System.out.println(new GregorianCalendar().getTime());
        System.out.println(teo + " || " + ti);
    }
}
