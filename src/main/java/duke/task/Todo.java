package duke.task;

public class Todo extends Task {
    /**
     * Initializes task description and set the initial
     * status to "not done".
     *
     * @param description task description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Initializes task description and sets the initial
     * status according to the given parameter.
     *
     * @param description task description
     * @param isDone initial status
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String serialize() {
        return TODO + " | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public String toString() {
        return "[" + TODO + "]" + super.toString();
    }
}
