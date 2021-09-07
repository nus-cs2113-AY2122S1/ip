package tasks;

public class Deadline extends Todo {
    protected String by;

    public Deadline(String description, String date) {
        super(description);
        by = date;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String outcome) {
        by = outcome;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + getBy() + ")";
    }

    @Override
    public String getClassType() {
        return "[D]";
    }
}
