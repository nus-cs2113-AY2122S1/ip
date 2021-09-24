package commands;

import task.Task;

import java.util.ArrayList;

public class CommandResult {

    public final String feedbackToUser;

    private final ArrayList<Task> relevantTasks;

    // for all commands except LIST or FIND
    public CommandResult (String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        relevantTasks = null;
    }

    // for LIST or FIND
    public CommandResult(String feedbackToUser, ArrayList<Task> relevantTasks) {
        this.feedbackToUser = feedbackToUser;
        this.relevantTasks = relevantTasks;
    }

    public ArrayList<Task> getRelevantTasks() {
        return relevantTasks;
    }
}
