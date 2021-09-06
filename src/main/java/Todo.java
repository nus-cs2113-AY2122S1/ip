public class Todo extends Task {

    /**
     * Constructor to initialize task description.
     *
     * @param description Description stores the description of the task.
     */
    Todo(String description) {
        super(description);
        this.taskType = TaskType.TODO;
    }

    /**
     * Returns the string in a particular format.
     *
     * @return Task description in a particular format.
     */
    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }
}
