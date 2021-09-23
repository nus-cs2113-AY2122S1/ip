package alfred.task;

import java.time.LocalDate;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String getType() {
        return "T";
    }

    public LocalDate getDate() {
        return null;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
