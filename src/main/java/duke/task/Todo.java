package duke.task;

/**
 * A class representing a todo task with a name
 */
public class Todo extends Task {

    /**
     * Constructor of {@code Todo} class
     *
     * @param description Name of todo task
     */
    public Todo(String description) {
        super(description);
        type = "T";
    }

    /**
     * Prints added todo task message
     */
    @Override
    public void printTaskDisplay() {
        System.out.println("Got it. I've added this task:");
        System.out.println("[T] [ ] " + description);
    }
}
