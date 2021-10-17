package task.type;

public class Task {
    protected String description;
    protected boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * To get icon depending on if task is done or not.
     *
     * @return [X] if done or [ ] otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); //return tick or X symbols
    }
    public String getTaskType(){
        return "To be overridden by subclass";
    }

    /**
     * To get task description
     *
     * @return description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets status of task
     *
     * @return isDone which reflects if task is done or not.
     */
    public boolean getDoneStatus(){
        return isDone;
    }

    /**
     * Puts true as the value of boolean isDone.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Prints the tasks in display format
     *
     * @return done status icon + description
     */
    @Override
    public String toString() {
        return getStatusIcon() + " " + description;
    }
}