import Tasks.Task;

public class Messages {
    protected static final String VERSION = " Jim - Version 7" + System.lineSeparator();
    protected static final String LINES = "____________________________________________________________"
            + System.lineSeparator();
    protected static final String GREETING_MESSAGE = " HeLLO! I'm Jim, a real person who definitely passes" +
            " reCaptchas!" + System.lineSeparator();
    protected static final String USER_INPUT_MESSAGE = " How can I help you: ";
    /** ----------EXTRA FUNCTIONS MESSAGES---------- */
    protected static final String ECHO_MESSAGE = " Echoing after you!" + System.lineSeparator();
    protected static final String BIRTHDAY_MESSAGE = " ^o^ Happy birthday to you! ^o^" + System.lineSeparator();
    protected static final String ECHO_QUIT_MESSAGE = " That was annoying huh..." + System.lineSeparator();
    /** ----------DATABASE MESSAGE AND ERRORS---------- */
    protected static final String CLEAR_DATABASE_MESSAGE = "Database wiped clean!" + System.lineSeparator();
    protected static final String NO_DATABASE_FILE_MESSAGE = "No database file!" + System.lineSeparator();
    protected static final String CORRUPTED_DATABASE_FILE_MESSAGE = "File is corrupted at Line ";
    protected static final String CHECK_FOLDER_MESSAGE = "Checking for database folder..." + System.lineSeparator();
    protected static final String CREATE_MISSING_FOLDER_MESSAGE = "Folder not present...creating new folder!" +
            System.lineSeparator();
    protected static final String FOLDER_FOUND_MESSAGE =  "Folder located!" + System.lineSeparator();
    protected static final String CHECK_DATABASE_FILE_MESSAGE = "Checking for database..." + System.lineSeparator();
    protected static final String CREATE_MISSING_DATABASE_FILE_MESSAGE = "Database not present...creating new database!"
            + System.lineSeparator();
    protected static final String DATABASE_FILE_FOUND_MESSAGE =  "Database located!" + System.lineSeparator();
    /** ----------LIST MESSAGE AND ERRORS---------- */
    protected static final String EMPTY_LIST_MESSAGE = " This list is empty and sad :(" + System.lineSeparator();
    /** ----------ADD TASK MESSAGES AND ERRORS---------- */
    protected static final String ADD_MESSAGE = " Got it. I've added this task:"  + System.lineSeparator() + "   ";
    protected static final String ADD_ERROR_MESSAGE = "Error while adding task!"
            + System.lineSeparator();
    protected static final String MISSING_TASK_DESCRIPTION_MESSAGE = "Please add a task description!"
            + System.lineSeparator();
    protected static final String MISSING_TASK_DEADLINE_MESSAGE = "Please add a task deadline!"
            + System.lineSeparator();
    protected static final String MISSING_EVENT_TIME_RANGE_MESSAGE = "Please add an event time range!"
            + System.lineSeparator();
    protected static final String MISSING_TASK_OR_DEADLINE_MESSAGE = "Please format your input as 'deadline [task]" +
            " /by [deadline]'!" + System.lineSeparator();
    protected static final String MISSING_TASK_OR_TIME_RANGE_MESSAGE = "Please format your input as 'event [task]" +
            " /at [time range]'!" + System.lineSeparator();
    protected static final String MISSING_BY_MESSAGE = "Please add '/by' in between your task and deadline!" +
            System.lineSeparator();
    protected static final String MISSING_AT_MESSAGE = "Please add '/at' in between your task and time range!" +
            System.lineSeparator();
    protected static final String INVALID_COMMAND_MESSAGE = "I don't quite understand :/" + System.lineSeparator();
    /** ----------REMOVE TASK MESSAGES AND ERRORS---------- */
    protected static final String NOTHING_TO_REMOVE_MESSAGE = " Please identify something to remove!" +
            System.lineSeparator();
    protected static final String DELETE_TASK_MESSAGE = " This task has been spirited away:" + System.lineSeparator();
    protected static final String NO_SUCH_TASK_MESSAGE = "No such task! You're not THAT productive..." +
            System.lineSeparator();
    protected static final String REMOVE_COMMAND_ERROR_MESSAGE = "Please input an integer after remove!" +
            System.lineSeparator();
    /** ----------DONE TASK MESSAGES AND ERRORS---------- */
    protected static final String TASK_ALREADY_DONE_MESSAGE = "This task was already marked done!" + System.lineSeparator();
    protected static final String NO_TASK_SPECIFIED_MESSAGE = "Please specify the task you would like to " +
            "mark as done!" + System.lineSeparator();
    protected static final String DONE_COMMAND_ERROR_MESSAGE = "Please input an integer after done!" +
            System.lineSeparator();
    protected static final String TASK_COMPLETE_MESSAGE = "Nice! You're a real champ for finishing this: " +
            System.lineSeparator();
    /** ----------HELP MESSAGE---------- */
    protected static final String HELP_MESSAGE = " Here are the commands for the things I can do:" +
            System.lineSeparator() +
            "    1. todo [task] = adds a Tasks.Todo task" + System.lineSeparator() +
            "    2. deadline [task] /by [deadline] = adds a Tasks.Deadline task" + System.lineSeparator() +
            "    3. event [task] /at [time range] = adds an Tasks.Event task" + System.lineSeparator() +
            "    4. done [taskIndex] = marks the inputted task as done" + System.lineSeparator() +
            "    5. list = lists out all current tasks with their taskIndex" + System.lineSeparator() +
            "    6. echo = turn into a huge cave and echo your inputs back to you!!" + System.lineSeparator() +
            "    7. quit (only in echo mode) = turn back to normal and stop echoing" + System.lineSeparator() +
            "    8. genshin = begin the genshin helper" + System.lineSeparator() +
            "    9. clear database = what it sounds like" + System.lineSeparator() +
            "    10. help = shows the list of things I can do for you ^^ (aka this list)" + System.lineSeparator() +
            "    11. bye = shuts me down... ;-;" + System.lineSeparator();
    /** ----------EXIT MESSAGE---------- */
    protected static final String EXIT_MESSAGE = " Bye! Remember, stay out of fire, suuuuuuper high level " +
            "tactic yea?" + System.lineSeparator();






