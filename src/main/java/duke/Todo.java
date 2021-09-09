package duke;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return (this.getId() + 1) + ".[T]" + super.toString();
    }
}
