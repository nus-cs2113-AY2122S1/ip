package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String at;
    protected LocalDate date;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        if (isDate(at)) {
            date = LocalDate.parse(at, DateTimeFormatter.ofPattern("d-MM-yyyy"));
        }
    }

    public String getAt() {
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                (isDate(at)? date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : at ) + ")";
    }

    private boolean isDate(String args) {
        if (!args.contains("-")) {
            return false;
        } else {
            String[] dateArray = args.split("-");
            if (dateArray.length < 3) {
                return false;
            }
            if (!(dateArray[0].length() == 2 && dateArray[1].length() == 2 && dateArray[2].length() == 4)) {
                return false;
            }
        }
        return true;
    }
}