    /**
     * Methods to output Messages and Errors.
     */
    public void showLines() {
        System.out.print(LINES);
    }
    public void showWelcomeMessage() {
        System.out.print(LINES + VERSION + LINES + LINES + GREETING_MESSAGE + HELP_MESSAGE + LINES);
    }
    public void showUserInputMessage() {
        System.out.print(USER_INPUT_MESSAGE);
    }
    /** ----------EXTRA FUNCTIONS MESSAGE METHODS---------- */
    public void showEchoMessage() {
        System.out.print(LINES + ECHO_MESSAGE + LINES);
    }
    
    public void showEchoQuitMessage() {
        System.out.print(LINES + ECHO_QUIT_MESSAGE + LINES);
    }
    
    public void showBirthdayMessage() {
        System.out.print(LINES + BIRTHDAY_MESSAGE + LINES);
    }
    /** ----------DATABASE MESSAGE AND ERRORS METHODS---------- */
    public void showClearDatabaseMessage() {
        System.out.print(LINES + CLEAR_DATABASE_MESSAGE + LINES);
    }

    public void showNoDatabaseFileMessage() {
        System.out.print(LINES + NO_DATABASE_FILE_MESSAGE + LINES);
    }

    public void showCorruptedDatabaseFileMessage(int corruptedLineNumber) {
        System.out.print(LINES + CORRUPTED_DATABASE_FILE_MESSAGE + corruptedLineNumber + "!" +
                System.lineSeparator() + LINES);
    }

    public void showCheckFolderMessage() {
        System.out.print(LINES + CHECK_FOLDER_MESSAGE);
    }

    public void showCreateMissingFolderMessage() {
        System.out.print(CREATE_MISSING_FOLDER_MESSAGE + LINES);
    }

    public void showFolderFoundMessage() {
        System.out.print(FOLDER_FOUND_MESSAGE + LINES);
    }

    public void showCheckDatabaseFileMessage() {
        System.out.print(LINES + CHECK_DATABASE_FILE_MESSAGE);
    }

