public class Event extends Task {
    protected String dateAndTime;
    protected static final String EVENT_LOGO = "[E] ";

    public Event(String description) {
        super(description);
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    @Override
    public String toString() {
        return EVENT_LOGO + super.toString() + " (at: " + dateAndTime + " )";
    }
}
