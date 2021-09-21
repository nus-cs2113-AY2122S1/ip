package duke.manager.task;

/**
 * <h1>Task</h1>
 * Represents any kind of task. A <code>Task</code> object is represented by a String of the task description
 * <code>taskDescription</code> and a boolean of the status of the task <code>isDone</code>.
 */
public class Task {

    protected String taskDescription;
    protected boolean isDone;

    public Task(String description) {
        this.taskDescription = description;
        isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Returns the task description with its status in a more reader friendly manner
     */
    public String getTaskDescriptionWithStatus() {
        // mark done task with X
        return (isDone ? "[X] " : "[ ] ") + taskDescription;
    }

    public void setDone() {
        isDone = true;
    }

    public boolean isInDateTimeFormat() {
        // If called on tasks without date-time arguments, assumed to be true
        return true;
    }
}
