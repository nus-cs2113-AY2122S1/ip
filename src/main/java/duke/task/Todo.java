package duke.task;

public class Todo extends Task {

    public Todo(String todo) {
        super(todo);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", getStatusIcon(), this.description);
    }

    @Override
    public String toSave() {
        return String.format("todo | %s | %b", this.description, this.isDone);
    }
}
