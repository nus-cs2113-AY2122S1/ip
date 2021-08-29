public class Event extends Task {

    protected String date;

    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return " [E]" + super.toString() + " (at: " + date + ")";
    }
}
