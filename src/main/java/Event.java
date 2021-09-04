public class Event extends Deadline {
    protected String time;

    public Event(String description, String time) {
        super(description, time);
        this.type = 'E';
    }
}
