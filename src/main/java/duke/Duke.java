package duke;

import duke.task.Task;
import duke.task.ToDo;
import duke.task.Event;
import duke.task.Deadline;

import java.util.Scanner;

public class Duke {

    private static final Scanner in = new Scanner(System.in);

    /**
     * Logo of the bot
     */
    private static final String LOGO = " _ __ _   _  __ _ _ __\n"
                                    + "| '__| | | |/ _` | '_ \\\n"
                                    + "| |  | |_| | (_| | | | |\n"
                                    + "|_|   \\__, |\\__,_|_| |_|\n"
                                    + "       __/ |\n"
                                    + "      |___/";
    private static final String SPACING = " ";
    /**
     * A decorative spacer between user inputs and outputs by the bot
     */
    private static final String DIVIDER = "____________________________________________________________";

    /**
     * Commands that tell the bot the event time or the due date of deadline
     */
    private static final String ENTRY_AT = "/at";
    private static final String ENTRY_BY = "/by";

    private static final String MESSAGE_TASK_ADDED = "Added task:\n    ";
    private static final String MESSAGE_WELCOME = "Hello from\n"
                                                + LOGO
                                                + "\nHow can I assist you? Type something below! :D";
    private static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";
    private static final String MESSAGE_NO_INPUT = "No input found! Please type <mode> + item";
    private static final String MESSAGE_OUT_OF_RANGE = "No such task found! Try a range of 1 to ";

    private static final String ERROR_WRONG_EVENT_FORMAT = "The input was wrong :( "
            + "Please type 'event <description> /at <time of event>'";
    private static final String ERROR_WRONG_DEADLINE_FORMAT = "The input was wrong :( "
            + "Please type 'deadline <description> /by <time of event>'";
    private static final String ERROR_WRONG_TODO_FORMAT = "The input was wrong :( Please type 'todo <description>'";

    private static final String TASK_PLURAL = "tasks";
    private static final String TASK_SINGLE = "task";

    private static final String PRINT_TASK_MESSAGE_FRONT = "Now you have ";
    private static final String PRINT_TASK_MESSAGE_BACK = " in the list.";
    private static final String PRINT_DONE_MESSAGE_FRONT = "I have marked\n     ";
    private static final String PRINT_DONE_MESSAGE_BACK = "\n as done!";

    /**
     * Commands for the different cases
     */
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_LIST = "list";

    /**
     * Max number of tasks that can be stored
     */
    private static final int TASK_SIZE = 100;

    private static Task[] tasks = new Task[TASK_SIZE];
    private static int taskCounter = 0;

    /**
     * Main entry point of the application and starts the interaction with user
     */
    public static void main(String[] args) {
        printWelcomeMessage();
        startChat();
        printByeMessage();
    }

    /**
     * Prints the goodbye message
     */
    private static void printByeMessage() {
        System.out.println(MESSAGE_GOODBYE);
    }

    /**
     * Prints the welcome message
     */
    private static void printWelcomeMessage() {
        System.out.println(MESSAGE_WELCOME);
    }

    /**
     * Prints a divider
     */
    private static void printDivider() {
        System.out.println(DIVIDER);
    }

    /**
     * Prints the list of tasks stored
     *
     * @param tasks array that stores the different types of tasks
     * @param taskCounter number of tasks in the array
     */
    private static void printList(Task[] tasks, int taskCounter) {
        for (int i = 0; i < taskCounter; i++) {
            System.out.println(tasks[i]);
        }
        printTaskNumber();
    }

    /**
     * Prints the number of tasks
     *
     */
    private static void printTaskNumber() {
        String task = TASK_PLURAL;
        if (taskCounter == 1) {
            task = TASK_SINGLE;
        }
        System.out.println(PRINT_TASK_MESSAGE_FRONT + taskCounter + SPACING + task + PRINT_TASK_MESSAGE_BACK);
    }

    /**
     * Scans for the description of the input task
     *
     * @param chatInput input of the user
     * @return description of task
     */
    private static String scanDescription(String chatInput) throws DukeException {
        String[] words = chatInput.split(SPACING);

        boolean isMissingDescription = (words.length <= 1);

        if (isMissingDescription) {
            throw new DukeException();
        }

        int spaceCount = 1;

        int startIdx = words[0].length() + spaceCount;
        int endIdx = 0;

        for (int i = 0; i < words.length; i++) {
            if (words[i].charAt(0) == '/') {
                break;
            }
            endIdx += words[i].length() + spaceCount;
        }
        return chatInput.substring(startIdx, endIdx - spaceCount);
    }

