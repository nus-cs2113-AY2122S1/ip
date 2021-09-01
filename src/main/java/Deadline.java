public class Deadline extends Todo{
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    public String getBy() {
        return by;
    }
    public void setBy(String by) {
        this.by = by;
    }

    public void printTask() {
        System.out.print("[D][" + getStatusIcon() + "] " + getDescription());
        System.out.println("(by:" + by + ")");
    }
}
