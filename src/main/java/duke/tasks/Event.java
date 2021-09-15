package duke.tasks;

public class Event extends Task {

    public String type = "[E]";
    private String dueDate;

    public Event(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    public Event(String description, boolean isDone, String dueDate) {
        super(description, isDone);
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
