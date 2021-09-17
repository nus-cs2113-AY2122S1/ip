package duke.tasks;

public class Task {
    public String description;
    public boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public void setDone() {
        this.isDone = true;
    }

    public String encode() {
        String PARTITION = " | ";
        String isDoneCode = (this.isDone) ? "1" : "0";

        return "T" + PARTITION + isDoneCode + PARTITION + this.description;
    }
}
