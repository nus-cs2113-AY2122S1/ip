public class Deadline extends Task {

    /** Date and time of deadline. */
    private String by;

    public Deadline(String description, String by) {
        super(description);
        super.setType("D");
        this.by = by;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " (by: " + getBy() + ")";
    }

    public String getBy() {
        return by;
    }
}
