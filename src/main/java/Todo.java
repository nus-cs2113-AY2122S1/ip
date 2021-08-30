public class Todo extends Task {
    private static final Types type = Types.TODO;

    public Todo(String description) {
        super(description, type);
    }
}
