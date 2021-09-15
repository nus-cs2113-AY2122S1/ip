package duke.tasks;

public class Deadline extends Task {

    private String type = "[D]";
    private String dueDate;

    public Deadline(String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    public Deadline(String description, boolean isDone, String dueDate) {
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
        return type + super.toString() + " (by:" + dueDate + ")";
    }
}
