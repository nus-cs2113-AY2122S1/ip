package constants;

public class Message {

    public static final String DIVIDER = "___________________________________________________________";
    public static final String INDENTATION = "      ";
    public static final String HELP_MESSAGE = "list\n" +INDENTATION +
            "- Shows you the list of tasks you have\n\n" +INDENTATION +
            "todo (insert task description)\n" + INDENTATION +
            "- Saves a general todo task\n\n" + INDENTATION +
            "deadline (insert deadline description) /by (insert time)\n" + INDENTATION +
            "- Saves a task with a given deadline\n\n" + INDENTATION +
            "event (insert event description) /at (insert time interval)\n" + INDENTATION +
            "- Saves an event happening at a certain time period\n\n" + INDENTATION +
            "delete (insert number)\n" + INDENTATION +
            "- Deletes a task with the corresponding number on the list\n\n" + INDENTATION +
            "done (insert number)\n" + INDENTATION +
            "- Marks the task with the corresponding number as done";
    public static final String HELLO_MESSAGE_2 = "Hello! I'm Duke, your friendly neighbourhood task manager\n" +
            INDENTATION + "What can I do for you? :D\n" + INDENTATION  + "(type help if you're not sure what to do!)";
    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String TASK_COMPLETED_MESSAGE = "You've completed the task! Well done!";
    public static final String GOODBYE_MESSAGE = "Bye, hope to see you again soon! :)";
    public static final String TYPE_SUITABLE_COMMAND_MESSAGE = "Sorry, I don't know what you mean. " +
            "Please give me a suitable command :)";
    public static final String PROMPT_TASK_DESCRIPTION = "Please tell me what do you need to do :)";
    public static final String MISSING_ARGUMENTS_FOR_EVENT_AND_DEADLINE = "Sorry," +
            " you're missing some arguments, do type 'help' if you're unsure :)";
    public static final String PROMPT_TASK_NUMBER = "Please tell me which task you want to select :)";
    public static final String PROMPT_SENSIBLE_INDEX = "Please give a number between 1 and ";
    public static final String DEFAULT_ERROR_MESSAGE = "Oops, something went wrong!";
    public static final String FILE_NOT_CREATED = "Looks like you don't have a file for your tasks, " +
            "let me create one now.....";
    public static final String UNEXPECTED_ERROR = "Oops,something unexpected happened";
    public static final String INCORRECT_FORMAT = "Oops, file format is incorrect. Please correct it!";
    public static final String DONE = "Done!";
    public static final String GETTING_TASK = "Getting your tasks.....";
    public static final String TASK_REMOVED = "Alright, I've removed this task:";

    public static String getSensibleRange(int number) {
        if(number < 1) {
            return LIST_IS_EMPTY;
        }
        return PROMPT_SENSIBLE_INDEX + number + " :)";
    }
}
