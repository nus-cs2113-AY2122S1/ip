package duke;

import duke.command.Command;

import java.util.Scanner;

public class Duke {
    private static final String LS = System.lineSeparator();
    private static final String S_TAB = "     ";

    private static final String MESSAGE_WELCOME = S_TAB + "Welcome to Jura Tempest!" + LS
                                                + S_TAB + "I'm Rimuru Tempest, pleased to make your acquaintance." + LS
                                                + S_TAB + "How can I help you today?";
    private static final String MESSAGE_GOODBYE = S_TAB + "Sayonara. Come visit Jura Tempest again soon!";

    private static TaskList tasks = new TaskList();
    private static Storage storage = new Storage();

    public static void main(String[] args) {
        greetUser();
        executeResponse();
    }

    /**
     * Prints a message that greets the user.
     */
    private static void greetUser() {
        System.out.println(Picture.RIMURU_LOGO);
        System.out.println(MESSAGE_WELCOME);
        Picture.printLine();
    }

    /**
     * Continually waits for input user commands,
     * and executes the corresponding actions,
     * until the exit command is typed.
     */
    private static void executeResponse() {
        loadData();
        handleUserInput();
        exitDuke();
    }

    /**
     * Loads task data from an external save file.
     */
    private static void loadData() {
        try {
            storage.loadTask(tasks);
        } catch (DukeException e) {
            Picture.printLine();
            System.out.println(e.getMessage());
            Picture.printLine();
        }
    }

    /**
     * Reads in user input and performs a corresponding command.
     */
    private static void handleUserInput() {
        String userInput;
        boolean isExit = false;
        Scanner in = new Scanner(System.in);
        while (!isExit) {
            userInput = in.nextLine().strip();
            try {
                Command command = CommandParser.parse(userInput);
                command.runCommand(tasks, storage);
                isExit = command.isExitCommand();
            } catch (DukeException e) {
                Picture.printLine();
                System.out.println(e.getMessage());
                Picture.printLine();
            }
        }
    }

    /**
     * Prints an exit message.
     */
    private static void exitDuke() {
        Picture.printLine();
        System.out.println(MESSAGE_GOODBYE);
        Picture.printLine();
    }



}
