package duke.exceptions;

//used when deadline description is in the wrong format
public class DeadlineException extends Exception {

    public final String DEADLINE_RESPONSE = "Kao needs you to repeat that (。_ 。 )?" +
            "\nThe following format must be used: deadline [description] /by [date and time]";

    @Override
    public String getMessage() {
        return DEADLINE_RESPONSE;
    }
}
