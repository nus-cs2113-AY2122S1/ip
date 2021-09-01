public class Event extends Task {
    private String at;

    public Event(String description, String at) {
        super(description, Task.TYPE_EVENT);
        this.at = at;
    }

    /**
     * Returns string in list entry format.
     * eg. [T][ ] task1 (at: Aug 6th 2-4pm)
     *
     * @return The formatted string.
     */
    @Override
    public String getListEntryString() {
        return String.format("%s (at: %s)", super.getListEntryString(), at);
    }
}
