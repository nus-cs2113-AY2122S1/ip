package wutdequack.terminator.ui;

import wutdequack.terminator.objects.task.Task;
import wutdequack.terminator.parser.Parser;
import wutdequack.terminator.tasklist.TaskList;

import static wutdequack.terminator.common.MagicValues.tasksList;
import static wutdequack.terminator.common.Messages.ADD_TASK_COUNTER_MESSAGE;
import static wutdequack.terminator.common.Messages.ADD_TASK_MESSAGE;
import static wutdequack.terminator.common.Messages.ADD_TASK_QUERY_MESSAGE;
import static wutdequack.terminator.common.Messages.COMPLETED_TASK_MESSAGE;
import static wutdequack.terminator.common.Messages.ERROR_CONFUSED_MESSAGE;
import static wutdequack.terminator.common.Messages.ERROR_INVALID_TASK_COMMAND;
import static wutdequack.terminator.common.Messages.ERROR_INVALID_TODO_TASK_MESSAGE;
import static wutdequack.terminator.common.Messages.ERROR_MISSING_DATETIME_MESSAGE;
import static wutdequack.terminator.common.Messages.ERROR_MISSING_PARAMS_MESSAGE;
import static wutdequack.terminator.common.Messages.FILE_DATABASE_EXIST_MESSAGE;
import static wutdequack.terminator.common.Messages.FILE_DONT_EXIST_MESSAGE;
import static wutdequack.terminator.common.Messages.FILE_PERMISSION_ERROR_MESSAGE;
import static wutdequack.terminator.common.Messages.FILTER_TASKS_MESSAGE;
import static wutdequack.terminator.common.Messages.LOGO;
import static wutdequack.terminator.common.Messages.GREETING_MESSAGE;
import static wutdequack.terminator.common.Messages.GREETING_QUERY_MESSAGE;
import static wutdequack.terminator.common.Messages.GREETING_QUIT_MESSAGE;
import static wutdequack.terminator.common.Messages.ERROR_NUMBER_INVALID_MESSAGE;
import static wutdequack.terminator.common.Messages.ERROR_INDEX_OUT_OF_BOUNDS_MESSAGE;

