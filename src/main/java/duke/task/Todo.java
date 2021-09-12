package duke.task;

class Todo extends Task {
    private static final Types type = Types.TODO;

    Todo(String description) {
        super(description, type);
    }
}
