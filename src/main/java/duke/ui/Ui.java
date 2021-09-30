package duke.ui;
import java.util.Scanner;
import duke.task.Task;
import java.util.ArrayList;

public class Ui {
    
    /**
     *
     */
    private static final String LIST_MESSAGE = "Here are the tasks in your list:";
    private static final String NO_TASK_FOUND_MESSAGE = "There are no tasks containing that keyword!";
    private static final String FOUND_MATCHING_TASK_MESSAGE = "Here are the matching tasks in your list:";
    public static final String LINE = "____________________________________________________________";
    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String INVALID_FILE_INPUT_MESSAGE = "Input format within file is wrong!";
    private static final String NEW_FOLDER_MESSAGE = "New folder created for storage";
    private static final String NEW_TEXT_FILE_MESSAGE = "New text file created for storage";
    private static final String EMPTY_LIST_MESSAGE = "No items added!";
    private static final String TASK_IS_ALREADY_DONE_MESSAGE = "That task is already done!";
    public static final String MARKED_TASK_AS_DONE_MESSAGE = "Nice! I've marked this task as done:";
    public static final String TASK_DOES_NOT_EXIST_MESSAGE = "That task does not exist!";
    public static final String TASK_ADDED_MESSAGE = "Got it. I've added this task: ";
    public static final String EMPTY_TODO_MESSAGE = "☹ OOPS!!! The description of a todo cannot be empty.";
    public static final String WRONG_DEADLINE_FORMAT_MESSAGE = "Incorrect format for entering deadline!";
    public static final String EMPTY_DEADLINE_MESSAGE = "☹ OOPS!!! The description of a deadline cannot be empty.";
    public static final String WRONG_EVENT_FORMAT_MESSAGE = "Incorrect format for entering event!";
    public static final String EMPTY_EVENT_MESSAGE = "☹ OOPS!!! The description of an event cannot be empty.";
    public static final String UNKNOWN_COMMAND_MESSAGE = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String WELCOME_MESSAGE = "Hello! I'm Duke What can I do for you?";
    public static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";
    public static final String SOMETHING_WENT_WRONG_MESSAGE = "Something went wrong: ";
    public static final String WRONG_COMMAND_FORMAT_MESSAGE = "Wrong command format!";
    
