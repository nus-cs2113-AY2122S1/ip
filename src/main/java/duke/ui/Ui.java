package duke.ui;
import java.util.Scanner;
import duke.task.Task;
import java.util.ArrayList;

public class Ui {
    
    public static final String LINE = "____________________________________________________________";
    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String INVALID_FILE_INPUT_MESSAGE = "Input format within file is wrong!";
    private static final String NEW_FOLDER_MESSAGE = "New folder created for storage";
    private static final String NEW_TEXT_FILE_MESSAGE = "New text file created for storage";
    private static final String EMPTY_LIST_MESSAGE = "No items added!";
    private static final String TASK_IS_ALREADY_DONE_MESSAGE = "That task is already done!";
    public static final String MARKED_TASK_AS_DONE_MESSAGE = "Nice! I've marked this task as done:";
    public static final String ENTER_A_NUMBER_AFTER_DONE_MESSAGE = "Please enter a number after 'done'!";
    public static final String TASK_DOES_NOT_EXIST_MESSAGE = "That task does not exist!";
    public static final String ENTER_A_NUMBER_AFTER_DELETE_MESSAGE = "Please enter a number after 'delete'!";
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

    public String readCommand() {
        return in.nextLine();
    }

    public void printRemoveTaskMessge(Task task, int numTasks) {
        System.out.format(LINE + LINE_SEPARATOR + "Noted. I've removed this task:  " + task + LINE_SEPARATOR
        + "Now you have %d tasks in the list." + LINE_SEPARATOR + LINE + LINE_SEPARATOR, numTasks);
    }

    public void printTasks(ArrayList<Task> list) {
        System.out.println(LINE);
        for (int i = 0; i < list.size(); i += 1) {
            System.out.println((i+1) + ". " + list.get(i));
        }
        System.out.println(LINE);
    }

    public void printWrongCommandFormatMessage() {
        System.out.println(LINE + LINE_SEPARATOR + WRONG_COMMAND_FORMAT_MESSAGE + LINE_SEPARATOR + LINE);
    }

    public void printInvalidFileInputMessage() {
        System.out.println(LINE + LINE_SEPARATOR + INVALID_FILE_INPUT_MESSAGE + LINE_SEPARATOR + LINE);
    }

    public void printNewFolderMessage() {
        System.out.println(NEW_FOLDER_MESSAGE + LINE_SEPARATOR + LINE);
    }

    public void printNewTextFileMessage() {
        System.out.println(NEW_TEXT_FILE_MESSAGE + LINE_SEPARATOR + LINE);
    }

    public void printEmptyListMessage() {
        System.out.println(LINE + LINE_SEPARATOR + EMPTY_LIST_MESSAGE + LINE_SEPARATOR + LINE);
    }

    public void printTaskIsDoneMessage() {
        System.out.println(LINE + LINE_SEPARATOR + TASK_IS_ALREADY_DONE_MESSAGE + LINE_SEPARATOR + LINE);
    }

    public void printMarkTaskAsDoneMessage(Task task) {
        System.out.println(LINE + LINE_SEPARATOR + MARKED_TASK_AS_DONE_MESSAGE + task + LINE_SEPARATOR + LINE);
    }

    public void printWrongDoneFormatMessage() {
        System.out.println(LINE + LINE_SEPARATOR + ENTER_A_NUMBER_AFTER_DONE_MESSAGE + LINE_SEPARATOR + LINE);
    }

    public void printTaskDoesNotExistMessage() {
        System.out.println(LINE + LINE_SEPARATOR + TASK_DOES_NOT_EXIST_MESSAGE + LINE_SEPARATOR + LINE);
    }

    public void printEnterNumberAfterTaskMessage() {
        System.out.println(LINE + LINE_SEPARATOR + ENTER_A_NUMBER_AFTER_DELETE_MESSAGE + LINE_SEPARATOR + LINE);
    }

    public void printTaskAddedMessage(ArrayList<Task> list) {
        System.out.println(LINE + LINE_SEPARATOR + TASK_ADDED_MESSAGE + list.size() + ". " + list.get(list.size() - 1));
        System.out.format("Now you have %d tasks in the list." + LINE_SEPARATOR + LINE + LINE_SEPARATOR, list.size());
    }

    public void printEmptyTodoMessage() {
        System.out.println(LINE + LINE_SEPARATOR + EMPTY_TODO_MESSAGE + LINE_SEPARATOR + LINE);
    }

    public void printWrongDeadlineFormatMessage() {
        System.out.println(LINE + LINE_SEPARATOR + WRONG_DEADLINE_FORMAT_MESSAGE + LINE_SEPARATOR + LINE);
    }

    public void printEmptyDeadlineMessage() {
        System.out.println(LINE + LINE_SEPARATOR + EMPTY_DEADLINE_MESSAGE + LINE_SEPARATOR + LINE);
    }

    public void printWrongEventFormatMessage() {
        System.out.println(LINE + LINE_SEPARATOR + WRONG_EVENT_FORMAT_MESSAGE + LINE_SEPARATOR + LINE);
    }

    public void printEmptyEventMessage() {
        System.out.println(LINE + LINE_SEPARATOR + EMPTY_EVENT_MESSAGE + LINE_SEPARATOR + LINE);
    }

    public void printUnknownCommandMessage() {
        System.out.println(LINE + LINE_SEPARATOR + UNKNOWN_COMMAND_MESSAGE + LINE_SEPARATOR + LINE);
    }

    public void printWelcomeMessage() {
        System.out.println(LINE + LINE_SEPARATOR + WELCOME_MESSAGE + LINE_SEPARATOR + LINE);
    }

    public void printGoodbyeMessage() {
        System.out.println(LINE + LINE_SEPARATOR + GOODBYE_MESSAGE + LINE_SEPARATOR + LINE);
    }

    public void printSomethingWentWrongMessage(Exception e) {
        System.out.println(SOMETHING_WENT_WRONG_MESSAGE
                + e.getMessage() + LINE_SEPARATOR + LINE);
    }



}
