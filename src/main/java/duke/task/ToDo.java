package duke.task;

public class ToDo extends Task {

    protected String type = "T";

    /**
     * Constructor to create ToDo object.
     *
     * @param description ToDo description.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Retrieve the task type.
     *
     * @return Task type: "T".
     */
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}