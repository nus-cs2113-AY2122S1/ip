public class DeadlineTask extends Task{

    private String deadline;

    public DeadlineTask(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    public String toString() {
        return super.toString() + String.format(" (by: %s)", deadline);
    }

    @Override
    public String getTypeIcon() {
        return "D";
    }

    public String toFileString() {
        return super.toFileString() + String.format(";%s", deadline);
    }
}
