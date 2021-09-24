package alfred.ui;

import alfred.task.Task;
import alfred.task.TaskList;

import java.util.Scanner;

public class TextUi {
    public static final String LINE = "____________________________________________________________\n";
    private static final String LOGO =
            " **********************************\n" +
            " *     _    _  __              _  *\n" +
            " *    / \\  | |/ _|_ __ ___  __| | *\n" +
            " *   / _ \\ | | |_| '__/ _ \\/ _` | *\n" +
            " *  / ___ \\| |  _| | |  __/ (_| | *\n" +
            " * /_/   \\_\\_|_| |_|  \\___|\\__,_| *\n" +
            " **********************************\n";

    private final Scanner scanner;

    /**
     * This constructor creates a command line TextUi for interaction with the user on the front
     * end. It instantiates a new System.in Scanner to read user input, and prints the initialisation
     * message.
     */
    public TextUi() {
        scanner = new Scanner(System.in);
        initMessage();
    }

    /**
     * This method formats text block outputs by the chat-bot by prepending and appending lines on the
     * text to be printed.
     * @param s Text to be printed
     */
    private static void printMessageTemplate(String s) {
        System.out.println(LINE + s + LINE);
    }

    /**
     * This method obtains the user input using the initialised scanner.
     * @return String This returns the user input to be processed
     */
    public String getUserInput() {
        return scanner.nextLine();
    }

    /**
     * This method prints the initialisation message.
     */
    public static void initMessage() {
        String messageString = " Welcome back, Master Wayne.\n" + " How may I be of service to you?\n";
        System.out.println(LOGO + "\n");
        printMessageTemplate(messageString);
    }

    /**
     * This method prints the exit message.
     */
    public static void shutdownMessage() {
        printMessageTemplate(" Very well sir, I shall leave you to your own devices.\n");
    }

    /**
     * This method prints the Task completion message along with its index in the list and description.
     * @param index Index in the TaskList (not displayed list)
     * @param task Completed Task
     */
    public static void completeTaskMessage(int index, Task task) {
        int listIndex = index + 1;
        String messageString =
                "Duly noted on completion of task, sir.\n" + "    " + listIndex + "." + task.toString() + "\n";
        printMessageTemplate(messageString);
    }

    /**
     * This method prints out all tasks, with enumeration, if there are tasks. If not, it will inform
     * the user that the TaskList is empty.
     * @param taskList The current TaskList to be printed
     */
    public static void listTasks(TaskList taskList) {
        int numberOfTasks = taskList.getSize();
        System.out.print(LINE);
        if (numberOfTasks == 0) {
            System.out.println(" Your schedule is clear, Master Wayne.");
        } else {
            System.out.println(" Your tasks, sir:");
            printEnumeratedTasks(taskList, numberOfTasks);
        }
        System.out.println(LINE);
    }

    /**
     * This method prints out all tasks that match the find query, with enumeration. If not, it will inform user
     * about the failed query.
     * @param filteredList The filtered TaskList to be printed
     */
    public static void printFoundTasks(TaskList filteredList) {
        int numberOfTasks = filteredList.getSize();
        System.out.print(LINE);
        if (numberOfTasks == 0) {
            System.out.println(" There appears to be no task by that query sir.");
        } else {
            System.out.println(" I've procured the following tasks based on that query, sir:");
            printEnumeratedTasks(filteredList, numberOfTasks);
        }
        System.out.println(LINE);
    }

    /**
     * This method iterates through the TaskList, enumerates the Tasks and prints them out.
     * @param taskList TaskList to be enumerated and printed
     * @param numberOfTasks Number of Tasks in the TaskList for iteration
     */
    private static void printEnumeratedTasks(TaskList taskList, int numberOfTasks) {
        for (int i = 0; i < numberOfTasks; i++) {
            System.out.println(" " + (i + 1) + "." + taskList.getTask(i).toString());
        }
    }

