package duke;

public class InvalidEventTimeException extends Exception {
    public String getMessage() {
        return "OPS! Please add time for event using the keyword '/at'!!";
    }
}
