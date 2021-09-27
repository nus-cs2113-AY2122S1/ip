package Ui;
import java.util.Scanner;

/**
 * Deals with user interaction
 */
public class UserInterface {
    Scanner userInput = new Scanner(System.in);
    static String horizontal = " __________________________________________________\n";
    static String greeting = horizontal + "   Hello! I'm Duke\n" + "   What can I do for you?\n" + horizontal;
    static String farewell = horizontal + "   Bye! Hope to see you again soon!\n" + horizontal;

    /**
     * Constructor class that prints greeting message
     */
    public void UserInterface() {
        System.out.println(greeting);
    }

    /**
     * Prints farewell message
     */
    public void printFarewell() {
        System.out.println(farewell);
    }

    /**
     * Reads user input message
     * @return trimmed user input message
     */
    public String getUserInput() {
        return userInput.nextLine().trim();
    }

    /**
     * Prints any message
     * @param output output text to be shown on the User Interface
     */
    public void printOutput(String output) {
        System.out.println(horizontal + output + horizontal);
    }

    /**
     * Prints error message
     * @param errorMessage error message to be shown on the User Interface
     */
    public void printError(String errorMessage) {
        System.out.println(errorMessage);
    }

}
