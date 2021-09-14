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
        DELETE_COMMAND,
        CLEAR_SAVE_COMMAND,
        SAVE_COMMAND,
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
        if (input.startsWith("delete")) {
            return Command.DELETE_COMMAND;
        }
        if (input.startsWith("clearsave")) {
            return Command.CLEAR_SAVE_COMMAND;
        }
        if (input.startsWith("save")) {
            return Command.SAVE_COMMAND;
        }
        if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
            return Command.TASK_COMMAND;
        }
        throw new InvalidInputFormatException();
    }

    private static void executeCommand(Command command, List list, String input, FileManager fileManager) {
        switch (command) {
        case LIST_COMMAND:
            list.printList();
            break;
        case HELP_COMMAND:
            printHelpMessage();
            break;
        case DONE_COMMAND:
            executeDoneCommand(command, list, input);
            break;
        case DELETE_COMMAND:
            executeDeleteCommand(command, list, input);
            break;
        case CLEAR_SAVE_COMMAND:
            executeClearSaveCommand(fileManager);
            break;
        case SAVE_COMMAND:
            executeSaveCommand(fileManager, list);
            break;
        case TASK_COMMAND:
            executeTaskCommand(list, input);
            break;
        }
    }

    private static void executeTaskCommand(List list, String input) {
        try {
            list.addEntryToList(input);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("todo, deadline or event commands must have task descriptions");
        } catch (InvalidInputFormatException e) {
            printMissingDateTimeMessage();
        }
    }

    private static void executeDeleteCommand(Command command, List list, String input) {
        try {
            int entryNumber = list.parseInputForEntryNumber(input, command);
            list.deleteEntry(entryNumber);
        } catch (NumberFormatException e) {
            //entry number is an invalid character
            System.out.println("the delete command is of the form \"delete x\" where x is an entry number");
        } catch (StringIndexOutOfBoundsException e) {
            //entry number is missing
            System.out.println("The delete command must have an entry number, " +
                    "enter it in the form \"delete x\" where x is an entry number");
        } catch (IndexOutOfBoundsException e) {
            //entry number does not exist in the list
            System.out.println("That entry number does not exist in your list");
        }
    }

    private static void executeDoneCommand(Command command, List list, String input) {
        try {
            int entryNumber = list.parseInputForEntryNumber(input, command);
            list.doneEntry(entryNumber);
        } catch (NumberFormatException e) {
            //entry number is an invalid character
            System.out.println("the done command is of the form \"done x\" where x is an entry number");
        } catch (StringIndexOutOfBoundsException e) {
            //entry number is missing
            System.out.println("The done command must have an entry number, " +
                    "enter it in the form \"done x\" where x is an entry number");
        } catch (IndexOutOfBoundsException e) {
            //entry number does not exist in the list
            System.out.println("That entry number does not exist in your list");
        }
    }

    private static void executeClearSaveCommand(FileManager fileManager) {
        try {
            fileManager.clearSavedData();
        } catch (IOException e) {
            System.out.println("Could not find save file to clear. The file will be created after the first \"bye\"" +
                    "or \"save\" command that you enter");
        }
        System.out.println("I've cleared your saved data");
    }

    private static void executeSaveCommand(FileManager fileManager, List list) {
        fileManager.saveData(list);
        System.out.println("List has been saved");
    }

    public static void main(String[] args) {
        printWelcomeMessage();
        List list = new List();
        FileManager fileManager = new FileManager();
        loadDataFromFile(list);
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
                fileManager.saveData(list);
                break;
            }
            executeCommand(command, list, userInput, fileManager);
            printResponseSeparator();
        }
    }

    private static void loadDataFromFile(List list) {
        try {
            FileManager.readDukeDataFromFile(list);
        } catch (IOException e) {
            System.out.println("Something went wrong, cannot load saved data");
        }
    }

    private static void printExitMessage() {
        System.out.println("Bye! See you soon!");
    }

    private static void printInvalidInputMessage() {
        System.out.println("I don't know what you mean, please look at the instructions and try again");
    }

    public static void printMissingDateTimeMessage() {
        System.out.println("Command not entered properly, remember to use \"/by\" or " +
                "\"/at\" modifiers for deadline and event tasks respectively. Type \"help\"" +
                " for more information");
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
        System.out.println("To delete a task in the list: delete (x) where x is the entry number");
        System.out.println("note that only the words in parentheses() can be replaced");
        System.out.println("To see your current list, Use the command \"list\"");
        System.out.println("To cross out an entry, use the command \"done x\" where x is the entry number");
        System.out.println("To save your current data, use the command \"save\"");
        System.out.println("To clear all your saved data, use the command \"clearsave\"");
        System.out.println("When you're done, type \"bye\" to end the program (automatically saves your current list)");
    }

    private static void printResponseSeparator() {
        System.out.println(RESPONSE_SEPARATOR);
    }
}
