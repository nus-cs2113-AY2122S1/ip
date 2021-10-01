package duke.ui;

import duke.exception.AtEmptyException;
import duke.exception.ByEmptyException;
import duke.exception.NumberOutOfBoundsException;
import duke.tasklist.TaskList;

import java.util.Scanner;

public class Ui {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final Scanner SCANNER = new Scanner(System.in);
    public static final String SAD_FACE = "\uD83D\uDE41";

    public Ui(){
    }

    /**
     * Prints the Duke Logo
     */
    public void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Prints a greeting to the user
     * @param s First line of the greeting
     * @param s2 Second Lind of the greeting
     */
    public static void printGreeting(String s, String s2) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(s);
        System.out.println(s2);
    }

    /**
     * Retrieves and trims the input from the user
     * @return trimmed user input as a String
     */
    public static String getUserInput() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Enter command: ");
        String input = SCANNER.nextLine();
        while (input.trim().isEmpty()) {
            input = SCANNER.nextLine();
        }
        return input;
    }

    /**
     * Prints a reply to the user and informs the user when an invalid command is given
     * @param userInput input from the user
     */
    public static void printReply(String userInput) {
        try {
            TaskList.processUserInput(userInput);
        } catch (NumberFormatException e) {
            System.out.println(SAD_FACE + " OOPS! The character you entered is not a number: " + TaskList.number);
        } catch (NumberOutOfBoundsException e) {
            System.out.println(SAD_FACE + e.getMessage() + TaskList.number);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(SAD_FACE + " The description of " + "\"" + TaskList.inputCommand + "\"" + " is not " +
                    "complete");
        } catch (AtEmptyException e) {
            System.out.println(SAD_FACE + e.getMessage());
        } catch (ByEmptyException e) {
            System.out.println(SAD_FACE + e.getMessage());
        }
    }

    /**
     * Tells the user that a command was not given
     */
    public static void printInvalidInput() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(SAD_FACE + " OOPS! I'm sorry, but I don't know what that means " + SAD_FACE);
    }

    /**
     * Terminates Duke
     */
    public static void exitProgram() {
        printGreeting("Bye. Hope to see you again soon!", HORIZONTAL_LINE);
        System.exit(0);
    }
}
