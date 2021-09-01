public class Event extends Task{
    protected String type = "E";
    protected String at = "";

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public String toString() {
        return "[" + this.type + "]" + "[" + this.done + "] " + description.split("/at ")[0] + " (at: " + at + ")";
    }
}
