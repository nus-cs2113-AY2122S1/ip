package duke.tasks;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public String getStatusIcon() {
        return "[T]" + (isDone ? "[X]" : "[ ]");
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + description;
    }

    public void setDone() {
        this.isDone = true;
    }
}
