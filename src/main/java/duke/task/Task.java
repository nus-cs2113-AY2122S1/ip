package duke.task;

public class Task {

    /**
     * Status of whether the task is completed
     */
    private boolean isDone;
    private String description;


    public Task(String description) {
        this.description = description.trim();
        isDone = false;
    }

    public boolean getDoneStatus() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get all information of the task.
     *
     * @return String containing all information of the task.
     */
    public String getTaskInfo() {
        return description;
    }

    /**
     * Returns a string depending on the isDone status. If the task isDone is true, "X" is return. Else " " is return.
     *
     * @return isDone status icon
     */
    public String getStatusIcon() {
        String isDoneFlag = "[ ]";
        if (isDone) {
            isDoneFlag = "[X]";
        }
        return isDoneFlag;
    }

}
