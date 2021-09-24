package duke.task;

import java.time.LocalDateTime;

public class Todo extends Task {

    public Todo(String desc) {
        super(desc, TODO_ICON);
    }

    @Override
    public LocalDateTime getDateTime() {
        return null;
    }

    @Override
    public String toStorageString() {
        return type + PADDED_DATA_SEP + super.toStorageString();
    }

    @Override
    public String toString() {
         return type + super.toString();
    }
}