import static wutdequack.terminator.common.MagicValues.USER_FORMATTING;
import static wutdequack.terminator.common.MagicValues.TERMINATOR_FORMATTING;
import static wutdequack.terminator.common.MagicValues.USER_STRING;
import static wutdequack.terminator.common.MagicValues.TERMINATOR_STRING;
import static wutdequack.terminator.common.Messages.POST_CRUD_QUERY_MESSAGE;
import static wutdequack.terminator.common.Messages.UPDATED_TASK_MESSAGE;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class TextUi {
    private TaskList taskList;
    private final Scanner in;
    private final PrintStream out;

    public TextUi() {
        this(System.in, System.out);
    }

    public TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
        this.taskList = new TaskList();
    }

    /**
     * Formats printed messages with the appropriate headings.
     * If option TERMINATOR_FORMATTING is selected, [The Terminator] will prepend the msg.
     * If option USER_FORMATTING is selected, [User] will prepend the msg.
     * @param msg Message to be printed.
     * @param option TERMINATOR_FORMATTING or USER_FORMATTING.
     * @return String with prepended heading.
     */
    private static String formatWithHeading(String msg, Integer option) {
        String prepend = "";
        switch (option) {
        case TERMINATOR_FORMATTING:
            prepend = TERMINATOR_STRING;
            break;
        case USER_FORMATTING:
            prepend = USER_STRING;
            break;
        default:
            break;
        }
        return String.format("%s: %s", prepend, msg);
    }

    /**
     * Prints the welcome message to the user.
     */
    public void printHelloMessage() {
        out.println(LOGO);
        out.println(formatWithHeading(GREETING_MESSAGE, TERMINATOR_FORMATTING));
        out.println(formatWithHeading(GREETING_QUERY_MESSAGE, TERMINATOR_FORMATTING));
        out.println(GREETING_QUIT_MESSAGE);
    }

    /**
     * Prints Invalid User Number message to the user.
     */
    public void printInvalidUserNumberMessage() {
        out.println(formatWithHeading(ERROR_NUMBER_INVALID_MESSAGE, TERMINATOR_FORMATTING));
    }

    /**
     * Prints error message if requested task to access is out of bounds.
     */
    public void printOutOfBoundsMessage() {
        out.println(formatWithHeading(ERROR_INDEX_OUT_OF_BOUNDS_MESSAGE, TERMINATOR_FORMATTING));
    }

    /**
     * Prints response back to user of task that is modified.
     * @param taskString The String of the Task.
     */
    public void printUpdateMessage(String taskString) {
        out.println(COMPLETED_TASK_MESSAGE + System.lineSeparator() +
                taskString);
        out.println(formatWithHeading(POST_CRUD_QUERY_MESSAGE, TERMINATOR_FORMATTING));
    }

    /**
     * Prints response back to user of task that is deleted.
     * @param taskString The String of the Task.
     */
    public void printDeleteMessage(String taskString) {
        out.println(UPDATED_TASK_MESSAGE  + System.lineSeparator() +
                taskString);
        out.println(formatWithHeading(POST_CRUD_QUERY_MESSAGE, TERMINATOR_FORMATTING));
    }

    /**
     * Prints the tasks in the Task list with formatting.
     */
    public void printTasks() {
        out.println("Here is a list of taskings:");
        out.println("===================================================");
        for (int i = 0; i < taskList.getTasksList().size(); ++i) {
            Task currentTask = taskList.getTasksList().get(i);
            // If the current task is completed, check the completion status
            System.out.printf("%d.%s" + System.lineSeparator(), i+1, currentTask.toString());
        }
        out.println(formatWithHeading("Anything else?", TERMINATOR_FORMATTING));
    }

    /**
     * Prints response back to user of task that is added.
     * @param newTask The task that is added by the user.
     */
    public void printAddTaskMessage(Task newTask) {
        out.println(formatWithHeading(ADD_TASK_MESSAGE + System.lineSeparator() +
                newTask.toString(), TERMINATOR_FORMATTING));
        out.println(formatWithHeading(String.format(ADD_TASK_COUNTER_MESSAGE,
                taskList.getTasksList().size()), TERMINATOR_FORMATTING));
        out.println(formatWithHeading(ADD_TASK_QUERY_MESSAGE, TERMINATOR_FORMATTING));
    }

    /**
     * Prints message to user informing them that their input lack the required parameters.
     */
    public void printMissingParametersMessage(){
        out.println(formatWithHeading(ERROR_MISSING_PARAMS_MESSAGE, TERMINATOR_FORMATTING));
    }

    /**
     * Prints message to user informing them that they are missing the date/time field.
     */
    public void printDateTimeMessage(){
        out.println(formatWithHeading(ERROR_MISSING_DATETIME_MESSAGE, TERMINATOR_FORMATTING));
    }

    /**
     * Prints message to user informing them that their todo task is invalid.
     */
    public void printInvalidToDoMessage(){
        out.println(formatWithHeading(ERROR_INVALID_TODO_TASK_MESSAGE, TERMINATOR_FORMATTING));
    }

    /**
     * Prints message to user informing them that their file to read from does not exist.
     */
    public void printFileDontExistMessage(){
        out.println(formatWithHeading(FILE_DONT_EXIST_MESSAGE, TERMINATOR_FORMATTING));
    }

    /**
     * Prints message to user informing them that a file has been created for them to read from.
     */
    public void printFileCreationMessage(){
        out.println(formatWithHeading(FILE_DATABASE_EXIST_MESSAGE, TERMINATOR_FORMATTING));
    }

    /**
     * Prints message to user informing them that file could not be created.
     */
    public void printFilePermissionsErrorMessage(){
        out.println(formatWithHeading(FILE_PERMISSION_ERROR_MESSAGE, TERMINATOR_FORMATTING));
    }

    /**
     * Prints Goodbye message to user.
     */
    public void printGoodByeMessage() {
        out.println(formatWithHeading("Hasta la vista.", TERMINATOR_FORMATTING));
        out.println(formatWithHeading("I will be back.", TERMINATOR_FORMATTING));
        out.println(formatWithHeading("Program Terminating in...", TERMINATOR_FORMATTING));
        // Stops at 2 intentionally
        for (int i = 5; i > 1; --i) {
            out.println(formatWithHeading(Integer.toString(i), TERMINATOR_FORMATTING));
        }
    }

    /**
     * Prompts the user for input and returns the received input.
     * @return Line entered by the user.
     */
    public String getUserInput() {
        System.out.print(formatWithHeading("", USER_FORMATTING));
        String input = in.nextLine();
        // Keep getting input until its not empty
        while (input.trim().isEmpty()){
            input = in.nextLine();
        }
        return input;
    }

    /**
     * Prints to the screen that you don't understand the commands.
     */
    public void printUnknownCommandMessage(){
        out.println(formatWithHeading(ERROR_CONFUSED_MESSAGE, TERMINATOR_FORMATTING));
        out.println(formatWithHeading(ERROR_INVALID_TASK_COMMAND, TERMINATOR_FORMATTING));
    }

    /**
     * Finds and prints tasks given user's request.
     * @param userLine Line of input from the user.
     */
    public void findTasks(String userLine) {
        String searchTerm = new Parser().getSearchTermFromInput(userLine);
        out.println(formatWithHeading(String.format(FILTER_TASKS_MESSAGE, searchTerm), TERMINATOR_FORMATTING));
        tasksList
                .stream()
                .filter((t) -> t.toString().contains(searchTerm))
                .forEach(out::println);
        out.println(formatWithHeading(POST_CRUD_QUERY_MESSAGE, TERMINATOR_FORMATTING));
    }
}