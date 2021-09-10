package duke.task;

public class Todo extends Task{

    public static final String OPEN_BRACKET = "[";
    public static final String CLOSE_BRACKET = "]";

    public Todo(String description) {
        super(description);
        this.typeIcon = "T";
    }

    @Override
    public String toString() {
        return OPEN_BRACKET + typeIcon + CLOSE_BRACKET + super.toString();
    }
}
