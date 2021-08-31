public class Event extends Task{
    String time;

    public Event(String taskDescription, String timing) {
        super(taskDescription);
        this.time = timing;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
