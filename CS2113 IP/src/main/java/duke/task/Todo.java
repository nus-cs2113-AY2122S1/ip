package duke.task;

public class Todo extends Task {
    public Todo(String description, int index) {
        super(description, index);
        this.description = trimUserInput(description);
        taskType = "T";
    }

    private String trimUserInput(String description) {
        String[] trimDescription = description.trim().split("\\s+", 2);
        return trimDescription[1];
    }
}
