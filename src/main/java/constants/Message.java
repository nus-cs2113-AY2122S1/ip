package constants;

import static commands.ListCommand.LIST_IS_EMPTY;

/**
 * Messages that do not belong to any Commands.
 */
public class Message {

    public static final String DIVIDER = "___________________________________________________________";
    public static final String INDENTATION = "      ";
    public static final String HELLO_MESSAGE_2 = "Hello! I'm Duke, your friendly neighbourhood task manager\n" +
            INDENTATION + "What can I do for you? :D\n" + INDENTATION  + "(type help if you're not sure what to do!)";
    public static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public static final String TYPE_SUITABLE_COMMAND_MESSAGE = "Sorry," +
            " I don't know what you mean, do type 'help' if you're unsure :)";
    public static final String FILE_WRITING_ERROR_MESSAGE = "Oops, something unexpected happened while " +
            "writing to file";
    public static final String FILE_READING_ERROR_MESSAGE = "Oops, something unexpected happened while " +
            "reading the file";
    public static final String PROMPT_TASK_NUMBER = "Please tell me which task you want to select :)";
    public static final String PROMPT_SENSIBLE_INDEX = "Please give a number between 1 and ";
    public static final String DEFAULT_ERROR_MESSAGE = "Oops, something went wrong!";
    public static final String FILE_ERROR_MESSAGE = "Oops, there might be something wrong with the file.";
    public static final String FILE_NOT_CREATED = "Looks like you don't have a file for your tasks, " +
            "let me create one now.....";
    public static final String DONE = "Done!";
    public static final String GETTING_TASK = "Getting your tasks.....";
    public static final String PROMPT_NUMBER = "Please give me an integer :)";

    /**
     * Asks the user to give a number between 1 and the total number of tasks.
     *
     * @param number Total number of tasks
     * @return Message
     */
    public static String giveSensibleRange(int number) {
        if(number < 1) {
            return LIST_IS_EMPTY;
        }
        return PROMPT_SENSIBLE_INDEX + number + " :)";
    }
}
