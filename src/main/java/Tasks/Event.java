package Tasks;

public class Event extends Task{
    protected String timing;

    public Event(String task, String timing) {
        super(task);
        this.timing = timing;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + timing + ")";
    }
}
