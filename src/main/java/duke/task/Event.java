package duke.task;

public class Event extends Task{
    protected String duration;

    public Event(String description, String duration) {
        super(description);
        this.duration = duration;
    }

    @Override
    public String listTask() {
        return "[E]" + super.listTask() + " (at: " + duration + ")";
    }
}
