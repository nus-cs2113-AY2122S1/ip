package task;

public class Deadline extends Todo {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        setBy(by);
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String fileForm() {
        return "D / " + this.getStatusIcon() + " / " + this.description + " / " + this.by + "\n";
    }

    @Override
    public String toString() {
        return "[D]" + "[" + this.getStatusIcon() + "] " + this.description + " (by: " + this.by + ")";
    }

}
