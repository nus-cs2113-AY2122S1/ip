package duke.task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Re-formats the task type, status and description into the proper format for the txt file.
     *
     * @return task details in format for txt file.
     */
    public String getTaskDetailsInFileFormat() {
        return "T | " + isDone + " | " + description;
    }

    /**
     * Overwrites the default method with a custom print message instead.
     *
     * @return the task type, status and details.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
