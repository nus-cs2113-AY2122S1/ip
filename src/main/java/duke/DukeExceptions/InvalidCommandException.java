package duke.DukeExceptions;

public class InvalidCommandException extends DukeException {
    private String thisError;

    public InvalidCommandException() {
        this.thisError = ("\t____________________________________________________________\n"
                + "\tOOOPS!!! I'm sorry, but I don't know what that means. EPIC SADS :-(\n"
                + "\t____________________________________________________________\n"
                + "\tKey in [help] to list available commands\n"
                + "\t____________________________________________________________\n\t");
    }

    public InvalidCommandException(String errorMessage) {
        this.thisError = ("\t____________________________________________________________\n\t"
                + errorMessage
                + "\n\t____________________________________________________________\n\t");
    }
    public String toString() {
        return (this.thisError);
    }
}
