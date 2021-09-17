package kate.common;

public class Message {

    public static final String TEXT_INDENTATION = "    ";

    public static final String COMMAND_TODO = "todo [description]";
    public static final String COMMAND_DEADLINE = "deadline [description] /by [deadline]";
    public static final String COMMAND_EVENT = "event [description] /at [time frame]";
    public static final String COMMAND_DONE = "done [task number shown in list]";
    public static final String COMMAND_DELETE = "delete [task number]";
    public static final String COMMAND_FIND = "find [keyword]";
    public static final String COMMAND_LIST = "list";
    public static final String COMMAND_BYE = "bye";

    public static final String FAILURE_MESSAGE_EMPTY_TASK = Message.TEXT_INDENTATION
            + "Task list is empty!\n"
            + Message.TEXT_INDENTATION + "Please specify a task using the <help> page \n";
}
