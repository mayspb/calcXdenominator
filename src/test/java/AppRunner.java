import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;

public class AppRunner {

    private PipedOutputStream pipedOutputStream;
    private PipedInputStream pipedInputStream;
    private ByteArrayOutputStream outputStream;

    public AppRunner() throws IOException {
        pipedOutputStream = new PipedOutputStream();
        pipedInputStream = new PipedInputStream(pipedOutputStream);
        System.setIn(pipedInputStream);

        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    public void startCalc() {
        Thread thread = new Thread("Calculator"){
            @Override public void run(){Calculator.main();}
        };
        thread.setDaemon(true);
        thread.start();
    }

    public boolean hasDisplayed(String text) throws InterruptedException {
        boolean displayed = false;
        int tries = 10;
        while(tries > 0 && !displayed){
            Thread.sleep(100);
            displayed = outputStream.toString().contains(text);
            tries--;
        }
        clearOutputStream();
        return displayed;
    }

    public void userEnters(String userInput) throws IOException {
        pipedOutputStream.write(userInput.getBytes());
    }

    public void clearOutputStream() {
        outputStream.reset();
    }
}