package shikabot.task;

public class Event extends Task{

    private String at;

    public Event(String name, String at) {
        super(name);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    public String getType() {
        return "E";
    }

    public String getAtBy() {
        return at;
    }

}
