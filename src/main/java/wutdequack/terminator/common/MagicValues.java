package wutdequack.terminator.common;

import java.util.ArrayList;
import wutdequack.terminator.objects.task.Task;
import wutdequack.terminator.tasklist.TaskList;
import wutdequack.terminator.ui.TextUi;
import wutdequack.terminator.storage.Storage;

public class MagicValues {
    /**
     * Keywords for commands.
     */
    public static final String BYE_STRING = "BYE";
    public static final String DONE_STRING = "DONE";
    public static final String LIST_STRING = "LIST";
    public static final String FIND_STRING = "FIND";
    public static final String TODO_STRING = "TODO";
    public static final String EVENT_STRING = "EVENT";
    public static final String DELETE_STRING = "DELETE";
    public static final String DEADLINE_STRING = "DEADLINE";

    /**
     * List of constants used to define who is speaking.
     */
    public static final String TERMINATOR_STRING = "[The Terminator]";
    public static final String USER_STRING = "[User]";

    /**
     * List of constants used in extractNameDateTime
     */
    public static final String BY_KEYWORD = "/by";
    public static final String AT_KEYWORD = "/at";
    public static final String TODO_KEYWORD = "todo";
    public static final String EVENT_KEYWORD = "event";
    public static final String DEADLINE_KEYWORD = "deadline";

    /**
     * List of constants used in formatWithHeading.
     */
    public static final int TERMINATOR_FORMATTING = 0;
    public static final int USER_FORMATTING = 1;

    /**
     * List of constants used in tokenizing user input.
     */
    public static final int KEYWORD_INDEX = 0;
    public static final int SEARCH_TERM_INDEX = 1;
    public static final int TASK_NUMBER_INDEX = 1;

    /**
     * List of constants used to identify Event Type for createEvent
     */
    public static final String TODO_TYPE = "T";
    public static final String EVENT_TYPE = "E";
    public static final String DEADLINE_TYPE = "D";

    /**
     * List of constants used to show index of taskName and dateTime from retrieval list
     */
    public static final int TASK_NAME_INDEX = 0;
    public static final int DATE_TIME_INDEX = 1;
    public static final int COMPLETION_INDEX = 2;

    /**
     * Global File Path to save and retrieve Task Records
     */
    public static final String FILE_LOCATION = "./data/records.txt";

    /**
     * Global variables used to determine if input for Task Creation is from file or from user
     */
    public static final int FROM_USER = 0;
    public static final int FROM_FILE = 1;

    /**
     * Global variable to show the deliminator for strings stored in the file format
     */
    public static final String DELIMINATOR_FOR_FILE = " | ";

    /**
     * Global ArrayList of all Tasks
     */
    public static ArrayList<Task> tasksList;
    public static TextUi ui;
}
