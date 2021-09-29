package duke.tasks;

/**
 * Class representing all tasks inputted by the user.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initializes Task with given description and isDone = false.
     * @param description description of task from user input
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns "X" if isDone == true, else returns " ".
     * @return String of symbol representing task completion
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns description of the task.
     * @return String of description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Marks task as completed.
     * Sets isDone = true.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns the completion status of the task.
     * @return boolean isDone representing completion status
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns task as a formatted string with brackets and description.
     * @return string of task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

}
