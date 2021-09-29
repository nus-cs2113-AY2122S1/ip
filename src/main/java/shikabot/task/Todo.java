package shikabot.task;

import java.time.LocalDate;

public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String getType() {
        return "T";
    }

    public LocalDate getAtBy() {
        return null;
    }

}
