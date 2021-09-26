public class Deadline extends Task {

    protected String by;

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String toString() {
        return super.toString() + " [D][" + super.getStatusIcon() + "] " + description + " (by: " + by + ")\n";
    }

    @Override
    public String getDescription() {
        return this.description + " (by: " + by + ")\n";
    }

}