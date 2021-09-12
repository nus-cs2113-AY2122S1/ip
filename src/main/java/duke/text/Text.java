package duke.text;

public class Text { //contains all constants used

    public static final String LINE = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    public static final String GOODBYE_MESSAGE = "GoodBye! Please finish up your task!";
    public static final String HELLO_MESSAGE = "Hey there! I am Chai!\n" + "What are you doing today?";
    public static final String UNKNOWN_COMMAND = "Error404!!! I don't understand what that is, please try again!";
    public static final String LOGO = " ______    __                     __\n" +
            "/  ____|  |  |                   |__|\n" +
            "|  |      |  |_____    _______    __\n" +
            "|  |      |   __   \\  /  __   |  |  |\n" +
            "|  |____  |  |  |  |  | |__|  |  |  |\n" +
            "\\______|  |__|  |__|  \\____/\\_|  |__|\n";
    public static final String LIST_MESSAGE = "Tasks list so far:";
    public static final String NO_TASK_NUMBER = "Please input a task number!";
    public static final String NO_VALID_NUMBER = "Please input a valid number!";
    public static final String TASK_NUMBER_NOT_FOUND = "Task number not found!";
    public static final String TASK_MARKED = "Well done! Task marked:";
    public static final String TASK_DELETED = "Roger! Task removed from list:";
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String HELP_LIST = "List of commands available:\n\n"
            + "todo <type your task here> [eg. todo swimming]\n"
            + "-> Adds a todo task to the task list.\n\n"
            + "deadline <type your deadline here> <SPACE> </by> <SPACE> <type a date or time> [eg. deadline assignment /by monday 1pm]\n"
            + "-> Adds a deadline task to the task list.\n\n"
            + "event <type your event here> <SPACE> </at> <SPACE> <type a date or time> [eg. lecture /at friday 4pm]\n"
            + "-> Adds an event task to the task list.\n\n"
            + "done <type task number> <SPACE> <type task number> [eg. done 1 3]\n"
            + "Marks task on the list as completed, can input multiple number with space in between.\n\n"
            + "delete <type task number> [eg. delete 2]\n"
            + "Deletes the task found on the task list.\n\n"
            + "list [eg. list]\n"
            + "-> Lists out all tasks on the list.\n\n"
            + "bye [eg. bye]\n"
            + "-> Exits program";
}
