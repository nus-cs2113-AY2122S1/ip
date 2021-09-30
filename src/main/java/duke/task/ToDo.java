package duke.task;

/**
 * An extension of Task class.
 * This class only has a description field.
 * A user is expected to use this if their task is not urgent or not specific.
 */
public class ToDo extends Task {
    /**
     * Constructs a ToDo instance.
     *
     * @param description The description of a ToDo given by user.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
