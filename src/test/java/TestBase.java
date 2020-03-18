import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;

public class TestBase extends Assert {

    private PipedOutputStream pipedOutputStream;
    private PipedInputStream pipedInputStream;
    private ByteArrayOutputStream outputStream;

    @BeforeTest
    public void beforeTest() throws IOException {
        pipedOutputStream = new PipedOutputStream();
        pipedInputStream = new PipedInputStream(pipedOutputStream);
        System.setIn(pipedInputStream);

        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterTest
    public void afterTest() {
        clearOutputStream();
    }

    public void startCalc() {
        Thread thread = new Thread("Calculator"){
            @Override public void run(){Calculator.main();}
        };
        thread.setDaemon(true);
        thread.start();
    }

    public String getDisplayedText() throws InterruptedException {
        boolean displayed = false;
        int tries = 15;
        while(tries > 0 && !displayed){
            Thread.sleep(100);
            displayed = outputStream.toString().length() > 0;
            tries--;
        }
        String output = outputStream.toString().replaceAll("\r\n$", "");
        clearOutputStream();
        return output;
    }

    public void userEnters(String userInput) throws IOException {
        pipedOutputStream.write(userInput.getBytes());
    }

    public void clearOutputStream() {
        outputStream.reset();
    }
}