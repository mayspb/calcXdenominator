package calculator;

import java.util.regex.Pattern;

public class Handler {

    public double calculate(String value) {
        return (1 / Double.parseDouble(value));
    }

    public boolean validateInput(String input) {
        return Pattern.matches("[-+]?\\d*\\.?\\d*(E|E-)?\\d{1,3}", input);
    }

}
