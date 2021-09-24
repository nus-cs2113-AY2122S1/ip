package duke;

public class Ui {
    private static final String WELCOME_MESSAGE = "     Hello! I'm Duke\n     What can I do for you?";
    private static final String EXIT_MESSAGE = "     Bye. Hope to see you again soon!";

    private static final String DIVIDER = "    ____________________________________________________________";

    public static void printHorizontalLine() {
        System.out.println(DIVIDER);
    }

    public static void printWelcomeMessage() {
        printHorizontalLine();
        System.out.println(WELCOME_MESSAGE);
        printHorizontalLine();
    }

    public static void printExitMessage() {
        printHorizontalLine();
        System.out.println(EXIT_MESSAGE);
        printHorizontalLine();
    }

    public static void readUserInputUntilBye(TaskManager taskManager) {
        taskManager.processUserInput();
    }
}
