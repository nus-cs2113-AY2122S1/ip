public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return (this.getId() + 1) + ".[D]" + super.toString() + " (by: " + by + ")";
    }
}
