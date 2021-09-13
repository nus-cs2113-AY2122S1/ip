package duke.task;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    @Override
    public String getTaskDescription() {
        return "[E]" + super.getTaskDescription() + " (at: " + at + ")";
    }

    @Override
    public String getTaskFileFormat() {
        String isDoneString = "0";

        if (isDone) {
            isDoneString = "1";
        }

        return "E | " + isDoneString + " | " + description + " | " + at;
    }

}
