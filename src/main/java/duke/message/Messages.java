package duke.message;

/**
 * Contains the messages that users see
 */
public class Messages {
    public static final String LOGO = " _______  __   __  ______   _______  _______  _______  __   __  _______\n" +
            "|       ||  | |  ||      | |       ||       ||   _   ||  |_|  ||   _   |\n" +
            "|    ___||  | |  ||  _    ||    ___||_     _||  |_|  ||       ||  |_|  |\n" +
            "|   | __ |  |_|  || | |   ||   |___   |   |  |       ||       ||       |\n" +
            "|   ||  ||       || |_|   ||    ___|  |   |  |       ||       ||       |\n" +
            "|   |_| ||       ||       ||   |___   |   |  |   _   || ||_|| ||   _   |\n" +
            "|_______||_______||______| |_______|  |___|  |__| |__||_|   |_||__| |__|\n";
    public static final String LOAD_DELIMITER = "--";
    public static final String WELCOME = "Hi... from GUDETAMA... so sleepy\n" +
            "Give me five more minutes..... What can I do for you?";
    public static final String HELP = "Do not include the angle brackets <>\n" +
            "Get help:\n" +
            "    help\n" +
            "List tasks:\n" +
            "    list\n" +
            "Add todo task:\n" +
            "    todo <description>\n" +
            "Add deadline task:\n" +
            "    deadline <description> /by <due date: yyyy-MM-dd> <due time: hh:mm>\n" +
            "Add event task:\n" +
            "    event <description> /at <date: yyyy-MM-dd> <start time: hh:mm> <end time: hh:mm>\n" +
            "Mark task done:\n" +
            "    done <task_number: int>\n" +
            "Delete task:\n" +
            "    delete <task_number: int>\n" +
            "Find task:\n" +
            "    find <keyword>\n" +
            "Save and exit program:\n" +
            "    bye";
    public static final String LIST_TASK = "    Tasks to do... so lazy:";
    public static final String NUMBER_LIST_TASK = " task(s) in list";
    public static final String FINISHED_TASK = "    Finished this task... I need a break:";
    public static final String DELETED_TASK = "    Deleted this task... less things to do:";
    public static final String FOUND_TASK = "    Task(s) found:";
    public static final String NUMBER_FOUND_TASK = " task(s) found";
    public static final String BYE = "Bye. I'm going back to sleep... zzz";
}
