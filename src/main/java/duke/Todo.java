package duke;

/**
 * The todo subclass
 */
public class Todo extends Task {
    public Todo(String description, int index) {
        super(description, index);
        this.type = Type.T;
    }
}
