public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return (this.getId() + 1) + ".[E]" + super.toString() + " (at: " + at + ")";
    }
}
