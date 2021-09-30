package duke.tasks;

public class Event extends Task {

    public String type = "[E]";
    private String dueDate;

    /**
     * Represents an Event made by the user.
     *
     * @param description Description of Event.
     * @param dueDate Due date of Event.
     */

    public Event(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    public String getType(){
        return this.type;
    }

    public String getDueDate(){
        return this.dueDate;
    }

    public String toString() {
        return type + super.toString() + " (at:" + dueDate + ")";
    }
}
