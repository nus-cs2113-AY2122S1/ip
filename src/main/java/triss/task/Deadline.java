package triss.task;

public class Deadline extends Task{
    /** Represents the date this task should be done by */
    private String dueDate;

    /**
     * Creates a deadline with task type [D], and dueDate based on user's input.
     * @param name The name of the deadline.
     * @param dueDate The due date of the deadline.
     */
    public Deadline(String name, String dueDate) {
        super(name);
        this.dueDate = dueDate;
        this.typeOfTask = "[D]";
    }

    /**
     * Get the due date of the deadline.
     * @return Due date of the deadline.
     */
    public String getDueDate() {
        return dueDate;
    }

    /**
     * Returns the deadline in a human-readable format.
     * @return [Type of Task][Completion Status] [Name of Task] ([Due Date of Task])
     */
    public String printTask() {
        return this.getTypeOfTask() + this.getDoneStatusAsSymbol() + " "
                + this.getName() + " (" + this.getDueDate() + ")";
    }
}
