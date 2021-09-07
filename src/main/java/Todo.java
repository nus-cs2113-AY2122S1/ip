public class Todo extends Task {
    /**
     * Constructor for Todo subclass.
     *
     * @param todoDescription Name of task.
     */
    public Todo(String todoDescription) {
        super(todoDescription, "todo");
        super.description = todoDescription.substring(5);
    }
}
