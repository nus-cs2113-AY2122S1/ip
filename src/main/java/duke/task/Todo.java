package duke.task;

/**
 * Represents tasks that are todo
 */
public class Todo extends Task{

    public Todo (String name) {
        super(name);
    }

    /**
     * Overrides default toString method with the custom print message
     *
     * @return the custom print message
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
