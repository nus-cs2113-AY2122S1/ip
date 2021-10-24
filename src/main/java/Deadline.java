import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Deadline extends Task {

    public LocalDate byDate;
    public LocalTime byTime;

    public Deadline(String description, String by, String by1) {
        super(description);
        this.byDate = LocalDate.parse(by);
        this.byTime = LocalTime.parse(by1);
    }

    @Override
    public String toString() {
        String wantedDate = byDate.format(DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH));
        String wantedTime = byTime.format(DateTimeFormatter.ofPattern(" h:mma", Locale.ENGLISH));
        return "[D]" + "[" + getStatusIcon() + "] "
                + description + " (by: " +  wantedDate + " " + wantedTime + ")";
    }

    @Override
    public String getStoredDataString() {
        String checkDone = isDone ? "1" : "0";
        return "deadline " + description + " /by " + byDate + " " + byTime + " | " + checkDone + System.lineSeparator();
    }

}