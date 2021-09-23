package duke.exceptionhandler;

/**
 * Print statements to inform the user of correct input
 */
public class InputCheckAndPrint {
    String name;

    public InputCheckAndPrint(String name) {
        this.name = name;
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

}
