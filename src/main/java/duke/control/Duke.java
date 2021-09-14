package duke.control;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    static final String RESPONSE_SEPARATOR = "=============================" +
            "===========================================";

    enum Command {
        HELP_COMMAND,
        LIST_COMMAND,
        DONE_COMMAND,
        TASK_COMMAND,
        BYE_COMMAND
    }

    private static String getUserResponse(Scanner in) {
        String line;
        line = in.nextLine();
        return line;
    }

    private static Command processInput(String input) throws InvalidInputFormatException {
        if (input.equals("list")) {
            return Command.LIST_COMMAND;
        }
        if (input.equals("help")) {
            return Command.HELP_COMMAND;
        }
        if (input.equals("bye")) {
            return Command.BYE_COMMAND;
        }
        if (input.startsWith("done")) {
            return Command.DONE_COMMAND;
        }
        if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
            return Command.TASK_COMMAND;
        }
        throw new InvalidInputFormatException();
    }

    private static void executeCommand(Command command, List list, String input) {
        switch (command) {
        case LIST_COMMAND:
            list.printList();
            break;
        case HELP_COMMAND:
            printHelpMessage();
            break;
        case DONE_COMMAND:
            try {
                int entryNumber = list.parseInputForEntryNumber(input);
                list.doneEntry(entryNumber);
            } catch (NumberFormatException e) {
                System.out.println("the done command is of the form \"done x\" where x is an entry number");
            } catch (NullPointerException e) {
                System.out.println("That entry number does not exist in your current list");
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("The done command must have an entry number, " +
                        "enter it in the form \"done x\" where x is an entry number");
            }
            break;
        case TASK_COMMAND:
            try {
                list.addEntryToList(input);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("todo, deadline or event commands must have task descriptions");
            }
            break;
        }
    }

    public static void main(String[] args) {
        printWelcomeMessage();
        List list = new List();
        new FileManager();
        try {
            FileManager.readDukeDataFromFile(list);
        } catch (IOException e) {
            System.out.println("Something went wrong, cannot load saved data");
        }
        Scanner in = new Scanner(System.in);
        String userInput;
        while (true) {
            userInput = getUserResponse(in);
            Command command;
            try {
                command = processInput(userInput);
            } catch (InvalidInputFormatException e) {
                printInvalidInputMessage();
                continue;
            }
            if (command.equals(Command.BYE_COMMAND)) {
                printExitMessage();
                FileManager.saveData(list);
                break;
            }
            executeCommand(command, list, userInput);
            printResponseSeparator();
        }
    }

    private static void printExitMessage() {
        System.out.println("Bye! See you soon!");
    }

    private static void printInvalidInputMessage() {
        System.out.println("I don't know what you mean, please look at the instructions and try again");
    }

    private static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("I am currently able to help you keep track of tasks");
        System.out.println("scheduled to receive a personality soon, exciting!");
        System.out.println("Type \"help\" to find out what I can do for you.");
        printResponseSeparator();
    }

    private static void printHelpMessage() {
        System.out.println("I currently support 3 types of tasks: todo, deadline and event");
        System.out.println("To add a todo task: todo (task name)");
        System.out.println("To add a deadline task: deadline (task name) /by (date or time)");
        System.out.println("To add an event task: event (task name) /at (date or time)");
        System.out.println("note that only the words in parentheses() can be replaced");
        System.out.println("Use the command \"list\" and I will show you your current list");
        System.out.println("To cross out an entry, use the command \"done x\" where x is the entry number");
        System.out.println("When you're done, type \"bye\" to end the program");
    }

    private static void printResponseSeparator() {
        System.out.println(RESPONSE_SEPARATOR);
    }
}
