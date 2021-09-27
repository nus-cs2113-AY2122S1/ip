package duke.task;

class Todo extends Task {
    private static final Type type = Type.TODO;

    Todo(String description) {
        super(description, type);
    }

    Todo(boolean isDone, String description) {
        super(isDone, description, type);
    }
}
