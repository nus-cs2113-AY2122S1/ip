package Duke.Task;

public class Events extends Task {
    private String when;
    private String originalWhen;
    public Events(String description, String when) {
        super(description);
        this.when = when.substring(3);
        this.originalWhen = when;
    }

    @Override
    public String getDescription() {
        return String.format("%s (At:%s)", description, when);
    }
    @Override
    public String getTaskIcon() {
        return "E";
    }
    @Override
    public String getOriginalDescription() {
        return this.description + " " + this.originalWhen;
    }
}
