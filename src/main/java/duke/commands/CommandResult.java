package duke.commands;

/**
 * Objects of this class are returned from the execution of a {@code Command}, and it contains the
 * feedback from the execution that can be displayed to the user via the user interface.
 */
public class CommandResult {

    private final String feedbackToUser;

    /**
     * Constructs a {@code CommandResult} upon finish executing the {@code Command}.
     *
     * @param feedbackToUser string display to the user on the user interface.
     */
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }
}
