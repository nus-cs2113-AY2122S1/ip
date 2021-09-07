package duke.list;

public class Deadline extends Task{
    protected String by;

    public Deadline(String description, int noOfTask, String by) {
        super(description, noOfTask);
        setBy(by);
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String toString() {
        return ("[D]" + super.toString() + " (by: " + by + ")");
    }
}
