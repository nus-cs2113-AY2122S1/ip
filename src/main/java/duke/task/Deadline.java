package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Todo {
    protected LocalDate date;

    public Deadline(String description, LocalDate date) {
        super(description);
        this.type = 'D';
        this.date = date;
    }

    @Override
    public String toString() {
        String prefix;
        if (type == 'D') {
            prefix = " (by: ";
        } else {
            prefix = " (at: ";
        }
        return super.toString() + prefix + date.format(DateTimeFormatter.ofPattern(Task.DATE_FORMAT)) + ")";
    }

    @Override
    public String saveString() {
        return super.saveString() + " | " + date.format(DateTimeFormatter.ofPattern(Task.DATE_FORMAT));
    }
}