    /**
     * Scans for the event time/due date of deadline of task
     *
     * @param chatInput input of user
     * @return either the due date of deadline or event time
     */
    private static String getTimeOfEvent(String chatInput) throws DukeException {
        String[] words = chatInput.split(SPACING);

        int spaceCount = 1;
        int startIdx = 0;

        for (int i = 0; i < words.length; i++) {

            if (i == words.length - 1) {
                throw new DukeException();
            }

            if (checkAtEntry(words[i])) {
                startIdx += ENTRY_AT.length();
                break;
            } else if (checkByEntry(words[i])) {
                startIdx += ENTRY_BY.length();
                break;
            }

            startIdx += words[i].length() + spaceCount;
        }
        return chatInput.substring(startIdx + spaceCount);
    }

    /**
     * Scans for the command (keyword)
     *
     * @param chatInput input of user
     * @return the command to be executed
     */
    private static String scanKeyword(String chatInput) {
        String[] words = chatInput.toLowerCase().split(SPACING);
        return words[0];
    }

    /**
     * Searches for the task number in the command
     *
     * @param chatInput input of user
     * @return task number to be marked as done
     */
    private static int findTaskNumber(String chatInput) {
        int accountForArray = 1;
        String[] words = chatInput.split(SPACING);
        int taskNumber = Integer.parseInt(words[1]) - accountForArray;
        return taskNumber;
    }

    /**
     * Checks if input string is /at
     *
     * @param input input of entry
     * @return true if entry is /at
     */
    private static boolean checkAtEntry(String input) {
        if (input.equals(ENTRY_AT)) {
            return true;
        }
        return false;
    }

    /**
     * Checks if input string is /by
     *
     * @param input input of entry
     * @return true if entry is /by
     */
    private static boolean checkByEntry(String input) {
        if (input.equals(ENTRY_BY)) {
            return true;
        }
        return false;
    }

    /**
     * Checks if task is null
     *
     * @param item item at the point of array it is checking
     * @return true if item is added.
     */
    private static boolean checkIfTaskAdded(Task item) {
        return item != null;
    }

    /**
     * Adds a to-do item to the task list
     *
     * @param chatInput input of user
     * @return to-do item to be added to task list
     */
    private static void addToDoItem(String chatInput) {
        try {
            ToDo temp = new ToDo(scanDescription(chatInput));
            tasks[taskCounter] = temp;
            taskCounter++;
            System.out.println(MESSAGE_TASK_ADDED + temp);
            printTaskNumber();
        } catch (DukeException e) {
            System.out.println(ERROR_WRONG_TODO_FORMAT);
        }
    }

    /**
     * Adds an event item to the task list
     *
     * @param chatInput input of user
     * @return event item to be added to task list
     */
    private static void addEventItem(String chatInput) {
        try {
            Event temp = new Event(scanDescription(chatInput), getTimeOfEvent(chatInput));
            tasks[taskCounter] = temp;
            taskCounter++;
            System.out.println(MESSAGE_TASK_ADDED + temp);
            printTaskNumber();
        } catch (DukeException e) {
            System.out.println(ERROR_WRONG_EVENT_FORMAT);
        }
    }

    /**
     * Adds a deadline item to the task list
     *
     * @param chatInput input of user
     * @return deadline item to be added to task list
     */
    private static void addDeadlineItem(String chatInput) {
        try {
            Deadline temp = new Deadline(scanDescription(chatInput), getTimeOfEvent(chatInput));
            tasks[taskCounter] = temp;
            taskCounter++;
            System.out.println(MESSAGE_TASK_ADDED + temp);
            printTaskNumber();
        } catch (DukeException e) {
            System.out.println(ERROR_WRONG_DEADLINE_FORMAT);
        }
    }

    /**
     * Sets specific task in array as done
     *
     * @param chatInput input of user
     * @return the tasks array with specified item marked as done
     */
    private static void setTaskAsDone(String chatInput) {
        int taskIdx = findTaskNumber(chatInput);
        try {
            tasks[taskIdx].setDone();
            System.out.println(PRINT_DONE_MESSAGE_FRONT + tasks[taskIdx] + PRINT_DONE_MESSAGE_BACK);
        } catch (NullPointerException e) {
            System.out.println(MESSAGE_OUT_OF_RANGE + taskCounter);
        }
    }

    /**
     * Executes the main chatting function
     */
    private static void startChat() {
        while (true) {
            printDivider();
            String chatInput = in.nextLine();
            printDivider();
            switch (scanKeyword(chatInput)) {
            case COMMAND_BYE:
                return;
            case COMMAND_LIST:
                printList(tasks, taskCounter);
                break;
            case COMMAND_TODO:
                addToDoItem(chatInput);
                break;
            case COMMAND_EVENT:
                addEventItem(chatInput);
                break;
            case COMMAND_DEADLINE:
                addDeadlineItem(chatInput);
                break;
            case COMMAND_DONE:
                setTaskAsDone(chatInput);
                break;
            default:
                System.out.println(MESSAGE_NO_INPUT);
                break;
            }
        }
    }
}
