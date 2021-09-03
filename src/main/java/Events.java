public class Events extends Task {
    protected String time;

    public Events(String description, String time) {
        super(description);
        this.time = time;
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
