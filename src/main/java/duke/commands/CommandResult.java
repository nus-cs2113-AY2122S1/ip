package duke.commands;

/**
 * Objects of this class are returned from the execution of a <code>Command</code>, and it contains the
 * feedback from the execution that can be displayed to the user via the user interface.
 */

public class CommandResult {

    private final String feedbackToUser;

    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }
}
