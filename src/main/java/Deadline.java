public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, String isDone) {
        super(description);
        this.by = by;
        if (isDone.equals("1")) {
            this.isDone = true;
        }
    }

    @Override
    public void printTask() {
        System.out.println("[D]" + super.getStatusIcon() + super.getDescription() + "(by:" + by + ")");
    }

    @Override
    public String getTextString() {
        return ("D" + "|" + super.getStatusValue() + "|" + super.getDescription() + "|" + by + "\n");
    }
}
