public class Event extends Task {
    protected String at;
    protected String taskName;
    protected int index;

    //Constructor of Event object
    public Event(String description, String at, int index) {
        super(description);
        this.at = at;
        this.index = index;
    }

    //Getter of at var
    public String getAt() {
        return this.at;
    }

    //Setter of at var
    public void setAt(String at) {
        this.at = at;
    }

    //toString method
    public String toString() {
        taskName = description.substring(6, index - 1);
        return "[E][ ] " + taskName + " (at: " + at.substring(3) + ")";
    }
}