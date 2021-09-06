public class Event extends Task{
    protected String duration;

    public Event(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    public String listTask() {
        return "[E]" + super.listTask() + " (at: " + duration + ")";
    }
}
