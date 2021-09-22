package duke.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

//reference: baeldung.com/java-string-valid-date
public class DateValidatorUsingDateFormat implements DateValidator {
    private DateTimeFormatter dateFormatter;

    public DateValidatorUsingDateFormat(DateTimeFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

    @Override
    public boolean isValid(String dateStr) {
        try {
            LocalDate.parse(dateStr, this.dateFormatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
