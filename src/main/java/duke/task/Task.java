package duke.task;

/**
 * Shows the instances and methods of a task.
 */
public class Task {

    protected String taskName;
    protected boolean isDone;
    protected String taskType;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public String getTaskStatus() {
        return (isDone ? "[X] " : "[ ] ");
    }

    public String getTaskType(){
        return taskType;
    }

    public String getTaskDescription() {
        return this.taskName;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return getTaskStatus() + this.taskName;
    }
}