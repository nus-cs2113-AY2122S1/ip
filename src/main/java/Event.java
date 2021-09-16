public class Event extends Task{
    protected String at;
    protected String originalInput;

    public Event (String description, String at, String originalInput) {
        super(description);
        this.at = at;
        this.originalInput = originalInput;
    }

    public String getOriginalInput() {
        return originalInput;
    }

    public void setAt(String at) {
        this.at = at;
    }
    public String getAt() {
        return at;
    }

    public String getType() {
        return "E";
    }

    public String toString() {
        return "[E]" + super.toString() + " (" + at + ")";
    }
}