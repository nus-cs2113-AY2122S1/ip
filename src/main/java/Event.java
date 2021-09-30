public class Event extends Task {
    public String eventDate;
    public Event(String name, String eventDate) {
        super(name);
        this.eventDate = eventDate;
    }
    /**
     * Show the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + eventDate + ")";
    }
}