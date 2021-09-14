package Duke.Task;

public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }

    @Override
    public String getTaskIcon() {
        return "T";
    }
}
