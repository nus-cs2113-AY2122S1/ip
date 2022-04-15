public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    public Event(String description, String at, String isDone) {
        super(description);
        this.at = at;
        if (isDone.equals("1")) {
            this.isDone = true;
        }
    }

    @Override
    public void printTask() {
        System.out.println("[E]" + super.getStatusIcon() + super.getDescription() + "(at: " + at + ")");
    }

    @Override
    public String getTextString() {
        return ("E" + "|" + super.getStatusValue() + "|" + super.getDescription() + "|" + at + "\n");
    }

}
