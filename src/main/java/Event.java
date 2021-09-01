public class Event extends Task {

    protected String on;

    public Event(String description, String on) {
        super(description);
        this.on = on;
    }

    @Override
    public String getDescription() {
        return "[E]" + super.getDescription() + " (on: " + on + ")";
    }
}
