public class Todo extends Task {
    protected static final String TODO_LOGO = "[T] ";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return TODO_LOGO + super.toString();
    }
}
