public class Deadline extends Task {
    private String dateTime;

    public Deadline(String description, String dateTime) {
        super(description);
        this.type = TaskType.DEADLINE;
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("(by: %s)", dateTime);
    }
}
