package duke.task;

public class Todo extends Task {
    /**
     * Constructor that initialises Todo type tasks.
     * Splits the description into its descriptive fields before storing into variables within the Task object.
     *
     * @param description User's input in the Command Line.
     */
    public Todo(String description) {
        super(description);
        this.description = trimUserInput(description);
        taskType = "T";
    }
}
