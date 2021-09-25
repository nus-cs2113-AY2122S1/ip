package Duke.UI;

/**
 * Class which stores all the constants used in Duke Program
 */
public class DukeConstants {
    //List of Special User Commands
    public static final String EXIT_STRING = "bye";
    public static final String LIST_COMMAND = "list";
    public static final String DONE_COMMAND = "done";
    public static final String TODO_COMMAND = "todo";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String EVENT_COMMAND = "event";
    public static final String DELETE_COMMAND = "delete";
    public static final String HELP_COMMAND = "help";
    public static final String FIND_COMMAND = "find";
    public static final String DUKE_LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String LINE = "____________________________________________________________";
    public static final String EVENT_KEYWORD = " /at";
    public static final String DEADLINE_KEYWORD = " /by";
    public static final String GOODBYE_MESSAGE = " Bye. Hope to see you again soon!";
    public static final String HELP_MESSAGE = " Type list to display your list of todos.\n"
            +" Type todo {description} to add a task into your list.\n"
            +" Type event {description} /at {at} to add an event into your list.\n"
            +" Type deadline {description} /by {by} to add a deadline into your list.\n"
            +" Type delete {index} to delete a task from your list of todos.\n"
            +" Type done {index} to mark a task as done in your list of todos.\n"
            +" Type bye to exit Duke.";
    public static final String FORMAT_DATE_TIME_INPUT = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_DATE_TIME_OUTPUT = "LLL dd yyyy HH:mma";
}
