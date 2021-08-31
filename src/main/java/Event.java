public class Event extends Task{
    private String eventDate;
    public Event (String description, String eventDate) {
        super(description);
        this.eventDate = eventDate;
        System.out.println("  " + this.toString());
    }

    @Override
    public String toString() {
        String str = "[E]" + super.toString() +
                "(at:" + this.eventDate + ")";
        return str;
    }
}
