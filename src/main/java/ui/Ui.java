package ui;
import java.util.Scanner;
import tasks.Task;

public class Ui {
    protected static final String VERSION = " Chatbot Jim - Version 7" + System.lineSeparator();
    protected static final String LINES = "____________________________________________________________"
            + System.lineSeparator();
    protected static final String GREETING_MESSAGE = " HeLLO! I'm Jim, a real person who definitely passes" +
            " reCaptchas!" + System.lineSeparator();
    protected static final String USER_INPUT_MESSAGE = " How can I help you: ";
    /** ----------EXTRA FUNCTIONS MESSAGES---------- */
    protected static final String BIRTHDAY_MESSAGE = " ^o^ Happy birthday to you! ^o^" + System.lineSeparator();
    /** ----------DATABASE MESSAGE AND ERRORS---------- */
    protected static final String CLEAR_DATABASE_MESSAGE = " Database wiped clean!" + System.lineSeparator();
    protected static final String NO_DATABASE_FILE_MESSAGE = " No database file!" + System.lineSeparator();
    protected static final String CORRUPTED_DATABASE_FILE_MESSAGE = " File is corrupted at Line ";
    protected static final String CHECK_FOLDER_MESSAGE = " Checking for database folder..." + System.lineSeparator();
    protected static final String CREATE_MISSING_FOLDER_MESSAGE = " Folder not present...creating new folder!" +
            System.lineSeparator();
    protected static final String FOLDER_FOUND_MESSAGE =  " Folder located!" + System.lineSeparator();
    protected static final String CHECK_DATABASE_FILE_MESSAGE = " Checking for database..." + System.lineSeparator();
    protected static final String CREATE_MISSING_DATABASE_FILE_MESSAGE = " Database not present...creating new database!"
            + System.lineSeparator();
    protected static final String DATABASE_FILE_FOUND_MESSAGE =  " Database located!" + System.lineSeparator();
    /** ----------LIST MESSAGE AND ERRORS---------- */
    protected static final String EMPTY_LIST_MESSAGE = " This list is empty and sad :(" + System.lineSeparator();
    protected static final String LIST_MESSAGE = " Here is your task list:" + System.lineSeparator();
    /** ----------ADD TASK MESSAGES AND ERRORS---------- */
    protected static final String ADD_MESSAGE = " Got it. I've added this task:"  + System.lineSeparator() + "   ";
    protected static final String ADD_ERROR_MESSAGE = " Error while adding task!"
            + System.lineSeparator();
    protected static final String MISSING_TASK_DESCRIPTION_MESSAGE = " Please add a task description!"
            + System.lineSeparator();
    protected static final String MISSING_TASK_DEADLINE_MESSAGE = " Please add a task deadline!"
            + System.lineSeparator();
    protected static final String MISSING_EVENT_TIME_RANGE_MESSAGE = " Please add an event time range!"
            + System.lineSeparator();
    protected static final String MISSING_TASK_OR_DEADLINE_MESSAGE = " Please format your input as 'deadline [task]" +
            " /by [deadline]'!" + System.lineSeparator();
    protected static final String MISSING_TASK_OR_TIME_RANGE_MESSAGE = " Please format your input as 'event [task]" +
            " /at [time range]'!" + System.lineSeparator();
    protected static final String MISSING_BY_MESSAGE = " Please add '/by' in between your task and deadline!" +
            System.lineSeparator();
    protected static final String MISSING_AT_MESSAGE = " Please add '/at' in between your task and time range!" +
            System.lineSeparator();
    protected static final String INVALID_COMMAND_MESSAGE = " I don't quite understand :/" + System.lineSeparator();
    /** ----------REMOVE TASK MESSAGES AND ERRORS---------- */
    protected static final String NOTHING_TO_REMOVE_MESSAGE = " Please identify something to remove!" +
            System.lineSeparator();
    protected static final String DELETE_TASK_MESSAGE = " This task has been spirited away:" + System.lineSeparator();
    protected static final String NO_SUCH_TASK_MESSAGE = " No such task! You're not THAT productive..." +
            System.lineSeparator();
    protected static final String REMOVE_COMMAND_ERROR_MESSAGE = " Please input an integer after remove!" +
            System.lineSeparator();
    /** ----------DONE TASK MESSAGES AND ERRORS---------- */
    protected static final String TASK_ALREADY_DONE_MESSAGE = " This task was already marked done!" + System.lineSeparator();
    protected static final String NO_TASK_SPECIFIED_MESSAGE = " Please specify the task you would like to " +
            "mark as done!" + System.lineSeparator();
    protected static final String DONE_COMMAND_ERROR_MESSAGE = " Please input an integer after done!" +
            System.lineSeparator();
    protected static final String TASK_COMPLETE_MESSAGE = " Nice! You're a real champ for finishing this: " +
            System.lineSeparator();
    /** ----------FIND TASK MESSAGES AND ERRORS---------- */
    protected static final String NO_KEYWORD_SPECIFIED_MESSAGE = " Please specify the keyword you would like to " +
            "find!" + System.lineSeparator();
    protected static final String FOUND_TASK_MESSAGE = " Here are the matching tasks in your list: " +
            System.lineSeparator();
    protected static final String TASK_NOT_FOUND_MESSAGE = " No task with given keyword in description!" +
            System.lineSeparator();
    /** ----------HELP MESSAGE---------- */
    protected static final String HELP_MESSAGE = " Here are the commands for the things I can do:" +
            System.lineSeparator() +
            "    1. todo [task] = adds a Todo task" + System.lineSeparator() +
            "    2. deadline [task] /by [deadline] = adds a Deadline task" + System.lineSeparator() +
            "    3. event [task] /at [time range] = adds an Event task" + System.lineSeparator() +
            "    4. done [taskIndex] = marks the inputted task as done" + System.lineSeparator() +
            "    5. list = lists out all tasks you have currently" + System.lineSeparator() +
            "    6. find [keyword] = finds all tasks that contain the keyword in the description" + System.lineSeparator() +
            "    7. clear database = wipes your database spick and span" + System.lineSeparator() +
            "    8. help = shows the list of things I can do for you ^^ (aka this list)" + System.lineSeparator() +
            "    9. bye = shuts me down... ;-;" + System.lineSeparator();
    /** ----------EXIT MESSAGE---------- */
    protected static final String EXIT_MESSAGE = " Bye! Remember, stay out of fire, suuuuuuper high level " +
            "tactic yea?" + System.lineSeparator();

