package Duke.Task;

public class Deadline extends Task{
    private String when;
    private String originalWhen;
    public Deadline(String description, String when) {
        super(description);
        this.when = when.substring(3);
        this.originalWhen = when;
    }

    @Override
    public String getDescription() {
        return String.format("%s (By:%s)", description, when);
    }
    @Override
    public String getTaskIcon() {
        return "D";
    }
    @Override
    public String getOriginalDescription() {
        return this.description + " " + this.originalWhen;
    }
}
