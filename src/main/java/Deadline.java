public class Deadline extends Task {
    protected String dueDate;

    /**
     * Class deadline constructor.
     *
     * @param description Short description of the task.
     * @param dueDate     Date that the task has to be completed by.
     */
    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * Gets the description and due date of the task.
     *
     * @return String containing task description and due date.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate + ")";
    }
}
