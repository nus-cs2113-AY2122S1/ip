package Tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDateTime dueDate;

    public Deadline(String task, boolean isDone, LocalDateTime dueDate) {
        super(task, isDone, TaskTypes.DEADLINE);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return super.toString() + " | by: " + dueDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss"));
    }
}
