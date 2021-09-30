package duke.task;

public class Todo extends Task {
    private static final TaskType taskType = TaskType.TODO;

    /**
     * Todo constructor
     *
     * @param title Title of todo
     */
    public Todo(String title) {
        super(title);
    }

    /**
     * @return Type of Task
     */
    @Override
    public TaskType getTaskType() {
        return taskType;
    }

    /**
     * @return String representation of Todo for display
     */
    @Override
    public String toString() {
        String SYMBOL = "T";
        return "[" + SYMBOL + "]" + super.toString();
    }

    /**
     * @return Null since Todo does not have any time associated with it
     */
    @Override
    public String getStandardTime() {
        return null;
    }

    /**
     * @return Null since Todo does not have any time associated with it
     */
    @Override
    public String getTime() {
        return null;
    }
}
