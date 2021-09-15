package duke.task;

public class Deadline extends Task{

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getTaskIcon() {
        return "D";
    }

    @Override
    public String toString() {
        return ". [" + this.getTaskIcon() +"]" + super.toString() + " by: " + by;
    }

    @Override
    public String getDue() {
        return by;
    }
}
