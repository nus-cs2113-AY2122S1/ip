package duke.task;
import duke.List;

public class Deadline extends List {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}