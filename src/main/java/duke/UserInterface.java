package duke;

import duke.command.Command;
import duke.exception.DukeBlankDescriptionsException;
import duke.exception.DukeInvalidTaskIndexException;
import duke.task.Task;

import java.util.Scanner;

/**
 * Deals with sending messages and receiving input from users.
 */
public class UserInterface {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";
    private static final int USER_COMMAND_INDEX = 0;
    private static final int REMAINING_USER_INPUT_INDEX = 1;
    private static final Scanner sc = new Scanner(System.in);
    private static String[] userInputs;

    /**
     * Executes the command give.
     * If command given is invalid, user will be notified through standard output.
     *
     * @param userCommand The command given by the user.
     */
    public static void executeCommand(Command userCommand) {
        if (userCommand == Command.EXIT) {
            showExitMessage();
            Duke.isRunning = false;
            return;
        } else if (userCommand == Command.LIST) {
            showList();
            return;
        } else if (userCommand == Command.INVALID) {
            showInvalidCommand();
            return;
        }
        
        if (userInputs.length == 1) {
            showWrongFormat();
            return;
        }
        
        if (userCommand == Command.ADD_TO_DO || userCommand == Command.ADD_DEADLINE
                || userCommand == Command.ADD_EVENT) {
            addTask(userCommand, userInputs[REMAINING_USER_INPUT_INDEX]);
        } else if (userCommand == Command.DONE) {
            setDone();
        } else if (userCommand == Command.DELETE) {
            deleteTask();
        } else if (userCommand == Command.FIND) {
            findTask();
        } 
    }

    private static void findTask() {
        printLine();
        TaskManager.printRelatedTask(userInputs[REMAINING_USER_INPUT_INDEX]);
        printLine();
    }

    private static void deleteTask() {
        try {
            int taskIndex = Parser.getTaskIndex(userInputs[REMAINING_USER_INPUT_INDEX]);
            showItemDeleted(TaskManager.delete(taskIndex));
        } catch (DukeInvalidTaskIndexException | NumberFormatException e) {
            showInvalidIndex();
        } catch (ArrayIndexOutOfBoundsException e) {
            showWrongFormat();
        }
    }

    private static void setDone() {
        try {
            int taskIndex = Parser.getTaskIndex(userInputs[REMAINING_USER_INPUT_INDEX]);
            TaskManager.setDone(taskIndex);
            showItemSetDone(taskIndex);
        } catch (DukeInvalidTaskIndexException | NumberFormatException e) {
            showInvalidIndex();
        } catch (ArrayIndexOutOfBoundsException e) {
            showWrongFormat();
        }
    }

    /**
     * Interprets the command given by the user.
     *
     * @return The command given by the user.
     */
    public static Command interpretUserInput() {
        String userInput = sc.nextLine();
        if (Parser.isExitCommand(userInput)) {
            return Command.EXIT;
        } else if (Parser.isListCommand(userInput)) {
            return Command.LIST;
        }

        userInputs = Parser.splitCommandAndRemainder(userInput);

        if (Parser.isAddToDoCommand(userInputs[USER_COMMAND_INDEX])) {
            return Command.ADD_TO_DO;
        } else if (Parser.isAddDeadlineCommand(userInputs[USER_COMMAND_INDEX])) {
            return Command.ADD_DEADLINE;
        } else if (Parser.isAddEventCommand(userInputs[USER_COMMAND_INDEX])) {
            return Command.ADD_EVENT;
        } else if (Parser.isSetDoneCommand(userInputs[USER_COMMAND_INDEX])) {
            return Command.DONE;
        } else if (Parser.isDeleteCommand(userInputs[USER_COMMAND_INDEX])) {
            return Command.DELETE;
        } else if (Parser.isFindCommand(userInputs[USER_COMMAND_INDEX])) {
            return Command.FIND;
        }

        return Command.INVALID;
    }

    private static void addTask(Command addCommand, String line) {
        try {
            TaskManager.addTask(addCommand, line);
            showItemAdded();
        } catch (DukeBlankDescriptionsException | ArrayIndexOutOfBoundsException e) {
            showWrongFormat();
        }
    }

    private static void printLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Prints a greeting to standard output.
     */
    public static void showGreet() {
        System.out.println("Greetings from\n" + LOGO);
        printLine();
        System.out.print("Hello! I'm DUKE\n" + "What can I do for you?\n");
        printLine();
    }

    private static void showInvalidCommand() {
        printLine();
        System.out.println("Invalid command!");
        printLine();
    }

    private static void showItemSetDone(int taskIndex) {
        printLine();
        System.out.println("Got it. I've set this task as done:\n" + TaskManager.getTask(taskIndex));
        printLine();
    }

    private static void showInvalidIndex() {
        printLine();
        System.out.println("Invalid index");
        printLine();
    }

    private static void showList() {
        printLine();
        TaskManager.printTaskList();
        printLine();
    }

    private static void showExitMessage() {
        printLine();
        System.out.print("Bye. Hope to see you again soon!\n");
        printLine();
    }

    private static void showItemAdded() {
        printLine();
        System.out.println("Got it. I've added this task:\n" + TaskManager.getLatestTask());
        System.out.println("Now you have " + TaskManager.getNumOfTasks() + " task(s)");
        printLine();
    }

    private static void showWrongFormat() {
        printLine();
        System.out.println("Wrong format!");
        System.out.println("Missing some description or wrong command format.");
        printLine();
    }

    /**
     * Prints "Loaded saved data successfully!" to standard output.
     */
    public static void showLoadSuccess() {
        System.out.println("Loaded saved data successfully!");
        printLine();
    }

    /**
     * Prints "Data saved Successfully!" to standard output.
     */
    public static void showSaveSuccess() {
        System.out.println("Data saved Successfully!");
    }

    /**
     * Prints "Error saving data. Some or all data maybe lost." to standard output.
     */
    public static void showSaveError() {
        System.out.println("Error saving data. Some or all data maybe lost.");
    }

    /**
     * Prints "Error saving data. Some or all data maybe lost." to standard output.
     * A line will be printed to act as a separator.
     */
    public static void showSaveErrorWithLine() {
        System.out.println("Error saving data. Some or all data maybe lost.");
        printLine();
    }

    /**
     * Prints "Missing data file. An empty one is created." to standard output.
     */
    public static void showMissingDataFile() {
        System.out.println("Missing data file. An empty one is created.");
        printLine();
    }

    private static void showItemDeleted(Task task) {
        UserInterface.printLine();
        System.out.println("Noted! I've deleted this task:");
        System.out.println(task);
        System.out.println("Now you have " + TaskManager.getNumOfTasks() + " tasks");
        UserInterface.printLine();
    }

    /**
     * Prints "DukeData.txt has missing data. Some Tasks might be lost." to standard output.
     */
    public static void showFileCorrupted() {
        System.out.println("DukeData.txt has missing data. Some Tasks might be lost.");
        printLine();
    }
}
