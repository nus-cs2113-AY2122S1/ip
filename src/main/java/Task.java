/**
 * This represents the individual Tasks in each element of Task[] list in Duke.java
 */
public class Task {

    protected String description;
    protected boolean isDone;
    private final static String TAB = "    ";

    /**
     * This function initialises the task input by user
     *
     * @param description description input by user
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * This function returns a relevant status icon
     * @return "X" if task is done, " " if it is not
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * This function marks a given task as done, and prints a message letting the user know
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * This function is a getter for task description
     * @return description
     */
    public String getDescription() {
        return description;
    }

}