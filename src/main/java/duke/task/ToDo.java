package duke.task;

/**
 * Represents a {@code ToDo} task (Subclass of {@code Task}).
 */
public class ToDo extends Task {

    /**
     * Constructor that takes in {@code description} only.
     *
     * @param description description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructor that takes in both {@code description} and {@code isDone}.
     *
     * @param description description of the ToDo task.
     * @param isDone      whether the ToDo task is done.
     */
    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getTaskDescription() {
        return "[T]" + super.getTaskDescription();
    }

    @Override
    public String getTaskFileFormat() {
        String isDoneString = "0";

        if (isDone) {
            isDoneString = "1";
        }

        return "T | " + isDoneString + " | " + description;
    }

}
