package duke.task;

import duke.Parser;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String at;
    protected LocalDate date;

    /**
     * Constructs an Event instance.
     *
     * @param description The description of an Event given by user.
     * @param at Usually has a date or time by which the Event has to be done.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        convertDateFormat(at);
    }

    private void convertDateFormat(String at) {
        String[] words = Parser.splitIntoWords(at);
        for (int i = 0; i < words.length; i++) {
            try {
                date = LocalDate.parse(words[i]);
                words[i] = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
                this.at = String.join(" ", words);
                break;
            } catch (DateTimeException ignored) {
                // Continue to check is other words are legitimate dates.
                // If all words are not legitimate dates the by variable will be printed exactly the same way the user 
                // gave.
            }
        }
    }
    
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
