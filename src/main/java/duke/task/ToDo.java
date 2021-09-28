package duke.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        this(description);
        super.taskDone(isDone);
    }

    public String getIcon() {
        return "T";
    }

    @Override
    public String toString() {
        return "[" + getIcon() + "]"+ super.toString();
    }
}
