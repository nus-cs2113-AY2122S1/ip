public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        if (at.equals("")) {
            this.at = "???";
        } else {
            this.at = at;
        }
    }

    public String getBy() {
        return at;
    }

    public void setBy(String by) {
        this.at = at;
    }

    @Override
    public String getTaskDescriptionWithStatus() {
        return "[E]" + super.getTaskDescriptionWithStatus() + " (at: " + at + ")";
    }
}