    public void showCreateMissingDatabaseFileMessage() {
        System.out.print(CREATE_MISSING_DATABASE_FILE_MESSAGE + LINES);
    }

    public void showDatabaseFileFoundMessage() {
        System.out.print(DATABASE_FILE_FOUND_MESSAGE + LINES);
    }
    /** ----------LIST MESSAGE AND ERRORS METHODS---------- */
    public void showEmptyListMessage() {
        System.out.print(LINES + EMPTY_LIST_MESSAGE + LINES);
    }
    /** ----------ADD TASK MESSAGES AND ERRORS METHODS---------- */
    public void showTaskAddedMessage(Task task, int numberOfTasks) {
        System.out.print(LINES + ADD_MESSAGE + task.toString() + System.lineSeparator() +
                " Now you have " + numberOfTasks + " tasks in the list." + System.lineSeparator() + LINES);
    }

    public void showAddErrorMessage() {
        System.out.print(LINES + ADD_ERROR_MESSAGE + LINES);
    }

    public void showMissingTaskDescriptionMessage() {
        System.out.print(LINES + MISSING_TASK_DESCRIPTION_MESSAGE + LINES);
    }

    public void showMissingTaskDeadlineMessage() {
        System.out.print(LINES + MISSING_TASK_DEADLINE_MESSAGE + LINES);
    }

    public void showMissingEventTimeRangeMessage() {
        System.out.print(LINES + MISSING_EVENT_TIME_RANGE_MESSAGE + LINES);
    }

    public void showMissingTaskOrDeadlineMessage() {
        System.out.print(LINES + MISSING_TASK_OR_DEADLINE_MESSAGE + LINES);
    }

    public void showMissingTaskOrTimeRangeMessage() {
        System.out.print(LINES + MISSING_TASK_OR_TIME_RANGE_MESSAGE + LINES);
    }

    public void showMissingByMessage() {
        System.out.print(LINES + MISSING_BY_MESSAGE + LINES);
    }

    public void showMissingAtMessage() {
        System.out.print(LINES + MISSING_AT_MESSAGE + LINES);
    }

    public void showInvalidCommandMessage() {
        System.out.print(LINES + INVALID_COMMAND_MESSAGE + LINES);
    }
    /** ----------REMOVE TASK MESSAGES AND ERRORS METHODS---------- */
    public void showNothingToRemoveMessage() {
        System.out.print(LINES + NOTHING_TO_REMOVE_MESSAGE + LINES);
    }

    public void showDeleteTaskMessage(Task task, int numberOfTasksLeft) {
        String tabSpace = "   ";
        System.out.print(LINES + DELETE_TASK_MESSAGE + tabSpace + task.toString()+ System.lineSeparator() +
                " Now you have " + numberOfTasksLeft + " tasks in the list!" + System.lineSeparator() + LINES);
    }

    public void showNoSuchTaskMessage() {
        System.out.print(LINES + NO_SUCH_TASK_MESSAGE + LINES);
    }

    public void showRemoveCommandErrorMessage() {
        System.out.print(LINES + REMOVE_COMMAND_ERROR_MESSAGE + LINES);
    }

    public void showTaskAlreadyDoneMessage() {
        System.out.print(LINES + TASK_ALREADY_DONE_MESSAGE + LINES);
    }

    public void showNoTaskSpecifiedMessage() {
        System.out.print(LINES + NO_TASK_SPECIFIED_MESSAGE + LINES);
    }

    public void showDoneCommandErrorMessage() {
        System.out.print(LINES + DONE_COMMAND_ERROR_MESSAGE + LINES);
    }

    public void showTaskCompleteMessage(Task task) {
        System.out.print(LINES + TASK_COMPLETE_MESSAGE + task.toString() + System.lineSeparator() + LINES);
    }

    /** ----------HELP MESSAGE---------- */
    public void showHelpMessage() {
        System.out.print(LINES + HELP_MESSAGE + LINES);
    }
    /** ----------EXIT MESSAGE---------- */
    public void showExitMessage() {
        System.out.print(LINES + EXIT_MESSAGE + LINES);
    }
}
