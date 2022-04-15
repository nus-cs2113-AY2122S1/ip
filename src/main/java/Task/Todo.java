package Task;

public class Todo extends Task {

    /**
     * A public constructor to initialized the Todo.
     */
    public Todo(String content) {
        super(content);

    }

    /**
     * A methods to return the whole information of the Todo.
     * @return A String contains the type, and name this task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
