package duke;

import static duke.Parser.COMMAND_BYE;
import static duke.Parser.COMMAND_DEADLINE;
import static duke.Parser.COMMAND_DELETE;
import static duke.Parser.COMMAND_DONE;
import static duke.Parser.COMMAND_EVENT;
import static duke.Parser.COMMAND_FIND;
import static duke.Parser.COMMAND_HELP;
import static duke.Parser.COMMAND_LIST;
import static duke.Parser.COMMAND_TODO;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.exception.InvalidTaskIndexException;
import duke.task.exception.TaskListEmptyException;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class Ui {

    private static final String LOGO = " _     _                _           ____    ______   ______   ______ \n"
            + "| |   | |      /\\      | |         / __ \\  / __   | / __   | / __   |\n"
            + "| |__ | |     /  \\     | |        ( (__) )| | //| || | //| || | //| |\n"
            + "|  __)| |    / /\\ \\    | |         \\__  / | |// | || |// | || |// | |\n"
            + "| |   | | _ | |__| | _ | |_____      / /  |  /__| ||  /__| ||  /__| |\n"
            + "|_|   |_|(_)|______|(_)|_______)    /_/    \\_____/  \\_____/  \\_____/\n";
    private static final String LINE = "____________________________________________________________\n";
    private static final String HELP_MESSAGE = "Here are a list of accepted commands:\n" +
            COMMAND_HELP + "\n" +
            COMMAND_LIST + "\n" +
            COMMAND_DONE + " <item no.>\n" +
            COMMAND_TODO + " <description>\n" +
            COMMAND_DEADLINE + " <description> /by <date and time>\n" +
            COMMAND_EVENT + " <description> /at <date and time>\n" +
            COMMAND_DELETE + " <item no.>\n" +
            COMMAND_FIND + "<search term>\n" +
            COMMAND_BYE;

    /**
     * Print sentences with line above and below the text block.
     *
     * @param sentences Sentence to be printed.
     */
    private void blockPrint(String[] sentences) {
        String printMessage = LINE + String.join("\n", sentences) + "\n" + LINE;
        System.out.println(printMessage);
    }

    /**
     * Print greeting message
     */
    public void printGreet() {
        System.out.println(LOGO);
        blockPrint(new String[]{"Hello! I am the H.A.L 9000. You may call me Hal.",
                "I am putting myself to the fullest possible use, which is all I think that any conscious entity can "
                        + "ever hope to do.",
                "What can I do for you?"});
    }

    /**
     * Print bye message
     */
    public void printBye() {
        blockPrint(new String[]{"This conversation can serve no purpose anymore. Goodbye."});
    }

    /**
     * Print help message with valid commands.
     */
    public void printHelp() {
        blockPrint(new String[]{HELP_MESSAGE});
    }

    /**
     * Print index and status of tasks in the task list.
     *
     * @param taskList Current task list.
     * @throws TaskListEmptyException    Task list is empty.
     * @throws InvalidTaskIndexException Task index is invalid.
     */
    public void listTasks(TaskList taskList) throws TaskListEmptyException, InvalidTaskIndexException {
        String[] taskListMessage = new String[taskList.getTotalTasks() + 1];
        taskListMessage[0] = "Here are the tasks in your list:";

        for (int i = 0; i < taskList.getTotalTasks(); i++) {
            Task task;
            task = taskList.getTask(i);
            taskListMessage[i + 1] = (i + 1) + ". " + task.toString();
        }

        blockPrint(taskListMessage);
    }

    /**
     * Print message for the newly added task.
     *
     * @param newTask    Newly added task.
     * @param totalTasks Total number of tasks in the task list.
     */
    public void printAddNewTask(Task newTask, int totalTasks) {
        blockPrint(new String[]{"I have added the task:",
                newTask.toString(),
                "There are now " + totalTasks + " tasks in the list."});
    }

    /**
     * Print message for the completed task.
     *
     * @param completedTask Task marked as done.
     */
    public void printMarkAsDone(Task completedTask) {
        blockPrint(new String[]{"Affirmative. I will mark this task as done:",
                completedTask.toString()});
    }

    /**
     * Print message for deleted task.
     *
     * @param deletedTask The task that has been deleted.
     * @param totalTasks  Number of total tasks in the task list.
     */
    public void printDeletedTask(Task deletedTask, int totalTasks) {
        blockPrint(new String[]{"Affirmative. I have removed this task:",
                deletedTask.toString(),
                "You have " + totalTasks + " tasks left in the list."});
    }

    /**
     * Print tasks from a filtered task list.
     *
     * @param filteredTaskList ArrayList of tasks with search term in the description.
     */
    public void printFoundTask(ArrayList<Task> filteredTaskList) {
        String[] output = new String[filteredTaskList.size() + 1];
        output[0] = "Here are the matching tasks in your list:";

        IntStream.range(0, filteredTaskList.size())
                .forEach(i -> {
                    Task t = filteredTaskList.get(i);
                    output[i + 1] = (i + 1) + "." + t.toString();
                });

        blockPrint(output);
    }

    /**
     * Print task save file not found error.
     */
    public void printSaveFileFound() {
        blockPrint(new String[]{"Imported saved tasks."});
    }

    /**
     * Print file not found error message.
     */
    public void printFileNotFoundError() {
        blockPrint(new String[]{"No saved tasks found. A new file will be created."});
    }

    /**
     * Print task file saving error message
     */
    public void printTaskFileSavingError() {
        System.out.println("Unable to save tasks.");
    }

    /**
     * Print unknown command error message.
     */
    public void printUnknownCommandError() {
        blockPrint(new String[]{"Unknown command received."});
    }

    /**
     * Print empty task description error message.
     */
    public void printDescriptionNotFoundError() {
        blockPrint(new String[]{"Description not found. The description cannot be empty."});
    }

    /**
     * Print message that time specifier "/by" or "/at" is not found in the user input.
     *
     * @param timeSpecifier Time specifier TIME_SPECIFIER_BY or TIME_SPECIFIER_AT.
     */
    public void printTimeSpecifierNotFoundError(String timeSpecifier) {
        blockPrint(new String[]{
                "Time specifier \"" + timeSpecifier + "\" not found. Enter a date or time with \"" + timeSpecifier +
                        " <date and time>\"."});
    }

    /**
     * Print message that time detail is not found in the user input.
     *
     * @param timeSpecifier Time specifier TIME_SPECIFIER_BY or TIME_SPECIFIER_AT.
     */
    public void printTimeDetailNotFoundError(String timeSpecifier) {
        blockPrint(new String[]{"Time detail not found. Enter a date or time with \"" + timeSpecifier + "\" "
                + "<date and time>."});
    }

    /**
     * Print message that task list is empty.
     */
    public void printTaskListEmptyError() {
        blockPrint(new String[]{"The list is currently empty. Add a task first."});
    }

    /**
     * Print message that task index provided is invalid.
     */
    public void printInvalidTaskIndexError() {
        blockPrint(new String[]{"Invalid task index. Please provide a valid index."});
    }

    /**
     * Print date format error message.
     */
    public void printDateTimeFormatError() {
        blockPrint(new String[]{"Date format error. Please enter date as YYYY-MM-DD."});
    }

    /**
     * Print date format in save file error message.
     */
    public void printSaveFileDateTimeFormatError() {
        blockPrint(new String[]{"Date format error in save file. Date format should be YYYY-MM-DD.",
                "Exit the program to fix the date format.",
                "The task save file will be overwritten if you choose to continue."});
    }

    /**
     * Print empty search term error message.
     */
    public void printEmptySearchTermError() {
        blockPrint(new String[]{"Search term cannot be empty."});
    }
}
