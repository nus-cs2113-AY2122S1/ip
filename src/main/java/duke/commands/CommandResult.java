package duke.commands;

/**
 * Objects of this class are returned from the execution of a <code>Command</code>, and it contains the
 * feedback from the execution that can be displayed to the user via the user interface.
 */
public class CommandResult {

    private final String feedbackToUser;

    /**
     * Constructs a <code>CommandResult</code> from the upon finish executing the <code>Command</code>.
     *
     * @param feedbackToUser <code>String</code> that is used for display to the user on the user interface.
     */
    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }
}
