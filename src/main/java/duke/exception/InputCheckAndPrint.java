package duke.exception;

public class InputCheckAndPrint {
    String name;

    public InputCheckAndPrint(String name) {
        this.name = name;
    }

    //short function to check for at most 2 sentences
    public static boolean isValidDeadline(String input) {
        return (countWhitespace(input) <= 1);
    }

    public static boolean isNull(String input) {
        return input.equals(null);
    }

    public static boolean isEmpty(String input) {
        return input.equals("");
    }
    public static int countWhitespace(String input) {
        int spaceCount = 0;
        for (char s : input.toCharArray()) {
            if (s == ' ') {
                spaceCount++;
            }
        }
        return spaceCount;
    }

    public static boolean startsWithSpace(String input) {
        return input.startsWith(" ");
    }

    public static String cleanUpString(String input) {
        input = input.trim();
        input = input.replaceAll(" ", "");
        return input;
    }
    public static boolean isIntegerInput(String input) {
        input = cleanUpString(input);
        for (char x : input.toCharArray()) {
            if (!Character.isDigit(x)) {
                return false;
            }
        }
        return true;
    }

    public static boolean inListRange(int index, int taskCount) {
        return index >= 0 && index < taskCount; //taskCount is the latest "empty" block
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

    public static void printDeadlineFormatIssue() {
        System.out.println("Please use the following format to add deadlines:");
        System.out.println("task_id /by deadline");
        System.out.println("e.g 1 /by 2pm today");
    }
}
