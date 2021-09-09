package tasks;

public class Todo extends Task {
    public Todo(boolean done, String name) {
        super(done, name);
    }

    public Todo() {
        super(false, "Nothing");
    }

    @Override
    public String getPrefix() {
        return "[T]";
    }

    @Override
    public String toString() {
        return getPrefix() + super.toString();
    }
}
