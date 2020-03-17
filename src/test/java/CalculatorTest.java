import calculator.Handler;
import calculator.InOut;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CalculatorTest extends Assert {

    // Store the original standard out before changing it.
    private final PrintStream originalStdOut = System.out;
    private ByteArrayOutputStream consoleContent = new ByteArrayOutputStream();

    @BeforeMethod
    public void beforeTest() {
        // Redirect all System.out to consoleContent.
        System.setOut(new PrintStream(this.consoleContent));
    }

    @Test(dataProvider = "getData", dataProviderClass = TestData.class)
    public void testMethods(String value, Object expected) {
        InOut inOut = new InOut();
        inOut.setInput(value);
        Handler handler = new Handler();
        if (handler.validateInput(value)) {
            double result = handler.calculate(value);
            inOut.printResult(result);
            assertEquals(result, expected);
        } else {
            inOut.printRejection();
            String consoleOutput = this.consoleContent.toString().replaceAll("\r\n", "");
            expected = InOut.REJECTION_TEXT + value;
            assertEquals(consoleOutput, expected);
        }
    }

    @AfterMethod
    public void afterTest() {
        // Put back the standard out.
        System.setOut(this.originalStdOut);

        // Print what has been captured.
        System.out.println(this.consoleContent.toString());

        // Clear the consoleContent.
        this.consoleContent = new ByteArrayOutputStream();
    }
}
