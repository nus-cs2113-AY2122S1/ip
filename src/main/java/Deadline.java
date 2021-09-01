public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String toString() {
        return super.toString() + " [D][ ] " + description + " (by: " + by + ")\n";
    }

    @Override
    public String getDescription() {
        return this.description + " (by: " + by + ")\n";
    }

}