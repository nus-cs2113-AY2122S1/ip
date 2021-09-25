package commands;

import task.Task;
import tasklist.TaskList;

public class CommandResult{
    public final String feedbackToUser;
    public final Task task;
    public final TaskList tasks;
    public final PrintOptions type;

    public CommandResult(String feedbackToUser, TaskList tasks,PrintOptions type) {
        this.feedbackToUser = feedbackToUser;
        this.task = null;
        this.tasks = tasks;
        this.type = type;
    }

    public CommandResult(String feedbackToUser,PrintOptions type) {
        this.feedbackToUser = feedbackToUser;
        this.task = null;
        this.tasks = null;
        this.type = type;
    }

    public CommandResult(String feedbackToUser, Task task,PrintOptions type) {
        this.feedbackToUser = feedbackToUser;
        this.task = task;
        this.tasks = null;
        this.type = type;
    }

}
