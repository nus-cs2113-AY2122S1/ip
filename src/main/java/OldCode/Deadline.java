package OldCode;

public class Deadline extends Task{
    private String when;
    public Deadline(String description, String when) {
        super(description);
        this.when = when.substring(3);
    }

    @Override
    public String getDescription() {
        return String.format("%s (By:%s)", description, when);
    }
}
