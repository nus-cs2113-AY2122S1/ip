package duke.exception;

public class DateError extends DukeException {
    public DateError() {
        this.errorMessage = "OH NO! There was an error in getting the date!";
    }
}