    protected Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Returns the user input.
     *
     * @return user input.
     */
    public String readCommand() {
        showUserInputMessage();
        return sc.nextLine();
    }

    /**
     * Prints the decorative lines with a line separator.
     */
    public void showLines() {
        System.out.print(LINES);
    }

    /**
     * Prints the welcome message with decorative lines.
     */
    public void showWelcomeMessage() {
        System.out.print(LINES + VERSION + LINES + LINES + GREETING_MESSAGE + HELP_MESSAGE + LINES);
    }

    /**
     * Prints the message to get user input.
     */
    public void showUserInputMessage() {
        System.out.print(USER_INPUT_MESSAGE);
    }
    /* ----------EXTRA FUNCTIONS MESSAGE METHODS---------- */

    /**
     * Prints the birthday message with decorative lines.
     */
    public void showBirthdayMessage() {
        System.out.print(LINES + BIRTHDAY_MESSAGE + LINES);
    }
    /* ----------DATABASE MESSAGE AND ERRORS METHODS---------- */

    /**
     * Prints the database cleared message with decorative lines.
     */
    public void showClearDatabaseMessage() {
        System.out.print(LINES + CLEAR_DATABASE_MESSAGE + LINES);
    }

    /**
     * Prints the no database file error with decorative lines.
     */
    public void showNoDatabaseFileMessage() {
        System.out.print(LINES + NO_DATABASE_FILE_MESSAGE + LINES);
    }

