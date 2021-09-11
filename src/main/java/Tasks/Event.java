package Tasks;

public class Event extends Task{
    protected String timing;

    public Event(String task, boolean isDone, String timing) {
        super(task, isDone, TaskTypes.EVENT);
        this.timing = timing;
    }

    @Override
    public String toString() {
        return super.toString() + " | at: " + timing ;
    }
}
