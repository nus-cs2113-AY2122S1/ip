package todo;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        super.setType("d");
    }
    
    public String getDate() {
        return by;
    }
    
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

}