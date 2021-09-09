package duke.task;

import duke.exceptions.EmptyField;
import duke.exceptions.IllegalOperation;

public class Todo extends Task {
    public Todo(String description) throws EmptyField {
        setDescription(description);
    }

    @Override
    public String getTime() throws IllegalOperation {
        throw new IllegalOperation();
    }

    @Override
    public void setTime(String time) throws IllegalOperation {
        throw new IllegalOperation();
    }
}
