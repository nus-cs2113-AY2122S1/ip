public class Todo extends Task {
    /**
     * Constructor for Todo subclass.
     *
     * @param todoDescription Name of task.
     */
    public Todo(String todoDescription) {
        super(todoDescription.substring(5), "todo");
    }
}
