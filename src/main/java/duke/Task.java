package duke;

public class Task {
    /**
     * Stores the task description.
     */
    public String description;
    public TaskType taskType;

    /**
     * Stores true if task is done and false otherwise.
     */
    protected boolean isDone;

    /**
     * Default Constructor to initialise the task description and task completion status.
     */
    public Task() {
        this.description = "";
        this.isDone = false;
        this.taskType = TaskType.NONE;
    }


    /**
     * Parameterized constructor to initialise the task description and task completion status.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns X if task is completed and space otherwise.Returns the task completion status of a particular task.
     *
     * @return Task Completion Status
     */
    public String getStatus() {
        if (this.isDone) {
            return "X";
        } else {
            return " ";
        }

    }

    /**
     * Marks a particular task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the task status in a particular String format.
     *
     * @return Task completion Status in a String format.
     */
    @Override
    public String toString() {
        String taskStatus = isDone ? "X" : " ";
        return ("[" + taskStatus + "] " + this.description);
    }

}
