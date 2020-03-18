import calculator.Handler;
import calculator.InOut;
import org.testng.annotations.Test;

public class CalculatorTest extends TestBase {

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
            String consoleOutput = outputStream.toString().replaceAll("\r\n", "");
            expected = InOut.REJECTION_TEXT + "'" + value + "'";
            assertEquals(consoleOutput, expected);
        }
    }

}
