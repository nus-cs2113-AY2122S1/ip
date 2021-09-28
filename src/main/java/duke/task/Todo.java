package duke.task;

public class Todo extends Task {

    /**
     * Toto class constructor.
     *
     * @param description Task description.
     * @param taskType    Task type 'T'.
     */
    public Todo(String description, char taskType) {
        super(description, taskType);
    }

    /**
     * Displays the task in a formatted way.
     *
     * @return Formatted task display.
     */
    @Override
    public String toString() {
        return " [" + getTaskType() + "][" + getStatusIcon() + "] " + super.toString();
    }

    /**
     * Formats the task to the file format
     *
     * @return Formatted task display to file format.
     */
    @Override
    public String toFileFormat() {
        String isDoneString = isDone ? "1" : "0";
        return getTaskType() + " | " + isDoneString + " | " + super.toFileFormat();
    }
}
