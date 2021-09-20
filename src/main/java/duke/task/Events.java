package duke.task;

public class Events extends Task {

    private final String atWhen;

    public Events(String taskName, String at) {
        super(taskName);
        this.atWhen = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + atWhen + ")";
    }

    @Override
    public String storageText () {
        return "E" + super.storageText() + "|" + atWhen;
    }
}
