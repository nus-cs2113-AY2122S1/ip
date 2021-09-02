public class Event extends Task{
    protected String eventDateTime;

    public Event(String description, String eventDateTime) {
        super(description);
        this.eventDateTime = eventDateTime.substring(eventDateTime.indexOf(" ") + 1);
    }

    @Override
    public String getDescription() {
        return description + "(at: " + eventDateTime + ")";
    }

    @Override
    public String getSymbol() {
        return "E"; // mark Events with an "E"
    }
}
