package duke.task;

public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }
    private static final String ICON_TODO = "T";

    @Override
    public String getTaskIcon() {
        return ICON_TODO;
    }

    @Override
    public String toString() {
        return "["  + this.getTaskIcon() +"]" + super.toString();
    }
}
