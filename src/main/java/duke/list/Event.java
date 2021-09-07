package duke.list;

public class Event extends Task{
    protected String at;

    public Event(String description, int noOfTask, String at) {
        super(description, noOfTask);
        setAt(at);
    }

    public void setAt(String at) {
        this.at = at;
    }

    @Override
    public String toString() {
        return ("[E]" + super.toString() + " (at: " + at + ")");
    }
}
