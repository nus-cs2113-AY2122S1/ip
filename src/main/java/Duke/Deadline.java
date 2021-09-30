package Duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDateTime by;
    private String unformattedBy;
    private String formattedBy;

    public Deadline (String description, String unformattedBy) throws DateTimeParseException {
        super(description);
        this.unformattedBy = unformattedBy;
        this.by = convertBy();
        this.formattedBy = formatBy();
    }

    public String getUnformattedBy() {
        return unformattedBy;
    }

    public String getFormattedBy() {
        return formattedBy;
    }

    public LocalDateTime getBy() {
        return by;
    }

    private LocalDateTime convertBy() throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy HHmm");
        return LocalDateTime.parse(unformattedBy, formatter);
    }

    public String formatBy() {
        return by.format(DateTimeFormatter.ofPattern("MMM d yyyy, HHmm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formattedBy + ")";
    }

}
