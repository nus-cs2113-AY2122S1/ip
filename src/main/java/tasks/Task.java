package tasks;

import java.util.ArrayList;

/**
 * Represents a generic task. A Task object is represented by a description and a boolean flag
 * representing whether the task is completed. It is inherited by all other tasks.
 */

public class Task {
    public static final String taskDoesNotExist = "The task ID does not exist!";
    public static final String noTasks = "You have no tasks in your list!";
    public static final String hereAreYourTasks = "Here are the tasks in your list:\n";

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + getDescription();
    }

    public String toFile() {
        return isDone + "|" + description;
    }
}
