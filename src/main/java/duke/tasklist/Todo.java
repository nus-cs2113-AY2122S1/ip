package duke.tasklist;

public class Todo extends Task {

    /**
     * Constructor that takes in the description of the task to be added to to do list
     *
     * @param description task to be added to to do list
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the String that represents the task type
     *
     * @return task type T for 'to do'
     */
    @Override
    public String getTaskType() {
        return ("T");
    }
}
