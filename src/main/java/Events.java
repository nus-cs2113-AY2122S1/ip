public class Events extends Task {
    private String when;
    public Events(String description, String when) {
        super(description);
        this.when = when.substring(3);
    }

    @Override
    public String getDescription() {
        return String.format("%s (At:%s)", description, when);
    }
}
