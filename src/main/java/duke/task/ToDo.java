package duke.task;

public class ToDo extends Task {

    private static final String SYMBOL = "T";
    private static final String SEPARATOR = " | ";

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[" + SYMBOL + "]" + super.toString();
    }

    @Override
    public String toData() {
        return SYMBOL + SEPARATOR + (isDone ? 1 : 0) + SEPARATOR + description;
    }
}
