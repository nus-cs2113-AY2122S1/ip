public class Event extends Task {
    private String to;

    public Event(String description, String to) {
        super(description);
        setTo(to);
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + getTo() + ")";
    }

    @Override
    protected String getType() {
        return "event";
    }
}
