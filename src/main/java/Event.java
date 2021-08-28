public class Event extends Task {
    protected String time;
    protected int keywordIdx = 0;

    public String extractTime(String inp) {
        this.keywordIdx = inp.indexOf("/");
        return inp.substring(keywordIdx + 4);
    }

    public String getTimeString() {
        return "(at: " + this.time + ")";
    }

    public Event(String eventDescription) {
        super(eventDescription, "event");
        this.time = extractTime(eventDescription);
        super.description = eventDescription.substring(6, this.keywordIdx) + getTimeString();
    }
}
