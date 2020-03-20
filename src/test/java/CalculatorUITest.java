import calculator.InOut;
import org.testng.annotations.Test;

import java.io.IOException;

public class CalculatorUITest extends TestBase {

    @Test(dataProvider = "getData", dataProviderClass = TestData.class)
    public void testUI(String value, Object expected) throws IOException {
        startCalc();
        getDisplayedTextPrintAndClearStream();
        userEnters(value + "\r\n");
        if (!expected.equals("rejection")) {
            assertEquals(getDisplayedTextPrintAndClearStream(), "1 / " + value + " = " + expected);
        } else {
            String consoleOutput = getDisplayedTextPrintAndClearStream();
            assertTrue(consoleOutput.contains(InOut.REJECTION_TEXT),
                    getErrorMessage(consoleOutput, InOut.REJECTION_TEXT));
        }
    }

}
