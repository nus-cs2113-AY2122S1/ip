public class Todo extends Task {

    /**
     * Todo constructor.
     *
     * @param description Short description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Gets the description of the task.
     *
     * @return String containing task description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
