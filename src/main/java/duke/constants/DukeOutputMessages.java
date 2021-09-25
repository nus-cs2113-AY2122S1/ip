package duke.constants;

import static duke.constants.DukeCommandStrings.DATE_TIME_INPUT_FORMAT;

public class DukeOutputMessages extends DukeConstants {

    /** Messages to greet user upon entry and exit of program. */
    public static final String HEY_MESSAGE = WHITESPACE + "Hey there! I'm Duke." + System.lineSeparator() + " How may I help you?";
    public static final String BYE_MESSAGE = WHITESPACE + "Goodbye! Hope to see you again soon.";
    public static final String HELLO_FROM = "Hello from" + System.lineSeparator();
    public static final String DUKE_LOGO = " ____        _        \n"
                                         + "|  _ \\ _   _| | _____ \n"
                                         + "| | | | | | | |/ / _ \\\n"
                                         + "| |_| | |_| |   <  __/\n"
                                         + "|____/ \\__,_|_|\\_\\___|\n";

    /** Messages informing user of erroneous commands. */
    public static final String UNRECOGNIZED_COMMAND_MESSAGE = WHITESPACE + "I don't know what that means!";
    public static final String COMMAND_WRONG_FORMAT_MESSAGE = WHITESPACE + "Your command is of the wrong format!";
    public static final String TASK_NOT_IN_LIST_MESSAGE = WHITESPACE + "Sorry, the task is not in the list! Try again.";
    public static final String QUERY_NOT_FOUND_MESSAGE = WHITESPACE + "I could not find any task with that keyword! Try again.";
    public static final String DATE_TIME_INVALID_MESSAGE = WHITESPACE + "The date and time entered are invalid!";

    /** Messages informing user that a non-erroneous command has been executed successfully. */
    public static final String HERE_IS_TASK_LIST_MESSAGE = WHITESPACE + "Here are the tasks in your list:";
    public static final String HERE_ARE_TASKS_CONTAINING_MESSAGE = WHITESPACE + "Here are tasks containing" + WHITESPACE;
    public static final String TASK_LIST_EMPTY_MESSAGE = WHITESPACE + "The list is empty! Add a task first.";
    public static final String TASK_ADDED_MESSAGE = WHITESPACE + "I have added a task:";
    public static final String TASK_DELETED_MESSAGE = WHITESPACE + "Got it! I have deleted this task:";
    public static final String TASK_MARKED_DONE_MESSAGE = WHITESPACE + "Great job! The following task is done:";

    /** Messages for {@code help} command. */
    public static final String ENTER_HELP = WHITESPACE + "Enter \"help\" for a list of valid commands and their respective formats!";
    public static final String HELP_INTRO_MESSAGE      = WHITESPACE + "Here are the commands I recognize along with their formats:";
    public static final String LIST_COMMAND_FORMAT     = WHITESPACE + "list     | Format: list";
    public static final String DONE_COMMAND_FORMAT     = WHITESPACE + "done     | Format: done [task ID (int)]";
    public static final String TODO_COMMAND_FORMAT     = WHITESPACE + "todo     | Format: todo [description]";
    public static final String DEADLINE_COMMAND_FORMAT = WHITESPACE + "deadline | Format: deadline [description] /by [deadline (in " + DATE_TIME_INPUT_FORMAT + " format)]";
    public static final String EVENT_COMMAND_FORMAT    = WHITESPACE + "event    | Format: event [description] /at [time (in " + DATE_TIME_INPUT_FORMAT + " format)]";
    public static final String FIND_COMMAND_FORMAT     = WHITESPACE + "find     | Format: find [keyword]";
    public static final String DELETE_COMMAND_FORMAT   = WHITESPACE + "delete   | Format: delete [task ID (int)]";
    public static final String BYE_COMMAND_FORMAT      = WHITESPACE + "bye      | Format: bye";
}
