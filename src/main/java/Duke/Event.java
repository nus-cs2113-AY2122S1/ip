package Duke;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    private LocalDateTime at;
    private String unformattedAt;
    private String formattedAt;

    public Event (String description, String unformattedAt) throws DateTimeParseException {
        super(description);
        this.unformattedAt = unformattedAt;
        this.at = convertAt();
        this.formattedAt = formatAt();
    }

    public String getUnformattedAt() {
        return unformattedAt;
    }

    public String getFormattedAt() {
        return formattedAt;
    }

    public LocalDateTime getAt() {
        return at;
    }

    private LocalDateTime convertAt() throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy HHmm");
        return LocalDateTime.parse(unformattedAt, formatter);
    }

    public String formatAt() {
        return at.format(DateTimeFormatter.ofPattern("MMM d yyyy, HHmm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + formattedAt + ")";
    }

}
