package kate.common;

public class Message {

    public static final String TEXT_INDENTATION = "    ";

    public static final String COMMAND_TODO = "todo [description]";
    public static final String COMMAND_DEADLINE = "deadline [description] /by [yyyy-mm-dd]";
    public static final String COMMAND_EVENT = "event [description] /at [yyyy-mm-dd]";
    public static final String COMMAND_DONE = "done [task number shown in list]";
    public static final String COMMAND_DELETE = "delete [task number]";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_BYE = "bye";

    public static final String FAILURE_MESSAGE_EMPTY_TASK = TEXT_INDENTATION
            + "Task list is empty!\n"
            + TEXT_INDENTATION + "Please specify a task using the <help> page \n";
    public static final String FAILURE_PARSE_DATE = TEXT_INDENTATION
            + "I don't understand the date format!\n"
            + TEXT_INDENTATION + "Please enter date in [yyyy-mm-dd] format\n";

}
