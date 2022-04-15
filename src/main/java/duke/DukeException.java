package duke;

public class DukeException extends Exception{
    public String error;
    public DukeException(String error){
        this.error = error;
    }
}
