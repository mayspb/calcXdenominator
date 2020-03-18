import calculator.InOut;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import org.testng.Assert;

public class CalculatorUITest extends Assert {

    private AppRunner app = new AppRunner();

    public CalculatorUITest() throws IOException {
    }

    @Test(dataProvider = "getData", dataProviderClass = TestData.class)
    public void testUI(String value, Object expected) throws InterruptedException, IOException {
        app.startCalc();
        assertTrue(app.hasDisplayed(InOut.START_DESCRIPTION + "\r\n" + InOut.ENTER_VALUE));
        app.userEnters(value + "\r\n");
        if (!expected.equals("rejection")) {
            assertTrue(app.hasDisplayed("1 / " + value + " = " + expected));
        } else {
            assertTrue(app.hasDisplayed(InOut.REJECTION_TEXT + value));
        }
    }

    @AfterMethod
    public void afterTest() {
        app.clearOutputStream();
    }
}
