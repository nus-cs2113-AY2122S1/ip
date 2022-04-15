package duke.tasks;

public class Deadline extends Task {

    private String type = "[D]";
    private String dueDate;

    /**
     * Represents a Deadline made by the user.
     *
     * @param description Description of Deadline.
     * @param dueDate Due date of Deadline.
     */

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    public String getType(){
        return this.type;
    }

    public String getDueDate(){
        return this.dueDate;
    }

    public String toString() {
        return type + super.toString() + " (by:" + dueDate + ")";
    }
}
