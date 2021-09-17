package duke.control;

import java.io.IOException;
import java.util.Scanner;

public class Duke {

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

    private static void executeCommand(Command command, TaskList list, String input) {
        switch (command) {
        case LIST_COMMAND:
            list.printList();
            break;
        case HELP_COMMAND:
            Ui.printHelpMessage();
            break;
        case DONE_COMMAND:
            executeDoneCommand(command, list, input);
            break;
        case DELETE_COMMAND:
            executeDeleteCommand(command, list, input);
            break;
        case CLEAR_SAVE_COMMAND:
            executeClearSaveCommand();
            break;
        case SAVE_COMMAND:
            executeSaveCommand(list);
            break;
        case TASK_COMMAND:
            executeTaskCommand(list, input);
            break;
        }
    }

    private static void executeTaskCommand(TaskList list, String input) {
        try {
            list.addEntryToList(input);
            Storage.saveData(list);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("todo, deadline or event commands must have task descriptions");
        } catch (InvalidInputFormatException e) {
            Ui.printMissingDateTimeMessage();
        }
    }

    private static void executeDeleteCommand(Command command, TaskList list, String input) {
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
        Storage.saveData(list);
    }

    private static void executeDoneCommand(Command command, TaskList list, String input) {
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

    private static void executeClearSaveCommand() {
        try {
            Storage.clearSavedData();
        } catch (IOException e) {
            System.out.println("Could not find save file to clear. The file will be created after the first \"bye\"" +
                    "or \"save\" command that you enter");
        }
        System.out.println("I've cleared your saved data");
    }

    private static void executeSaveCommand(TaskList list) {
        Storage.saveData(list);
        System.out.println("List has been saved");
    }

    public static void main(String[] args) {
        Ui.printWelcomeMessage();
        TaskList list = new TaskList();
        Storage.setPath();
        Storage.loadDataFromFile(list);
        Scanner in = new Scanner(System.in);
        String userInput;
        while (true) {
            userInput = Ui.getUserResponse(in);
            Command command;
            try {
                command = processInput(userInput);
            } catch (InvalidInputFormatException e) {
                Ui.printInvalidInputMessage();
                continue;
            }
            if (command.equals(Command.BYE_COMMAND)) {
                Ui.printExitMessage();
                Storage.saveData(list);
                break;
            }
            executeCommand(command, list, userInput);
            Ui.printResponseSeparator();
        }
    }

}
