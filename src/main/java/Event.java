public class Event extends Task {
    private String at;

    public Event(String description, String at) {
        super(description,Duke.TASK_TYPE_EVENT);
        this.at = at;
    }

    @Override
    public String getListEntryString() {
        return String.format("%s (at: %s)", super.getListEntryString(), at);
    }
}
