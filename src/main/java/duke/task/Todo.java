package duke.task;

import static duke.message.Messages.LOAD_DELIMITER;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String getCode() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.getStatusIcon() + " " + super.getDescription();
    }

    public String toSave() {
        return this.getCode() +
                LOAD_DELIMITER +
                super.getDoneValue() +
                LOAD_DELIMITER +
                super.getDescription();
    }
}
