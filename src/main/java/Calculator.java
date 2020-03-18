import calculator.Handler;
import calculator.InOut;

public class Calculator {

    public static void main(String... args) {
        InOut inOut = new InOut();
        inOut.printDescription();
        try {
            String input;
            if (args.length > 0) {
                input = args[0];
                inOut.setInput(input);
            } else {
                inOut.inputValue();
                input = inOut.getInput();
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
    }
}