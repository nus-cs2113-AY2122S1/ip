public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

}