    protected Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }

    /**
     * returns the user input
     *
     * @return the user input
     */
    public String readCommand() {
        return in.nextLine();
    }

    /**
     * Prints out a line
     */
    public void printLine() {
        System.out.println(LINE);
    }

    /**
     * Prints out task found message
     */
    public void printFoundTaskMessage() {
        System.out.println(LINE + LINE_SEPARATOR + FOUND_MATCHING_TASK_MESSAGE);
    }

    /**
     * Prints out no task found message
     */
    public void printNoTaskFoundMessage() {
        System.out.println(LINE + LINE_SEPARATOR + NO_TASK_FOUND_MESSAGE + LINE_SEPARATOR + LINE);
    }

    /**
     * Prints out the task and its corresponding index
     *
     * @param task the task
     * @param index the index of the task
     */
    public void printTask(Task task, int index) {
        System.out.println(index + ". " + task);
    }

    /**
     * Prints out the task that is removed and the number of remaining tasks in the ArrayList
     *
     * @param task the task which is removed
     * @param numTasks the number of tasks in the ArrayList after removal
     */
    public void printRemoveTaskMessge(Task task, int numTasks) {
        System.out.format(LINE + LINE_SEPARATOR + "Noted. I've removed this task:  " + task + LINE_SEPARATOR
        + "Now you have %d tasks in the list." + LINE_SEPARATOR + LINE + LINE_SEPARATOR, numTasks);
    }

    /**
     * Prints out all the tasks in the ArrayList
     *
     * @param list the ArrayList of tasks
     */
    public void printTasks(ArrayList<Task> list) {
        System.out.println(LINE);
        System.out.println(LIST_MESSAGE);
        for (int i = 0; i < list.size(); i += 1) {
            System.out.println((i+1) + ". " + list.get(i));
        }
        System.out.println(LINE);
    }

    /**
     * Prints out the error message when a wrong command is input by the user
     */
    public void printWrongCommandFormatMessage() {
        System.out.println(LINE + LINE_SEPARATOR + WRONG_COMMAND_FORMAT_MESSAGE + LINE_SEPARATOR + LINE);
    }

    /**
     * Prints out the error message when the input within the file is wrong
     */
    public void printInvalidFileInputMessage() {
        System.out.println(LINE + LINE_SEPARATOR + INVALID_FILE_INPUT_MESSAGE + LINE_SEPARATOR + LINE);
    }

    /**
     * Prints out the message to inform user that a new folder has been created
     */
    public void printNewFolderMessage() {
        System.out.println(LINE + LINE_SEPARATOR + NEW_FOLDER_MESSAGE + LINE_SEPARATOR + LINE);
    }

    /**
     * Prints out the message to inform user that a new text file has been created
     */
    public void printNewTextFileMessage() {
        System.out.println(NEW_TEXT_FILE_MESSAGE);
    }

    /**
     * Prints out the message when the list is empty
     */
    public void printEmptyListMessage() {
        System.out.println(LINE + LINE_SEPARATOR + EMPTY_LIST_MESSAGE + LINE_SEPARATOR + LINE);
    }

    /**
     * Prints out the message to inform user that the task is already done
     */
    public void printTaskIsDoneMessage() {
        System.out.println(LINE + LINE_SEPARATOR + TASK_IS_ALREADY_DONE_MESSAGE + LINE_SEPARATOR + LINE);
    }

    /**
     * Prints out the message to inform the user that the task has been marked as done
     *
     * @param task the task which is marked as done
     */
    public void printMarkTaskAsDoneMessage(Task task) {
        System.out.println(LINE + LINE_SEPARATOR + MARKED_TASK_AS_DONE_MESSAGE + task + LINE_SEPARATOR + LINE);
    }

    /**
     * Prints out the error message when the user tries to access a task which does not exist
     */
    public void printTaskDoesNotExistMessage() {
        System.out.println(LINE + LINE_SEPARATOR + TASK_DOES_NOT_EXIST_MESSAGE + LINE_SEPARATOR + LINE);
    }

    /**
     * Prints out the message to inform user that a task has been added to the TaskList as well as the size of
     * the TaskList
     *
     * @param list the ArrayList of tasks
     */
    public void printTaskAddedMessage(ArrayList<Task> list) {
        System.out.println(LINE + LINE_SEPARATOR + TASK_ADDED_MESSAGE + list.size() + ". " + list.get(list.size() - 1));
        System.out.format("Now you have %d tasks in the list." + LINE_SEPARATOR + LINE + LINE_SEPARATOR, list.size());
    }

    /**
     * Prints out the error message when user does not provide the name of the todo task
     */
    public void printEmptyTodoMessage() {
        System.out.println(LINE + LINE_SEPARATOR + EMPTY_TODO_MESSAGE + LINE_SEPARATOR + LINE);
    }

    /**
     * Prints out the error message when user inputs the wrong deadline format command
     */
    public void printWrongDeadlineFormatMessage() {
        System.out.println(LINE + LINE_SEPARATOR + WRONG_DEADLINE_FORMAT_MESSAGE + LINE_SEPARATOR + LINE);
    }

    /**
     * Prints out the error message when user does not provide the name of the deadline task
     */
    public void printEmptyDeadlineMessage() {
        System.out.println(LINE + LINE_SEPARATOR + EMPTY_DEADLINE_MESSAGE + LINE_SEPARATOR + LINE);
    }

    /**
     * Prints out the error message when the user inputs the wrong event format command
     */
    public void printWrongEventFormatMessage() {
        System.out.println(LINE + LINE_SEPARATOR + WRONG_EVENT_FORMAT_MESSAGE + LINE_SEPARATOR + LINE);
    }

    /**
     * Prints out the error message when the user does not provide the name of the event task
     */
    public void printEmptyEventMessage() {
        System.out.println(LINE + LINE_SEPARATOR + EMPTY_EVENT_MESSAGE + LINE_SEPARATOR + LINE);
    }

    /**
     * Prints out the error message when the user inputs an unknown command
     */
    public void printUnknownCommandMessage() {
        System.out.println(LINE + LINE_SEPARATOR + UNKNOWN_COMMAND_MESSAGE + LINE_SEPARATOR + LINE);
    }

    /**
     * Prints out the welcome message
     */
    public void printWelcomeMessage() {
        System.out.println(LINE + LINE_SEPARATOR + WELCOME_MESSAGE + LINE_SEPARATOR + LINE);
    }

    /**
     * Prints out the goodbye message
     */
    public void printGoodbyeMessage() {
        System.out.println(LINE + LINE_SEPARATOR + GOODBYE_MESSAGE + LINE_SEPARATOR + LINE);
    }

    /**
     * Prints out the error message when an exception as occured
     *
     * @param e The exception
     */
    public void printSomethingWentWrongMessage(Exception e) {
        System.out.println(SOMETHING_WENT_WRONG_MESSAGE
                + e.getMessage() + LINE_SEPARATOR + LINE);
    }



}
