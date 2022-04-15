public class Event extends Task{
    private final String EVENT_TASK_SIGN = "[E]";
    private String eventDate;

    public Event (String description, String eventDate) {
        super(description);
        this.eventDate = eventDate;
    }

    /**
     * @return Event date
     * */
    public String getEventDate() {
        return this.eventDate;
    }

    @Override
    public String toString() {
        String str = EVENT_TASK_SIGN + super.toString() +
                "(at:" + this.eventDate + ")";
        return str;
    }
}
