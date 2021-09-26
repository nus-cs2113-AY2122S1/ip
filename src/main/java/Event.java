public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        setAt(at);
    }

    public void setAt(String atDate) {
        this.at = atDate;
    }

    @Override
    public String getType() {
        return "E";
    }

    @Override
    public String getWhen() {
        return this.at;
    }
}
