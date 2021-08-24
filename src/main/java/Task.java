/**
 * This represents the individual Tasks in each element of Task[] list in Duke.java
 */
public class Task {
    /**
     * String description is the description of each task
     */
    protected String description;
    /**
     * boolean isDone is the representation whether a task is done or not
     */
    protected boolean isDone;

    /**
     * This function initialises the task input by user
     * @param description description input by user
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * This function returns "X" if task is done, " " if it is not
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * This function marks a given task as done, and prints a message letting the user know
     */
    public void markAsDone() {
        isDone = true;
        System.out.println("    Nice! I've marked this task as done:");
        System.out.format("         [%s] %s%n", getStatusIcon(), getDescription());
    }

    /**
     * This function returns the description of the task
     */
    public String getDescription() {
        return description;
    }

}