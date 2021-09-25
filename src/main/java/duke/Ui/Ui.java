package duke.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Ui {
    public static final String COMMAND_DIVIDER = " /          / ";

    public static void printDivider() {
        System.out.println(COMMAND_DIVIDER);
    }

    public static void printDoneMessage() {
        System.out.println("\n / done tasks, good job! / ");
    }


    public static void printLine() {
        System.out.println("                 ...                 ");
    }

    public static void sayHi(String username) {
        printLine();
        System.out.println("Hello " + username + "!" + "\n" + "I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
    }

    public static void sayGoodbye() {
        printLine();
        System.out.println("Bye. Hope to see you  again soon!");
        printLine();
    }

    public static void printOneKeyword() {
        System.out.println("Please enter one, and only one keyword: ");
    }

    public static void printMatchingTasksAlert() {
        System.out.println("Here are the matching tasks in your list: ");
    }
    public static String readLine() {
        Scanner in = new Scanner(System.in);
        return  in.nextLine();
    }

    /**
     * Formates a single string into a date
     * @return LocalDate
     * @throws DateTimeParseException if string is not formatted
     */
    public static LocalDate getDate() throws DateTimeParseException {
        String userInput = Ui.readLine();
        return LocalDate.parse(userInput);
    }

    public static boolean isStop(String userInput) {
        return userInput.equalsIgnoreCase("stop");
    }

    public static String cleanUpString(String input) {
        input = input.trim();
        input = input.replaceAll(" ", "");
        return input;
    }

    public static void printNotInRange(int index) {
        System.out.println("... sorry, task " + (index + 1) + " is not in range. Try the 'done' command again!");
    }

    public static void printDoneFormat() {
        System.out.println("Simply type in the task ids, separated by a single whitespace");
        System.out.println("e.g 1 2 3 4 5");
    }

    public static void inputFailMessage() {
        System.out.println("☹ OOPS!!! Please follow the format:");
    }

    public static void printNoNull() {
        System.out.println("Please do not leave this field blank.");
    }

    public static void printIntegerOnly() {
        System.out.println("Please only input integers!");
    }

    public static void printDateFormat() {
        System.out.println("The format for a date is : YYYY-MM-DD");
        System.out.println("for instance, 1999-11-30!");
    }
}
