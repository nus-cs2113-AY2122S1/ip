package duke;

import duke.manager.TaskManager;

/**
 * TODO : Better Documentation
 *        Add more constants
 *        Update Tests
 *        Create more exceptions
 *        Add checkstyle
 */

public class Duke {

    private final static String WELCOME_GREETING = "Howdy there! I'm Fluke";
    private final static String WELCOME_ASK = "What can I do for you today master?";
    private final static String BYE_MESSAGE = "Bye. Hope to serve you again master!";

    public static void main(String[] args) {
        welcomeMessage();
        TaskManager.processInput();
        byeMessage();
    }

    /**
     * Prints a welcome message on the console
     */
    private static void welcomeMessage() {
        TaskManager.printLine();
        System.out.println(WELCOME_GREETING);
        System.out.println(WELCOME_ASK);
        TaskManager.printLine();
    }

    /**
     * Prints a bye message on the console
     */
    private static void byeMessage() {
        TaskManager.printLine();
        System.out.println(BYE_MESSAGE);
        TaskManager.printLine();
    }
}
