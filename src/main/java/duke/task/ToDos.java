package duke.task;

public class ToDos extends Task {
    public ToDos(String description, boolean isDone) {
        super(description, isDone);
    }

    public String toString() {
        return String.format("[T][%s] %s", isDone ? "X" : " ", this.taskDescription);
    }
}
