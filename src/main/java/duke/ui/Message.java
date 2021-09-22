package duke.ui;

public abstract class Message {
    public static final String SAD_EXPRESSION = ">﹏< Gomennasai!";

    public static final String ERROR_CREATING_FILE =
            SAD_EXPRESSION + " I can't create a new file.";

    public static final String ERROR_DESERIALIZING_DATA =
            SAD_EXPRESSION + " The data is in invalid format. I can't deserialize it.";

    public static final String ERROR_INVALID_COMMAND =
            SAD_EXPRESSION + " I don't understand your command.";

    public static final String ERROR_NOT_NUMBER =
            SAD_EXPRESSION + " I can't convert it to a number.";

    public static final String ERROR_RETRIEVING_DATA =
            SAD_EXPRESSION + " I can't retrieve any data. Creating new file..";

    public static final String ERROR_SAVING_DATA =
            SAD_EXPRESSION + " Something went wrong. I can't save the data.";

    public static final String ERROR_TASK_NUMBER =
            SAD_EXPRESSION + " The task number is invalid.";
}
