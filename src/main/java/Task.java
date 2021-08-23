public class Task {
    private String description;
    private boolean isDone;

    /**
     * Task is class that contains a description and status of the task.
     * The status is represented by a [X] for done and a [ ] for not done and is displayed
     * before the description. E.g. 1. [ ] buy book
     *
     * @param description   description or name of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns "X" or " " depending on whether the task is completed.
     * "X" for completed, " " for not completed
     *
     * @return the icon representing the status
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the description of the task
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status of the task
     *
     * @return is done
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Marks the task as done by changing the boolean isDone to true.
     */
    public void markDone() {
        isDone = true;
    }
}
