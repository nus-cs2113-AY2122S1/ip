package duke;

import duke.command.Command;

import java.util.Scanner;

public class UserInterface {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String LOGO = "\n" +
            "______  ___ ________  ___ ___  _   _ \n" +
            "| ___ \\/ _ |_   _|  \\/  |/ _ \\| \\ | |\n" +
            "| |_/ / /_\\ \\| | | .  . / /_\\ |  \\| |\n" +
            "| ___ |  _  || | | |\\/| |  _  | . ` |\n" +
            "| |_/ | | | || | | |  | | | | | |\\  |\n" +
            "\\____/\\_| |_/\\_/ \\_|  |_\\_| |_\\_| \\_/";
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
        } else if (userCommand == Command.LIST) {
            showList();
        }

        if (userCommand == Command.ADD_TODO || userCommand == Command.ADD_DEADLINE
                || userCommand == Command.ADD_EVENT) {
            try {
                addTask(userCommand, userInputs[REMAINING_USER_INPUT_INDEX]);
            } catch (ArrayIndexOutOfBoundsException e) {
                showWrongFormat();
            }
        } else if (userCommand == Command.DONE) {
            setDone();
        } else if (userCommand == Command.DELETE) {
            deleteTask();
        } else if (userCommand == Command.INVALID) {
            showInvalidCommand();
        }
    }

    private static void deleteTask() {
        try {
            int taskIndex = getTaskIndex(userInputs[REMAINING_USER_INPUT_INDEX]);
            showItemDeleted(TaskManager.delete(taskIndex));
        } catch (DukeInvalidTaskIndexException | NumberFormatException e) {
            showInvalidIndex();
        } catch (ArrayIndexOutOfBoundsException e) {
            showWrongFormat();
        }
    }

    private static void setDone() {
        try {
            int taskIndex = getTaskIndex(userInputs[REMAINING_USER_INPUT_INDEX]);
            TaskManager.setDone(taskIndex);
            showItemSetDone(taskIndex);
        } catch (DukeInvalidTaskIndexException | NumberFormatException e) {
            showInvalidIndex();
        } catch (ArrayIndexOutOfBoundsException e) {
            showWrongFormat();
        }
    }

    /**
     * Interpret the command given by the user.
     * 
     * @return The command given by the user.
     */
    public static Command interpretUserInput() {
        String userInput = sc.nextLine();
        if (userInput.replaceAll(" ", "").equals("bye")) {
            return Command.EXIT;
        } else if (userInput.replaceAll(" ", "").equals("list")) {
            return Command.LIST;
        }

        userInputs = splitCommandAndRemainder(userInput);

        if ("todo".equals(userInputs[USER_COMMAND_INDEX])) {
            return Command.ADD_TODO;
        } else if ("deadline".equals(userInputs[USER_COMMAND_INDEX])) {
            return Command.ADD_DEADLINE;
        } else if ("event".equals(userInputs[USER_COMMAND_INDEX])) {
            return Command.ADD_EVENT;
        } else if ("done".equals(userInputs[USER_COMMAND_INDEX])) {
            return Command.DONE;
        } else if ("delete".equals(userInputs[USER_COMMAND_INDEX])) {
            return Command.DELETE;
        }

        return Command.INVALID;
    }

    private static int getTaskIndex(String word) {
        return Integer.parseInt(word.replaceAll(" ", ""));
    }

    private static String[] splitCommandAndRemainder(String line) {
        return line.split(" ", 2);
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
        System.out.print("Hello! I'm BATMAN\n" + "What can I do for you?\n");
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
        TaskManager.printList();
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
        System.out.println("Now you have " + TaskManager.getNumOfTasks() + " tasks");
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
     * Prints "Error loading data. Some or all data maybe lost." to standard output.
     */
    public static void showLoadError() {
        System.out.println("Error loading data. Some or all data maybe lost.");
        printLine();
    }

    /**
     * Prints "Error saving data. Some or all data maybe lost." to standard output.
     */
    public static void showSaveError() {
        System.out.println("Error saving data. Some or all data maybe lost.");
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
}
