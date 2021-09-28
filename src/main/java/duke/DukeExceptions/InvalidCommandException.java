package duke.DukeExceptions;

public class InvalidCommandException extends DukeException {
    private String thisError;

    /**
     * Default Constructor.
     */
    public InvalidCommandException() {
        this.thisError = ("\t______________________________________________________________________\n"
                + "\tOOOPS!!! I'm sorry, but I don't know what that means. EPIC SADS :-(\n"
                + "\t______________________________________________________________________\n"
                + "\tKey in [help] to list available commands\n"
                + "\t______________________________________________________________________\n\t");
    }

    /**
     * Constructor with custom errorMessage.
     *
     * @param errorMessage error Message to train Elgle.
     */
    public InvalidCommandException(String errorMessage) {
        this.thisError = ("\t______________________________________________________________________\n\t"
                + errorMessage
                + "\n\t______________________________________________________________________\n\t");
    }

    public String toString() {
        return (this.thisError);
    }
}
