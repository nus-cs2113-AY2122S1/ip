public class Deadline extends Task {
    protected String by;
    protected String taskName;
    protected int index;

    //Constructor for Deadline object
    public Deadline(String description, String by, int index) {
        super(description);
        this.by = by;
        this.index = index;
    }

    //Getter of by var
    public String getBy() {
        return this.by;
    }

    //Setter of by var
    public void setBy(String by) {
        this.by = by;
    }

    // toString method
    public String toString() {
        taskName = description.substring(9, index - 2);
        return "[D][ ] " + taskName + " (by: " + by + ")";
    }
}