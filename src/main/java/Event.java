public class Event extends Deadline {
    public Event(String description, String time) {
        super(description, time);
        this.type = 'E';
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
