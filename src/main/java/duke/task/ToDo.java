package duke.task;

public class ToDo extends Task {
    private String type;

    public ToDo(String description) {
        super(description);
        this.type = "T";
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
        this.type = "T";
    }

    @Override
    public String getType() {
        return type;
    }
}
