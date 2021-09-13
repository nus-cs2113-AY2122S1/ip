package duke;

import java.io.FileNotFoundException;

public class Duke {
    private static final String WELCOME_MESSAGE = "     Hello! I'm duke.Duke\n     What can I do for you?";
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

    public static void loadSavedData(TaskManager taskManager) {
        try {
            taskManager.loadSavedData();
        } catch (FileNotFoundException e) {
            System.out.println("     file from hard disk is not found. Unable to load data");
        }

    }

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        loadSavedData(taskManager);

        printWelcomeMessage();
        readUserInputUntilBye(taskManager);
        printExitMessage();
    }
}
