package duke.task;

public class ToDo extends Task {

    /**
     * Constructor of a to do. It takes in a description to be added to the task.
     * @param description Description of the to do task.
     */
    public ToDo(String description) {
        super(description, "T");
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
