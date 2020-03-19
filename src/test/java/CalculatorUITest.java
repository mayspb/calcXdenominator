import calculator.InOut;
import org.testng.annotations.Test;

import java.io.IOException;

public class CalculatorUITest extends TestBase {

    @Test(dataProvider = "getData", dataProviderClass = TestData.class)
    public void testUI(String value, Object expected) throws InterruptedException, IOException {
        startCalc();
        getDisplayedText();
        userEnters(value + "\r\n");
        if (!expected.equals("rejection")) {
            assertEquals(getDisplayedText(), "1 / " + value + " = " + expected);
        } else {
            assertEquals(getDisplayedText(), InOut.REJECTION_TEXT + "'" + value + "'");
        }
    }

}
