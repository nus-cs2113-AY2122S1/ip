package duke.task;

public class Event extends Task{
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String getTaskIcon() {
        return "E";
    }

    @Override
    public String toString() {
        return itemIndex + ". [" + this.getTaskIcon() + "]" + super.toString() + " at: " + at;
    }
}
