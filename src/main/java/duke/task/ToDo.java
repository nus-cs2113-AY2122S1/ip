package duke.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public String getIcon() {
        return "[T]";
    }
    @Override
    public String toString() {
        return getIcon() + super.toString();
    }
}
