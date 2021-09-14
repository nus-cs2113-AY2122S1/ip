package duke.task;

import duke.exceptions.EmptyField;

public class Todo extends Task {
    public Todo(String description) throws EmptyField {
        setDescription(description);
    }

    public Todo(String description, boolean done) throws EmptyField {
        setDescription(description);
        setStatus(done);
    }

    @Override
    public String getSaveFormat() {
        return String.format("%s | %s | %s", "T", status? "1":"0", description);
    }
}
