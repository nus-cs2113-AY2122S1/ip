package Duke.TaskTypes;

public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    //Add Getter and Setter
    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() +" (by: " + by + ")";
    }
}
