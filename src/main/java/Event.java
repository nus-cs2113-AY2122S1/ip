public class Event extends Task {

    private final String symbol = "E";
    private String dateAndTime;

    public Event(String description, String dateAndTime) {
        super(description);
        this.dateAndTime = dateAndTime;
    }

    @Override
    public String toString() {
        return "[" + symbol + "]" + super.toString() + " (at: " + dateAndTime + ")";
    }
}
