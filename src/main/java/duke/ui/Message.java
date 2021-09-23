package duke.ui;

public abstract class Message {
    public static final String EXPRESSION_BLUSH = "(//ω//)";

    public static final String EXPRESSION_JOY = "(≧▽≦)";

    public static final String EXPRESSION_SORRY = "(>﹏<) Gomennasai!";

    public static final String ERROR_CREATING_FILE =
            EXPRESSION_SORRY + " I can't create a new file.";

    public static final String ERROR_DESERIALIZING_DATA =
            EXPRESSION_SORRY + " The data is in invalid format. I can't deserialize it.";

    public static final String ERROR_INVALID_COMMAND =
            EXPRESSION_SORRY + " I don't understand your command.";

    public static final String ERROR_INVALID_DATE =
            EXPRESSION_SORRY + " The date is in invalid format.";

    public static final String ERROR_NOT_NUMBER =
            EXPRESSION_SORRY + " I can't convert it to a number.";

    public static final String ERROR_RETRIEVING_DATA =
            EXPRESSION_SORRY + " I can't retrieve any data. Creating new file..";

    public static final String ERROR_SAVING_DATA =
            EXPRESSION_SORRY + " Something went wrong. I can't save the data.";

    public static final String ERROR_TASK_NUMBER =
            EXPRESSION_SORRY + " The task number is invalid.";
}
