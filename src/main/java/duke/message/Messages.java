package duke.message;

import duke.data.exception.DukeException;

public class Messages {
    /**
     * Store all the messages used in this program
     */
    private static final String logo = " ____              ________  \n"
            + "|  _ \\  | |   | |  |__  __| \n"
            + "| |_| | | |   | |    |  |\n"
            + "|____/  | |   | |    |  |\n"
            + "| |\\ \\  | |   | |    |  |\n"
            + "| | \\ \\ | |___| |   --  --\n"
            + "|_|  \\ \\\\ _____ /  | ____|\n";
    public static final String MESSAGE_WELCOME = "Hello from\n" + logo;
    public static final String MESSAGE_GREET = "Hello! I'm Rui\nWhat can I do for you?";
    public static final String MESSAGE_GOODBYE = "Bye. Hope to see you again soon!";
    public static final String MESSAGE_LIST = "Here are the tasks in your list:\n%s";
    public static final String MESSAGE_DONE = "Nice! I've marked this task as done:\n%s";
    public static final String MESSAGE_DELETE = "Noted. I've removed this task: \n%s";
    public static final String MESSAGE_ADD = "Got it. I've added this task:\n%s";
    public static final String MESSAGE_COUNT = "Now you have %d tasks in the list.";
    public static final String MESSAGE_FIND = "Here are the matching tasks in your list:\n%s";

    public static final String MESSAGE_HELP = "Sample commands: \n"
            + "list: show all the tasks\n"
            + "todo <content>: add a todo task\n"
            + "event <content> /at <time>: add an event task \n"
            + "deadline <content> /by <time>: add a deadline task\n"
            + "done <index>: mark the task at index \'index\' as done\n"
            + "delete <index>: delete the task at index \'index\'\n"
            + "bye: close the program\n"
            + "help: show the help information for users\n"
            + "find <keyword>: fine tasks containing keyword";


    public static final String MESSAGE_LOADING_ERROR = "No previous data";

}
