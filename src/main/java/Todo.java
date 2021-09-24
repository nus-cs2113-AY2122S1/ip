/**
 * A basic type of task
 */
public class Todo extends Task {


    /**
     * Constructor for Todo
     *
     * @param description what the todo is about
     * @param isDone      whether the todo has been done or not
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }


    /**
     * Builds on the string that is formatted for DukeData/data.txt for tasks
     *
     * @return String for storing in DukeData/data.txt
     */
    public String getDataStorageString() {
        return 'T' + super.getDataStorageString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
