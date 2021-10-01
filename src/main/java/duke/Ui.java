package duke;
import java.util.List;
import java.util.Scanner;

class Ui {
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Print the message of a given exception object
     * @param e The exception in question
     */
    public static void printException(Exception e) {
        System.out.println(e.getMessage());
    }

    /**
     * Print a List with each item prefixed by the 1-based index and a semicolon
     * e.g. 1: MyObjectStringified
     * @param list The list to be printed
     */
    public static void printWithIndex(List<?> list) {
        for (int i = 1; i <= list.size(); i += 1) {
            System.out.println(i + ": " + list.get(i - 1));
        }
    }

    /**
     * Get the next line from standard input
     * @return The line, or null if no line is available
     */
    public static String nextLine() {
        if (!sc.hasNextLine()) {
            return null;
        }
        return sc.nextLine();
    }
}
