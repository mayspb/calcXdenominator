package calculator;

import java.util.Scanner;

public class InOut {

    public static final String REJECTION_TEXT = "Expression couldn't calculated with value ";
    public static final String START_DESCRIPTION = "This program makes calculation of expression 1/x, where x should be inputted by the user.";
    public static final String ENTER_VALUE = "Enter a value: ";

    private String input = "";

    public void printDescription() {
        System.out.println(START_DESCRIPTION);
    }

    public void inputValue() {
        System.out.print(ENTER_VALUE);
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getInput() {
        return input;
    }

    public void printResult(Double result) {
        System.out.println("1 / " + input + " = " + result);
    }

    public void printRejection() {
        System.out.println(REJECTION_TEXT + input);
    }

}
