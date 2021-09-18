package tan;

import java.util.Scanner;

public class Ui {
    final static String BORDER = "------------------------------------------------------------------------";
    private static final Scanner SC = new Scanner(System.in);

    /**
     * Prints the intro message & border once.
     */
    public static void printIntro() {
        System.out.println("Top of the morning my good sir, what can I do for you on this fine day?");
        System.out.println(BORDER);
    }

    /**
     * Prints the outro message & border once.
     */
    public static void printOutro() {
        System.out.println("I bid you farewell my good man. Good Bye.");
        System.out.println(BORDER);
    }

    /**
     * Returns the user's input in String
     * after stripping leading and trailing spaces.
     *
     * @return User's input in a String format without any trailing spaces.
     */
    public static String readInput() {
        String input = SC.nextLine();
        return input.strip();
    }

    static void printBorder() {
        System.out.println(BORDER);
    }
}
