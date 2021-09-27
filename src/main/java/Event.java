public class Event extends Task{
    protected String at;

    public Event (String description, String at) {
        super(description);
        this.at = at;
    }

    public void setAt(String at) {
        this.at = at;
    }
    public String getTime() {
        return this.at;
    }
    public String getType() {
        return "E";
    }
    public String toString() {
        return "[E]" + super.getStatus() + super.getDescription() + " (at:" + at + ")";
    }
}