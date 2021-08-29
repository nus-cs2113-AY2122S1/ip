public class Deadline extends Task {
    private String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    @Override
    public String getTaskSymbol() {
        return "[D]";
    }

    public String toString() {
        return (getTaskSymbol() + getStatusSymbol() + " " + name + "(by: " + by + ")");
    }
}
