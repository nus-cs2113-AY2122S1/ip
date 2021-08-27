public class Event extends Task {
    protected String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
        this.type = 'E';
    }

    @Override
    public String toString() {
        if (isDone) {
            return "[" + type + "][X] " + description + " (" + time + ")";
        } else {
            return "[" + type + "][ ] " + description + " (" + time + ")";
        }
    }
}
