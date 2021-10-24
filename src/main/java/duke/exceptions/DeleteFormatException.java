package duke.exceptions;

//used when delete description is in the wrong format
public class DeleteFormatException extends Exception {

    public final String DELETE_FORMAT_RESPONSE =  "Could you rephrase that for Kao (￣▽￣*)?" +
            "\nThe following format must be used: delete [index]";

    @Override
    public String getMessage(){
        return DELETE_FORMAT_RESPONSE;
    }
}
