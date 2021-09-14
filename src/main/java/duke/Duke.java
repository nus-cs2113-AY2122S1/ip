package duke;

import duke.command.Command;

import java.util.Scanner;

public class Duke {
    public static final String LINE = "─────────────────────────────────────────────────────────────\n";

    public static void main(String[] args) {
        printHelloMessage();
        processUserInput();
    }

    /**
     * Processes user input
     */
    private static void processUserInput() {
        boolean isProcessing = true;
        TaskManager taskManager = new TaskManager();
        Scanner input = new Scanner(System.in);
        DataHandler dataHandler = null;
        try {
            dataHandler = new DataHandler();
            dataHandler.loadData(taskManager);
        } catch (DukeException e) {
            System.out.println(LINE);
            System.out.println(e.getMessage());
            System.out.println(LINE);
        }
        while (isProcessing) {
            String userInput = input.nextLine().stripLeading();
            try {
                Command command = CommandManager.processCommand(userInput);
                command.executeCommand(taskManager, dataHandler);
                isProcessing = !Command.getIsExit();
            } catch (DukeException e) {
                System.out.println(LINE);
                System.out.println(e.getMessage());
                System.out.println(LINE);
            }
        }
        printByeMessage();
    }

    /**
     * Prints Goodbye message before app exits
     */
    private static void printByeMessage() {
        String byeGreeting = "Bye. Hope to see you again soon!\n";
        System.out.println(LINE + byeGreeting + LINE);
    }

    /**
     * Prints the welcome message when app is launched
     */
    private static void printHelloMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String helloGreeting = "Hello! I'm Duke\n" + "What can I do for you?\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(LINE + helloGreeting + LINE);
    }

}
