package triss.task;

import triss.exception.TrissException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    /** Represents the date this task should be done by */
    private final LocalDate dueDate;

    /**
     * Creates a deadline with task type [D], and dueDate based on user's input.
     * @param name The name of the deadline.
     * @param dueDate The due date of the deadline.
     */
    public Deadline(String name, String dueDate) throws DateTimeParseException, TrissException {
        super(name);
        try {
            this.dueDate = LocalDate.parse(dueDate);
        } catch (DateTimeParseException e) {
            String errorMessage = "You didn't format your date properly!\n"
                    + " \n"
                    + "Try inserting an deadline in this format:\n"
                    + "    deadline Submit log report /2021-08-13";
            throw new TrissException(errorMessage);
        }
        this.typeOfTask = "[D]";
    }

    /**
     * Get the due date of the deadline.
     * @return Due date of the deadline.
     */
    public String getDueDate() {
        return dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns the deadline in a human-readable format.
     * @return [Type of Task][Completion Status] [Name of Task] ([Due Date of Task])
     */
    public String printTask() {
        return this.getTypeOfTask() + this.getDoneStatusAsSymbol() + " "
                + this.getName() + " (" + this.getDueDate() + ")";
    }

    @Override
    public String printTaskForStoring() {
        return getTypeOfTask() + "," + getDoneStatusAsSymbol()
                + "," + getName() + "," + dueDate;
    }
}
