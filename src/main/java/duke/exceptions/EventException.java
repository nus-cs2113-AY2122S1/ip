package duke.exceptions;

//used when event description is in the wrong format
public class EventException extends Exception {

    public final String EVENT_RESPONSE = "Kao doesn't understand you (  ⊙_⊙)?" +
            "\nThe following format must be used: event [description] /at [date and time]";

    @Override
    public String getMessage(){
        return EVENT_RESPONSE;
    }
}
