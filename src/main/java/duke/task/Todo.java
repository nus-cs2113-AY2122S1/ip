package duke.task;

public class Todo extends Task {
    protected String type;

    /**
     * Constructor for Todo.
     *
     * @param isDone Whether task has been done or not.
     * @param taskName Name of task.
     */
    public Todo(boolean isDone, String taskName) {
        super(taskName);
        this.isDone = isDone;
        this.type = "T";
    }

    /**
     * Returns information about task in a sensible form.
     *
     * @return Information about task in a string.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a String of information about task in format readable by load() method.
     *
     * @param DELIMITER Delimiter separating information in return String.
     * @return String of task information.
     */
    @Override
    public String toSaveFile(String DELIMITER) {
        return "T" + super.toSaveFile(DELIMITER);
    }

}
