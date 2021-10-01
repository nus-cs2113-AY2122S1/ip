package duke.task;

/**
 * The Deadline class is a subclass of the Task class.
 * It is a specific task that also contains a dateline (<code>endDate</code>).
 */
public class Deadline extends Task{
    protected String endDate;

    public Deadline(String description, String endDate) {
        super(description);
        this.endDate = endDate;
    }

    @Override
    public String listTask() {
        return "[D]" + super.listTask() + " (by: " + endDate + ")";
    }

    @Override
    public String getIcon() {
        return "D";
    }

    public String getDate() {
        return endDate;
    }
}
