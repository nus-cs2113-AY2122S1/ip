package duke.task;

public class ToDo extends Task{
    protected String icon;

    public ToDo(String description) {
        super(description);
        this.icon = "T";
    }

    public String getIcon() {
        return icon;
    }

    @Override
    public String toString() {
        return "[" + getIcon() + "]" + super.toString();
    }

    public String formatForDataStore() {
        return "T|" + ((getIsDone()) ? 1 : 0) + "|" + getDescription() + "\n";
    }
}