    /**
     * Prints the corrupted database file error that shows the first corrupted line
     * with decorative lines.
     * @param corruptedLineNumber Line number of the first corrupted line in the file.
     */
    public void showCorruptedDatabaseFileMessage(int corruptedLineNumber) {
        System.out.print(LINES + CORRUPTED_DATABASE_FILE_MESSAGE + corruptedLineNumber + "!" +
                System.lineSeparator() + LINES);
    }

    /**
     * Prints the check folder message with decorative line on top.
     */
    public void showCheckFolderMessage() {
        System.out.print(LINES + CHECK_FOLDER_MESSAGE);
    }

    /**
     * Prints the create missing folder message.
     */
    public void showCreateMissingFolderMessage() {
        System.out.print(CREATE_MISSING_FOLDER_MESSAGE);
    }

    /**
     * Prints the folder found message.
     */
    public void showFolderFoundMessage() {
        System.out.print(FOLDER_FOUND_MESSAGE);
    }

    /**
     * Prints the check database file message.
     */
    public void showCheckDatabaseFileMessage() {
        System.out.print(CHECK_DATABASE_FILE_MESSAGE);
    }

    /**
     * Prints the create missing file message with decorative line below.
     */
    public void showCreateMissingDatabaseFileMessage() {
        System.out.print(CREATE_MISSING_DATABASE_FILE_MESSAGE + LINES);
    }

    /**
     * Prints the file found message with decorative line below.
     */
    public void showDatabaseFileFoundMessage() {
        System.out.print(DATABASE_FILE_FOUND_MESSAGE + LINES);
    }
    /* ----------LIST MESSAGE AND ERRORS METHODS---------- */

    /**
     * Prints the empty list error with decorative lines.
     */
    public void showEmptyListMessage() {
        System.out.print(LINES + EMPTY_LIST_MESSAGE + LINES);
    }

    /**
     * Prints the list message with decorative line on top.
     */
    public void showListMessage() {
        System.out.print(LINES + LIST_MESSAGE);
    }
    /* ----------ADD TASK MESSAGES AND ERRORS METHODS---------- */

    /**
     * Prints the task added message with the task to be added and the total number of tasks in the list
     * after adding.
     *
     * @param task Task to be added.
     * @param numberOfTasks Number of tasks in the list.
     */
    public void showTaskAddedMessage(Task task, int numberOfTasks) {
        System.out.print(LINES + ADD_MESSAGE + task.toString() + System.lineSeparator() +
                " Now you have " + numberOfTasks + " tasks in the list." + System.lineSeparator() + LINES);
    }

    /**
     * Prints the task adding error message with decorative lines.
     */
    public void showAddErrorMessage() {
        System.out.print(LINES + ADD_ERROR_MESSAGE + LINES);
    }

    /**
     * Prints the missing task description error message with decorative lines.
     */
    public void showMissingTaskDescriptionMessage() {
        System.out.print(LINES + MISSING_TASK_DESCRIPTION_MESSAGE + LINES);
    }

    /**
     * Prints the missing deadline error message with decorative lines.
     */
    public void showMissingTaskDeadlineMessage() {
        System.out.print(LINES + MISSING_TASK_DEADLINE_MESSAGE + LINES);
    }

    /**
     * Prints the missing time range error message with decorative lines.
     */
    public void showMissingEventTimeRangeMessage() {
        System.out.print(LINES + MISSING_EVENT_TIME_RANGE_MESSAGE + LINES);
    }

    /**
     * Prints the missing description and/or deadline error message with decorative lines.
     */
    public void showMissingTaskOrDeadlineMessage() {
        System.out.print(LINES + MISSING_TASK_OR_DEADLINE_MESSAGE + LINES);
    }

    /**
     * Prints the missing description and/or time range error message with decorative lines.
     */
    public void showMissingTaskOrTimeRangeMessage() {
        System.out.print(LINES + MISSING_TASK_OR_TIME_RANGE_MESSAGE + LINES);
    }

    /**
     * Prints the missing '/by' error message with decorative lines.
     */
    public void showMissingByMessage() {
        System.out.print(LINES + MISSING_BY_MESSAGE + LINES);
    }

