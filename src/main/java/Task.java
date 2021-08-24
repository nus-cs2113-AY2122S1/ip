/**
 * Represents a task input by the user
 */
public class Task {
    protected String taskDescription;
    protected boolean isDone;

    /**
     * Creates a task and assigns the input to taskDescription
     * @param taskDescription A String that the user input
     */
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        isDone = false;
    }

    /**
     * Retrieves the completion status of a specified task
     * @return "X" if task is completed, " " otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); //Mark completed tasks with an X
    }

    /**
     * Sets boolean isDone to true
     */
    public void markAsDone() {
        isDone = true;
    }
}
