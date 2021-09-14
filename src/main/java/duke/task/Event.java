package duke.task;

public class Event extends Task {

    public Event(String description) {
        super(description);
    }

    @Override
    public String getType() {
        return ("E");
    }

    @Override
    public String getDescription() {
        int byPos = description.indexOf("/at");
        return (description.substring(0, byPos) + "(at: " + description.substring(byPos + 3).trim() + ")");
    }

}
