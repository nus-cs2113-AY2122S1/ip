public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description,Duke.TASK_TYPE_DEADLINE);
        this.by = by;
    }

    @Override
    public String getListEntryString() {
        return String.format("%s (by: %s)", super.getListEntryString(), by);
    }
}
