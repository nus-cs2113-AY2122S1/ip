package todo;

public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        super.setType("e");
    }
    
    public String getDate() {
        return at;
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

}