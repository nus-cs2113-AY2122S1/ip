public class Event extends Task {
    protected String at;
    protected String taskName;
    protected int index;

    public Event(String description, String at, int index) {
        super(description);
        this.at = at;
        this.index = index;
    }   //Constructor of Event object

    public String getAt() {
        return this.at;
    }   //Getter of at var

    public void setAt(String at) {
        this.at = at;
    }   //Setter of at var

    public String toString() {
        taskName = description.substring(6, index - 1);
        return "[E][ ] " + taskName + " (at: " + at.substring(3) + ")";
    }   //toString method
}