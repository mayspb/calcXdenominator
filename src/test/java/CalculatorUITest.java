import calculator.InOut;
import org.testng.annotations.Test;

import java.io.IOException;

public class CalculatorUITest extends TestBase {

    @Test(dataProvider = "getData", dataProviderClass = TestData.class)
    public void testUI(String value, Object expected) throws InterruptedException, IOException {
        startCalc();
        assertEquals(getDisplayedText(), InOut.START_DESCRIPTION + "\r\n" + InOut.ENTER_VALUE);
        userEnters(value + "\r\n");
        if (!expected.equals("rejection")) {
            assertEquals(getDisplayedText(), "1 / " + value + " = " + expected);
        } else {
            assertEquals(getDisplayedText(), InOut.REJECTION_TEXT + value);
        }
    }

}
