package commands;

import task.Task;

import java.util.ArrayList;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    /**
     * The feedback message to be shown to the user.
     * Contains a description of the execution result.
     */
    public final String feedbackToUser;

    private final ArrayList<Task> relevantTasks;

    /**
     * Used for commands that do not have a list of tasks in the result.
     * @param feedbackToUser description of execution result
     */
    public CommandResult (String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        relevantTasks = null;
    }

    /**
     * Used for commands that have a list of tasks in the result, such as ListCommand and FindCommand.
     * @param feedbackToUser description of execution result.
     * @param relevantTasks list of relevant tasks required for the result.
     */
    public CommandResult(String feedbackToUser, ArrayList<Task> relevantTasks) {
        this.feedbackToUser = feedbackToUser;
        this.relevantTasks = relevantTasks;
    }

    public ArrayList<Task> getRelevantTasks() {
        return relevantTasks;
    }
}
