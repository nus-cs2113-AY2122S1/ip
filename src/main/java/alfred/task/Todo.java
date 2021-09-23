package alfred.task;

import java.time.LocalDate;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public String getType() {
        return "T";
    }

    /**
     * This method retrieves the date specified for Todo.
     * However, as Todos have no date, it only returns a null LocalDate object.
     * @return LocalDate Returns null LocalDate object
     */
    public LocalDate getDate() {
        return null;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
