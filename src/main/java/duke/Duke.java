package duke;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    private static final String WAVING_EMOJI = "\uD83D\uDC4B";
    private static final String SMILEY_EMOJI = "\uD83D\uDE04";

    private static final TasksList tasks = new TasksList();

    public static void main(String[] args) {
        String rawInput;
        Scanner scanner = new Scanner(System.in);
        DukeCommand dukeCommand;

        try {  // Initialize ./data/duke.txt to store tasks
            tasks.initDataStore();
        } catch (IOException e) {
            System.out.println("\t☹ Directory does not exist, error in loading data store.\n" +
                    "Please check if ./data/duke.txt exists.");
        }
        try {  // Load all tasks stored in ./data/duke.txt
            tasks.loadTasks();
        } catch (FileNotFoundException e) {
            System.out.println("\t☹ File is not found.");
        } catch (DukeException e) {
            System.out.println("\t☹ Invalid file type in data store.");
        }

        printWelcomeBanner();

        rawInput = scanner.nextLine();
        while (true) {
            try {
                dukeCommand = getDukeCommand(rawInput);
            } catch (DukeException e) {
                System.out.println("\tOOPS!!! I'm sorry, but I don't know what that means :-(");
                printHorizontalLine();
                rawInput = scanner.nextLine();
                continue;
            }

            switch (dukeCommand) {
            case BYE:
                printGoodbyeMessage();
                return;
            case DONE:
                tasks.markTaskAsDone(rawInput);
                break;
            case LIST:
                tasks.printTasks();
                break;
            case DELETE:
                try {
                    tasks.deleteTask(rawInput);
                } catch (NumberFormatException e) {
                    System.out.println("\t☹ Wrong format, please retype with format `delete <taskIndex>`.");
                }
                break;
            case TODO:
                tasks.addToDo(rawInput);
                break;
            case EVENT:
                tasks.addEvent(rawInput);
                break;
            case DEADLINE:
                tasks.addDeadline(rawInput);
                break;
            default:
                System.out.println("Invalid command issued!");
                break;
            }

            try {  // Update ./data/duke.txt so that all tasks are stored
                tasks.saveTasks();
            } catch (IOException e) {
                System.out.println("\t☹ Directory does not exist, error in loading data store.\n" +
                        "Please check if ./data/duke.txt exists.");
            }

            printHorizontalLine();
            rawInput = scanner.nextLine();
        }
    }

    // Identify and return the duke command issued by user
    public static DukeCommand getDukeCommand(String rawInput) throws DukeException{
        if (rawInput.equals("bye")) {
            return DukeCommand.BYE;
        } else if (rawInput.startsWith("done")) {
            return DukeCommand.DONE;
        } else if (rawInput.equals("list")) {
            return DukeCommand.LIST;
        } else if (rawInput.startsWith("todo")) {
            return DukeCommand.TODO;
        } else if (rawInput.startsWith("event")) {
            return DukeCommand.EVENT;
        } else if (rawInput.startsWith("deadline")) {
            return DukeCommand.DEADLINE;
        } else if (rawInput.startsWith("delete")) {
            return DukeCommand.DELETE;
        } else {
            throw new DukeException();
        }
    }

    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }
    public static void printWelcomeBanner() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        printHorizontalLine();
        System.out.println("Hello from\n" + logo);
        System.out.println("\tHello! I'm Duke " + SMILEY_EMOJI);
        System.out.println("\tWhat can I do for you?");
        printHorizontalLine();
    }

    public static void printGoodbyeMessage() {
        System.out.println("\tBye. Hope to see you again soon! " + WAVING_EMOJI);  // Unicode is a waving emoji
        printHorizontalLine();
    }
}
