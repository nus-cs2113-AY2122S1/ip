package Duke.Tasks;

import Duke.Tasks.Task;

public class Todo extends Task {

    /**
     * Create a Todo class with given description.
     *
     * @param description Description of the todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Present todo in string of prescribed format.
     *
     * @return String presented todo description format.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
