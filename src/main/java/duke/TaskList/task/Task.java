package duke.TaskList.task;

import duke.TaskList.TaskManager;

public class Task {

    protected String description;
    protected boolean isDone;
    protected String taskType = "";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = "";
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    public String getTaskType() {
        return this.taskType;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toString() {
        return this.description;
    }

    public String toDataFormat() {
        return this.taskType + " | " + (getStatusIcon().equals(TaskManager.STATUS_DONE) ? "1" : "0")
                + " | " + this.description;
    }
}
