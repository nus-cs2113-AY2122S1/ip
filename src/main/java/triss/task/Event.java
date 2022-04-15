package triss.task;

import triss.exception.TrissException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{
    /** The timing this event occurs at */
    private LocalDate timing;

    /**
     * Creates a deadline with task type [E], and timing based on user's input.
     * @param name The name of the event.
     * @param timing The timing of the event.
     */
    public Event(String name, String timing) throws TrissException {
        super(name);
        try {
            this.timing = LocalDate.parse(timing);
        } catch (DateTimeParseException e) {
            String errorMessage = "You didn't format your date properly!\n"
                    + " \n"
                    + "Try inserting an event in this format:\n"
                    + "    event Stay in a log cabin /2021-08-13";
            throw new TrissException(errorMessage);
        }
        this.typeOfTask = "[E]";
    }

    /**
     * Get the timing of the event.
     * @return Timing of the event.
     */
    public String getTiming() {
        return timing.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns the event in a human-readable format.
     * @return [Type of Task][Completion Status] [Name of Task] ([Timing of Task])
     */
    @Override
    public String printTask() {
        return this.getTypeOfTask() + this.getDoneStatusAsSymbol() + " "
                + this.getName() + " (" + this.getTiming() + ")";
    }

    @Override
    public String printTaskForStoring() {
        return getTypeOfTask() + "," + getDoneStatusAsSymbol()
                + "," + getName() + "," + timing;
    }
}
