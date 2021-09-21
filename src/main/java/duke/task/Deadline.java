package duke.task;

import duke.Parser;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * An extension of Task class.
 * On top of having a description field like Tasks, Deadlines have a date and/or time field.
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDate date;

    /**
     * Constructs a Deadline instance.
     *
     * @param description The description of a Deadline given by user.
     * @param by          Usually a date or time by which the Deadline has to be done.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        convertDateFormat(by);
    }

    private void convertDateFormat(String by) {
        String[] words = Parser.splitIntoWords(by);
        for (int i = 0; i < words.length; i++) {
            try {
                date = LocalDate.parse(words[i]);
                words[i] = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                this.by = String.join(" ", words);
                break;
            } catch (DateTimeException ignored) {
                // Continue to check if other words are legitimate dates.
                // If all words are not legitimate dates the by variable will be printed exactly the same way the user 
                // gave. 
            }
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
