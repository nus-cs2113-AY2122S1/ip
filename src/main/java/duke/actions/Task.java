package duke.actions;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void updateIsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getTaskType() {
        return ("");
    }

    public String getDeadline() {
        return ("");
    }

    public void printMarkAsDoneMessage(int taskNumber) {
        System.out.println("Nice! I've marked this task as done:\n" + (taskNumber + 1) + ".[" + getTaskType() + "]" + "[" + getStatusIcon() + "] " + description);
    }

    public void printTaskList(int listIndex) {
        System.out.println(listIndex + ".[" + getTaskType() + "]" + "[" + getStatusIcon() + "] " + description);
    }

    public void printTaskAddedMessage() {
        System.out.println("I can do that! I have added [" + description + "] to your task list!");
    }
    //...
}
