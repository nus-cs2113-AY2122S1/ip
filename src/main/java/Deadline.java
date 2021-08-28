
public class Deadline extends Task {

    final private static String FLAG_TYPE = "[D]";

    private String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate.trim();
    }

    @Override
    public String getStatusIcon() {
        return FLAG_TYPE + super.getStatusIcon();
    }

    @Override
    public String getTaskInfo() {
        return getDescription() + " (by: " + dueDate + ")";
    }
}
