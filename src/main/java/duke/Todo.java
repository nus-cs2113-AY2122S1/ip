package duke;

public class Todo extends Task {

    /**
     * Constructor to initialize task description.
     *
     * @param description Description stores the description of the task.
     */
    public Todo(String description) {
        super(description);
        this.taskType = TaskType.TODO;
    }

    /**
     * Returns the string in a particular String format.
     *
     * @return Task description in a particular format.
     */
    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }
}
