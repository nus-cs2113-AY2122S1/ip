public class Event extends Task {
    //maybe inherit from deadline?
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
