package duke.task;

public class Deadline extends Task {
    protected String by;

    /**
     * Deadline class constructor.
     *
     * @param description Task description.
     * @param taskType    Task type 'D'.
     * @param by          Due date and time for task.
     */
    public Deadline(String description, char taskType, String by) {
        super(description, taskType);
        this.by = by;
    }

    /**
     * Displays the task in a formatted way.
     *
     * @return Formatted task display.
     */
    @Override
    public String toString() {
        return " [" + getTaskType() + "][" + getStatusIcon() + "] " + super.toString() + by;
    }

    /**
     * Formats the task to the file format
     *
     * @return Formatted task display to file format.
     */
    @Override
    public String toFileFormat() {
        String isDoneString = isDone ? "1" : "0";
        return getTaskType() + " | " + isDoneString + " | " + super.toFileFormat() + " /"
                + by.replace("(", "".replace(")", ""));
    }
}
