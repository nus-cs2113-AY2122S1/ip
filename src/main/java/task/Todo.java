package task;

public class Todo extends Task {
    /**
     * Class constructor with the specified task name.
     *
     * @param taskName The name of the Todo
     */
    public Todo(String taskName) {
        super(taskName);
    }

    /**
     * Class constructor with specified task name and isDone value.
     * Mainly used for creating a Todo using values loaded from file.
     *
     * @param taskName The name of the Todo
     * @param isDone The boolean of whether the Todo is done
     */
    public Todo(String taskName, boolean isDone) {
        super(taskName, isDone);
    }

    /**
     * Returns a String-formatted Todo for printing.
     *
     * @return The String-formatted Todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
