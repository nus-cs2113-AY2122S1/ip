package duke.task;

/**
 * Represents a Todo task and has a type of T. It is defined by a description String.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
        this.type = 'T';
    }

    @Override
    public String saveString() {
        String output = "";
        if (isDone) {
            output += "[X] | ";
        } else {
            output += "[ ] | ";
        }
        output += type + " | " + description;
        return output;
    }
}
