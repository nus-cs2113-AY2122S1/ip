package duke;

public class Todo extends Task{

    public Todo(String description) {
        super(description);
    }
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    public void printTask() {
        System.out.println("[T][" + getStatusIcon() + "] " + getDescription());
    }

    @Override
    public String saveTask() {
        return "T | " + (isDone ? 1 : 0) + " | " + description;
    }
}
