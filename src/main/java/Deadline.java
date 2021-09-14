public class Deadline extends Task {

    private String by;

    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    public String getDataStorageString() {
        return 'D' + super.getDataStorageString()
                + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() +
                " (by: " + by + ")";
    }
}
