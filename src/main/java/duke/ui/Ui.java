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

    public void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void printGreeting(String s, String s2) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(s);
        System.out.println(s2);
    }

    public static String getUserInput() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Enter command: ");
        String input = SCANNER.nextLine();
        while (input.trim().isEmpty()) {
            input = SCANNER.nextLine();
        }
        return input;
    }

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


    public static void printInvalidInput() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println(SAD_FACE + " OOPS! I'm sorry, but I don't know what that means " + SAD_FACE);
    }

    public static void exitProgram() {
        printGreeting("Bye. Hope to see you again soon!", HORIZONTAL_LINE);
        System.exit(0);
    }
}
