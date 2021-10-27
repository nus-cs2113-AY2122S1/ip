package duke.task;

public class Todo extends Task{

    /**
     * Constructor with task description.
     * @param description Task description.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructor with task description and whether todo task has been done.
     * @param description Task description.
     * @param isDone Whether todo task has been done.
     */
    public Todo(String description,boolean isDone){
        super(description,isDone);

    }

    /**
     * Returns a todo task in string.
     * @return A todo task in string.
     */
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
