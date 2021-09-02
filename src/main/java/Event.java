public class Event extends Task{
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String getIcon() {
        return "[E]";
    }
    public String getAt() {
        return "(at:" + at + ")";
    }

    @Override
    public String toString() {
        return getIcon() + super.toString() + "(at:" + at + ")";
    }
}
