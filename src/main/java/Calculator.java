import calculator.Handler;
import calculator.InOut;

public class Calculator {

    public static void main(String... args) {
        InOut inOut = new InOut();
        inOut.printDescription();
        do {
            try {
                inOut.inputValue();
                String input = inOut.getInput();
                if (input.toLowerCase().equals("exit")) {
                    break;
                }
                Handler handler = new Handler();
                if (handler.validateInput(input)) {
                    inOut.printResult(handler.calculate(input));
                } else {
                    inOut.printRejection();
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        } while (true);
        inOut.printBye();
    }
}