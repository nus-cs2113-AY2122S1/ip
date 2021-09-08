package duke;

public class InvalidDeadlineTimeException extends Exception {
    public String getMessage() {
        return "Ops! Please add time for Deadline using the keyword '/by'!";
    }
}
