package tasks;

import java.util.ArrayList;

/**
 * Represents a generic task. A Task object is represented by a description and a boolean flag
 * representing whether the task is completed.
 */

public class Task {
    public static final String taskDoesNotExist = "The task ID does not exist!";

    private String description;
    private boolean isDone;

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

    /**
     * Checks if a task id is valid.
     *
     * @param id String ID of task to be checked.
     * @return a boolean value indicating if a task was valid.
     * @throws NumberFormatException If id was not a number or < 1 or > tasks.size()
     */
    public static boolean isValidTaskId(String id, ArrayList<Task> tasks) {
        int taskId;
        try {
            taskId = Integer.parseInt(id);
            if (taskId < 1 || taskId > tasks.size()) { //invalid task ID
                throw new NumberFormatException();
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
