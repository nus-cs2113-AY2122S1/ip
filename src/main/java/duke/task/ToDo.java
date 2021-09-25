package duke.task;

public class ToDo extends Task {
    public ToDo(String name) {
        super(name);
    }

    public String getTaskSymbol() {
        return "[T]";
    }

    public String toString() {
        return (getTaskSymbol() + getStatusSymbol() + " " + description);
    }

    @Override
    public String toStringForSave() {
        return toString();
    }
}
