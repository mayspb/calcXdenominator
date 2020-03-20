import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;

public class TestBase extends Assert {

    protected final PrintStream originalStdOut = System.out;
    protected PipedOutputStream pipedOutputStream;
    protected PipedInputStream pipedInputStream;
    protected ByteArrayOutputStream outputStream;
    private PrintStream tempPrintStream;

    @BeforeMethod
    public void beforeTest() throws IOException {
        pipedOutputStream = new PipedOutputStream();
        pipedInputStream = new PipedInputStream(pipedOutputStream);
        System.setIn(pipedInputStream);

        outputStream = new ByteArrayOutputStream();
        tempPrintStream = new PrintStream(outputStream);
        System.setOut(tempPrintStream);
    }

    @AfterMethod
    public void afterTest() {
        System.setOut(originalStdOut);
        System.out.println(outputStream.toString());
        clearOutputStream();
    }

    protected void startCalc() {
        Thread thread = new Thread("Calculator") {
            @Override
            public void run() {
                Calculator.main();
            }
        };
        thread.setDaemon(true);
        thread.start();
    }

    protected String getDisplayedTextPrintAndClearStream() {
        isDisplayedText();
        String output = getDisplayedText();
        toggleOutAndPrint(output);
        clearOutputStream();
        return output;
    }

    protected boolean isDisplayedText() {
        boolean displayed = false;
        int tries = 15;
        while (tries > 0 && !displayed) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            displayed = outputStream.toString().length() > 0;
            tries--;
        }
        return displayed;
    }

    protected String getDisplayedText() {
        return outputStream.toString().replaceAll("(\r\n?|\n)$", "");
    }

    protected void userEnters(String userInput) throws IOException {
        pipedOutputStream.write(userInput.getBytes());
        toggleOutAndPrint(userInput);
    }

    protected void clearOutputStream() {
        outputStream.reset();
    }

    protected void toggleOutAndPrint(String output) {
        System.setOut(originalStdOut);
        System.out.println(output);
        System.setOut(tempPrintStream);
    }

    protected String getErrorMessage(String found, String expected) {
        return "String from console output is not what is expected:\n" +
                "Expected: " + expected + "\n" +
                "Found:    " + found + "\n";
    }
}
