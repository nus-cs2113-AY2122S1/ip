package duke.manager.task;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        if (at.equals("") || at.equals("???")) {
            this.at = "???";
        } else {
            this.at = at;
        }
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    @Override
    public String getTaskDescriptionWithStatus() {
        return "[E]" + super.getTaskDescriptionWithStatus() + " (at: " + at + ")";
    }
}
