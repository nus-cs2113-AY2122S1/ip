public class Todo extends Task {
    /**
     * Constructs Todo subclass.
     *
     * @param todoDescription Name of task.
     */
    public Todo(String todoDescription) {
        super(todoDescription, "todo");
        super.description = todoDescription.substring(5);
    }
}