    /**
     * Prints the missing '/at' error message with decorative lines.
     */
    public void showMissingAtMessage() {
        System.out.print(LINES + MISSING_AT_MESSAGE + LINES);
    }

    /**
     * Prints the invalid command error message with decorative lines.
     */
    public void showInvalidCommandMessage() {
        System.out.print(LINES + INVALID_COMMAND_MESSAGE + LINES);
    }
    /* ----------REMOVE TASK MESSAGES AND ERRORS METHODS---------- */

    /**
     * Prints the nothing to remove error message with decorative lines.
     */
    public void showNothingToRemoveMessage() {
        System.out.print(LINES + NOTHING_TO_REMOVE_MESSAGE + LINES);
    }

    /**
     * Prints the task deleted message with the deleted task and the number of tasks left with decorative lines.
     *
     * @param task Task to be deleted.
     * @param numberOfTasksLeft Number of tasks left in the list.
     */
    public void showDeleteTaskMessage(Task task, int numberOfTasksLeft) {
        String tabSpace = "   ";
        System.out.print(LINES + DELETE_TASK_MESSAGE + tabSpace + task.toString()+ System.lineSeparator() +
                " Now you have " + numberOfTasksLeft + " tasks in the list!" + System.lineSeparator() + LINES);
    }

    /**
     * Prints the no such task error message with decorative lines.
     */
    public void showNoSuchTaskMessage() {
        System.out.print(LINES + NO_SUCH_TASK_MESSAGE + LINES);
    }

    /**
     * Prints the remove command error message with decorative lines.
     * This message procs when an integer is not inputted after the 'remove' command.
     */
    public void showRemoveCommandErrorMessage() {
        System.out.print(LINES + REMOVE_COMMAND_ERROR_MESSAGE + LINES);
    }
    /* ----------DONE TASK MESSAGES AND ERRORS---------- */

    /**
     * Prints the task already done error message with decorative lines.
     */
    public void showTaskAlreadyDoneMessage() {
        System.out.print(LINES + TASK_ALREADY_DONE_MESSAGE + LINES);
    }

    /**
     * Prints the no task specified error message with decorative lines.
     */
    public void showNoTaskSpecifiedMessage() {
        System.out.print(LINES + NO_TASK_SPECIFIED_MESSAGE + LINES);
    }

    /**
     * Prints the done command error message with decorative lines.
     * This message procs when an integer is not inputted after the 'done' command.
     */
    public void showDoneCommandErrorMessage() {
        System.out.print(LINES + DONE_COMMAND_ERROR_MESSAGE + LINES);
    }

    /**
     * Prints the task completed message with the completed task and decorative lines.
     *
     * @param task Task that is completed.
     */
    public void showTaskCompleteMessage(Task task) {
        System.out.print(LINES + TASK_COMPLETE_MESSAGE + " " + task.toString() + System.lineSeparator() + LINES);
    }
    /* ----------FIND TASK MESSAGES AND ERRORS---------- */

    /**
     * Prints the no keyword specified error message with decorative lines.
     */
    public void showNoKeywordSpecifiedMessage() {
        System.out.println(LINES + NO_KEYWORD_SPECIFIED_MESSAGE + LINES);
    }

    /**
     * Prints the task found message with decorative line on top.
     */
    public void showFoundTaskMessage() {
        System.out.print(LINES + FOUND_TASK_MESSAGE);
    }

    /**
     * Prints the task not found error message with decorative lines.
     */
    public void showTaskNotFoundMessage() {
        System.out.print(LINES + TASK_NOT_FOUND_MESSAGE + LINES);
    }
    /* ----------HELP MESSAGE---------- */

    /**
     * Prints the help message with decorative lines.
     */
    public void showHelpMessage() {
        System.out.print(LINES + HELP_MESSAGE + LINES);
    }
    /* ----------EXIT MESSAGE---------- */

    /**
     * Prints the exit message with decorative lines.
     */
    public void showExitMessage() {
        System.out.print(LINES + EXIT_MESSAGE + LINES);
    }
}
