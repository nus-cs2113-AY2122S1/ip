public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description, Task.TYPE_DEADLINE);
        this.by = by;
    }

    /**
     * Returns string in list entry format.
     * eg. [T][ ] task1 (by: Sunday)
     *
     * @return The formatted string.
     */
    @Override
    public String getListEntryString() {
        return String.format("%s (by: %s)", super.getListEntryString(), by);
    }
}
