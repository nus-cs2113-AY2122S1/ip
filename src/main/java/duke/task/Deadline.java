package duke.task;


public class Deadline extends Task {
    private static String SYMBOL = "D";
    private String dueDate;

    public Deadline(String name, String dueDate) {
        super(name);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "[" + SYMBOL + "]" + super.toString() + " (by: " + dueDate + ")";
    }
}
