package herrekt.taskmanager;

import java.time.LocalDate;

public class Deadline<T> extends Task {
    protected T date;

    public Deadline(String description, T date) {
        super(description);
        this.date = date;
    }

    public String getDate() {
        if (date instanceof String) {
            return (String) date;
        } else {
            String month = ((LocalDate) date).getMonth().toString().substring(0,3);
            int day = ((LocalDate) date).getDayOfMonth();
            int year = ((LocalDate) date).getYear();
            return month + " " + day + " " + year;
        }
    }

    @Override
    public String toSave() {
        int done = 0;
        if (this.isDone) {
            done = 1;
        }
        return "D" + SAVE_FILE_SPACER
                + done + SAVE_FILE_SPACER
                + this.description + SAVE_FILE_SPACER
                + this.date.toString();
    }

    public String getDescription() {
        return super.description + " (by: " + this.getDate() + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDate() + ")";
    }
}
