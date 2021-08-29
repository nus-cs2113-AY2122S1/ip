public class Deadline extends Task {

    private static final String SYMBOL = "D";
    private String taskDue;

    public Deadline(String description, String taskDue) {
        super(description);
        this.taskDue = taskDue;
    }

    @Override
    public String toString() {
        return "[" + SYMBOL + "]" + super.toString() + " (by: " + taskDue + ")";
    }
}
