public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by.substring(by.indexOf(" ") + 1);
    }

    @Override
    public String getDescription() {
        return description + "(by: " + by + ")";
    }

    public String getSymbol() {
        return "D"; // mark Deadlines with a "D"
    }
}
