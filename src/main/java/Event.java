public class Event extends Task implements Timetable {
    private String date;

    Event(String description, String date) {
        super(description);
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String getDescription() {
        return super.description + " (at: " + this.getDate() + ")";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.getDate() + ")";
    }
}
