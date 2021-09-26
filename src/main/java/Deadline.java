public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        setBy(by);
    }

    public void setBy(String byDate) {
        this.by = byDate;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String getWhen() {
        return this.by;
    }
}
