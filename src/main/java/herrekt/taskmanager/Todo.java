package herrekt.taskmanager;

public class Todo extends Task {

    /**
     * Initialise an Todo with a description and date.
     *
     * @param description Description of the todo.
     */
    public Todo(String description) {
        super(description);
    }
    /**
     * Returns the save format of the current Todo.
     * Converts the todo into a string format recognizable in the save file.
     *
     * @return The todo as a string.
     */
    @Override
    protected String toSave() {
        int done = 0;
        if (this.isDone) {
            done = 1;
        }
        return "T" + SAVE_FILE_SPACER
                + done + SAVE_FILE_SPACER
                + this.description;
    }

    protected String getDescription() {
        return super.description;
    }

    @Override
    public String toString() {
        return "[T]" +  super.toString();
    }

}