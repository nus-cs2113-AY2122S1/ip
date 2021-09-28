package duke.exceptions;

//used when done description is in the wrong format
public class DoneFormatException extends Exception {

    public final String DONE_FORMAT_RESPONSE =  "Kao doesn't get what you said (  ・ω・)?" +
            "\nThe following format must be used: done [index]";

    @Override
    public String getMessage(){
        return DONE_FORMAT_RESPONSE;
    }
}
