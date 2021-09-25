package duke.task;
import java.time.LocalDate;

public class Deadline extends Task {
    LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        setBy(by);
    }

    public LocalDate getBy() {
        return by;
    }

    public void setBy(LocalDate by) {
        this.by = by;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + getBy().format(formatter) + ")";
    }

    @Override
    protected String getType() {
        return "deadline";
    }
}
