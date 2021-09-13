package duke.task;

/**
 * Represents a task input by the user.
 * Is superclass of Deadline, Event and ToDo.
 */
public class Task {
    public String taskDescription;
    public boolean isDone;

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

    /**
     * Displays the current task as well as its status
     * @return A string in the format "[ ] (taskDescription)" The box will be [X] if the task is completed
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + taskDescription;
    }

    /**
     * Formats the task into the supported format for file operations
     * @return A string in the format "{X, },description"
     */
    public String toFile() {
        return getStatusIcon() + "," + taskDescription;
    }
}