    /**
     * This method prints out the success message for adding a Task to the TaskList, and contains the
     * Task Description and number of Tasks currently in the TaskList.
     * @param t The Task added
     * @param numberOfTasks The current number of Tasks
     */
    public static void addTaskMessage(Task t, int numberOfTasks) {
        String messageString = " I shall put this in your schedule, Master Wayne: \n    " + t.toString() + "\n" +
                " Sir, the number of Tasks you have scheduled currently amounts to " + numberOfTasks + "." + "\n";
        printMessageTemplate(messageString);
    }

    /**
     * This method prints out the success message for deleting a Task from the TaskList, and contains the
     * Task Description and number of Tasks currently in the TaskList.
     * @param t The Task deleted
     * @param numberOfTasks The current number of Tasks
     */
    public static void deleteTaskMessage(Task t, int numberOfTasks, int taskIndex) {
        String messageString =
                " Very well, Master Wayne, I shall remove this: \n    " + (taskIndex + 1) + "." + t.toString() + "\n" +
                " Sir, the number of Tasks you have scheduled currently amounts to " + numberOfTasks + "." + "\n";
        printMessageTemplate(messageString);
    }

    // Error Messages

    /**
     * This method prints a message to show current command cannot be identified and is invalid.
     */
    public static void invalidCommandMessage() {
        printMessageTemplate(" Perhaps you could rephrase that in a way us civilians could comprehend.\n");
    }

    /**
     * This method prints a message denoting errors with the storage save file.
     */
    public static void fileErrorMessage() {
        printMessageTemplate(" There appears to be a problem with the save file, sir.");
    }

    /**
     * This method prints a message denoting that an Event/Deadline does not have a specified description.
     */
    public static void emptyDescriptionMessage() {
        printMessageTemplate(" Master Wayne, if you do not specify your task, I'm afraid I cannot note it down.\n");
    }

    /**
     * This method prints a message denoting that Add/Delete commands do not specify any Task index.
     */
    public static void missingIndexMessage() {
        String messageString = " And what task number are you specifying, Master Wayne?\n";
        printMessageTemplate(messageString);
    }

    /**
     * This method prints a message denoting that a Task index specified is not a number.
     */
    public static void invalidIndexMessage() {
        String messageString = " Sir, the bats must've gone to your head.\n" + " Do try again with a number that " +
                "identifies your task.\n";
        printMessageTemplate(messageString);
    }

    /**
     * This method prints a message denoting that a Task index specified does not exist, and informs the user
     * to try again with an index within the given range.
     * @param numberOfTasks The current number/range of Tasks for which the user can input
     */
    public static void uninitialisedTaskIndexMessage(int numberOfTasks) {
        String noTaskMessageString = " Sir, you have nothing scheduled.\n";
        String singularMessageString = " Sir, might I remind you that you only have 1 task.\n" +
                " Try again with a number in that range.\n";
        String pluralMessageString = " Sir, might I remind you that you only have " + numberOfTasks + " tasks.\n" +
                " Try again with a number in that range.\n";
        switch (numberOfTasks) {
        case 0:
            printMessageTemplate(noTaskMessageString);
            break;
        case 1:
            printMessageTemplate(singularMessageString);
            break;
        default:
            printMessageTemplate(pluralMessageString);
        }
    }

    /**
     * This method prints a message denoting that a date is missing for Event/Deadline.
     */
    public static void missingDateMessage() {
        printMessageTemplate(" Is there not a date for your so-called deadline or event, sir?\n");
    }

    /**
     * This method prints a message when user does not specify a query term.
     */
    public static void missingQueryMessage() {
        printMessageTemplate(" Master Wayne, if you don't specify a task to find, \n" +
                " I'm afraid I cannot help you.\n");
    }

    /**
     * This method prints a message to inform user to format date properly.
     */
    public static void invalidDateMessage() {
        String messageString = " Master Wayne, to which planet does this date belong to?\n" +
                " Please let me know soonest in the following forms:\n" +
                " [DD-MM-YYYY] or [DD/MM/YYYY] or [DDMMYYYY]\n";
        printMessageTemplate(messageString);
    }

    /**
     * This method prints a message informing the user that a save file has been created by the chat-bot.
     */
    public static void createNewFileMessage() {
        printMessageTemplate(" A new file has been created for Alfred, by Alfred.\n");
    }
}
