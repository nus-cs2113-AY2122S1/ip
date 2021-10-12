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

    /**
     * Gets full task description of a {@code ToDo} task and the task type symbol.
     *
     * @return a String containing the task type symbol, description and isDone status.
     */
    @Override
    public String getTaskDescription() {
        return "[T]" + super.getTaskDescription();
    }

    /**
     * Gets task information that matches the format of the {@code ToDo} tasks stored in duke.txt.
     *
     * @return a String in the format of duke.txt {@code ToDo} task entry.
     */
    @Override
    public String getTaskFileFormat() {
        String isDoneString = "0";

        if (isDone) {
            isDoneString = "1";
        }

        return "T | " + isDoneString + " | " + description;
    }

}
