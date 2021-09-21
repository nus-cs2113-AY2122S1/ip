package duke.text;

public class Text { // Class file to store all constant String used.

    public static final String LINE = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
    public static final String NEW_LINE = "\n";
    public static final String GOODBYE_MESSAGE = "GoodBye! Please finish up your task!\n";
    public static final String HELLO_MESSAGE = "Hey there! I am Chai!\n" + "What are you doing today?\n";
    public static final String UNKNOWN_COMMAND = "Error404!!! I don't understand what that is, please try again!\n";
    public static final String UNSPECIFIED_METHOD = "Method is unspecified.\n";
    public static final String EMPTY_TASK_DESCRIPTION = "Error404!!! Task description should not be empty!\n";
    public static final String LOGO = " ______    __                     __\n" +
            "/  ____|  |  |                   |__|\n" +
            "|  |      |  |_____    _______    __\n" +
            "|  |      |   __   \\  /  __   |  |  |\n" +
            "|  |____  |  |  |  |  | |__|  |  |  |\n" +
            "\\______|  |__|  |__|  \\____/\\_|  |__|\n";
    public static final String LIST_MESSAGE = "Tasks list so far:\n";
    public static final String NO_TASK_NUMBER = "Please input a task number!\n";
    public static final String NO_DATE = "Please input a date in the format yyyy-mm-dd\n";
    public static final String INPUT_VALID_TASK = "Task index not found! Please input a valid task number!\n";
    public static final String TASK_MARKED = "Well done! Task marked:\n";
    public static final String TASK_DELETED = "Roger! Task removed from list:\n";
    public static final String TODO = "todo";
    public static final String TODO_T = "T";
    public static final String DEADLINE = "deadline";
    public static final String DEADLINE_D = "D";
    public static final String DEADLINE_DATE_SEPARATOR = " /by ";
    public static final String EVENT = "event";
    public static final String EVENT_E = "E";
    public static final String EVENT_DATE_SEPARATOR = " /at ";
    public static final String FIND = "find";
    public static final String LIST = "list";
    public static final String HELP = "help";
    public static final String DONE = "done";
    public static final String DELETE = "delete";
    public static final String DATE = "date";
    public static final String BYE = "bye";
    public static final String SPACE = " ";
    public static final String ADDED_MESSAGE = "Alright! Added to the list:";
    public static final String CURRENT_MESSAGE = "You currently have ";
    public static final String TASK_RECORDED_MESSAGE = " task recorded in your list.";
    public static final String TASK_LEFT_MESSAGE = " left in the list.";
    public static final String HELP_LIST = "List of commands available:\n\n"
            + "todo <type your task here> [eg. todo swimming]\n"
            + "-> Adds a todo task to the task list.\n\n"
            + "deadline <type your deadline here> <SPACE> </by> <SPACE> <type a date with format yyyy-mm-dd> [eg. deadline assignment /by 2021-10-01]\n"
            + "-> Adds a deadline task to the task list.\n\n"
            + "event <type your event here> <SPACE> </at> <SPACE> <type a date with format yyyy-mm-dd> [eg. lecture /at 2021-10-01]\n"
            + "-> Adds an event task to the task list.\n\n"
            + "done <type task number> [eg. done 1]\n"
            + "-> Marks task on the list as completed.\n\n"
            + "delete <type task number> [eg. delete 2]\n"
            + "-> Deletes the task found on the task list.\n\n"
            + "find <type keyword> [eg. find assignment]\n"
            + "-> Find the tasks in task list associated with the given keyword.\n\n"
            + "date <type a date with format yyyy-mm-dd> [eg. 2021-10-01]\n"
            + "-> Find the tasks associated with the given date from user.\n\n"
            + "list [eg. list]\n"
            + "-> Lists out all tasks on the list.\n\n"
            + "bye [eg. bye]\n"
            + "-> Exits program\n";
    public static final String DATE_FORMAT = "dd MMM yyyy";
    public static final String DIRECTORY = "data";
    public static final String FILE_PATH = "data/dukeTask.txt";
    public static final String SAVING_ERROR = "Saving error\n";
    public static final String FILE_ERROR = "Error loading file!\n";
    public static final String GET_COMMAND = "Input command: ";
}
