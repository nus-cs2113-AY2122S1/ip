package duke.task;

public class Todo extends Task{

    public static final String OPEN_BRACKET = "[";
    public static final String CLOSE_BRACKET = "]";
    public static final String TODO_ICON = "T";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return OPEN_BRACKET + TODO_ICON + CLOSE_BRACKET + super.toString();
    }
}
