package duke.task;

public class ToDo extends Task{
    protected String icon;

    public ToDo(String description) {
        super(description);
        this.icon = "T";
    }

    @Override
    public String toString() {
        return "[" + icon + "]" + super.toString();
    }
}
