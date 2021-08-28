public class Todo extends Task {
    public Todo(String todoDescription) {
        super(todoDescription.substring(5), "todo");
    }
}
