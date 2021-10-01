package duke.task;

public class Todo extends Task {

    /**
     * Constructor to initialise Todo description
     * and make isDone false
     *
     * @param description string with the task
     *                    description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the formatted Description of the task.
     *
     * @return returns a String with the task description
     */
    @Override
    public String getDescription() {
        return "[T]" + super.getDescription();
    }

    @Override
    public String fileDescription() {
        return "T | " + super.fileDescription();
    }

}
