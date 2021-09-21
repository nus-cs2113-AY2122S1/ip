package duke.task;

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
