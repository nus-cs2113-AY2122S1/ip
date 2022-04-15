/**
 * Class inheriting from Task that has a description and deadline attribute
 */
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

    @Override
    public String getSymbol() {
        return "D"; // mark Deadlines with a "D"
    }
}
