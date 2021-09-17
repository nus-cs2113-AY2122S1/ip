public class Event extends Task{
    private String eventDate;

    public Event (String description, String eventDate) {
        super(description);
        this.eventDate = eventDate;
    }

    public String getEventDate() {
        return this.eventDate;
    }

    @Override
    public String toString() {
        String str = "[E]" + super.toString() +
                "(at:" + this.eventDate + ")";
        return str;
    }
}
