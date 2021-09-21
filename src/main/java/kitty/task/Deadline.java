package kitty.task;

import kitty.Kitty;
import kitty.KittyException;
import kitty.Parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The class <code>Deadline</code> includes methods that involves tasks of type Deadline.
 */
public class Deadline extends Task{
    protected LocalDate deadline;

    public Deadline(String taskName, LocalDate deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    /**
     * Adds a task of type Deadline to list of tasks at hand.
     * @param line line is the String that the user inputs.
     * @throws KittyException If line is of the wrong format for adding a task of Deadline type.
     */
    public static void addDeadlineTask(String line) throws KittyException {
        if (!Parser.hasDeadline(line)) {
            throw new KittyException("Deadline formatting is incorrect!");
        } else {
            // Get Deadline Name
            String taskName = Parser.getDeadlineTaskName(line);

            // Get Deadline Date as String
            String deadlineString = Parser.getDeadlineDateString(line);

            // Get Deadline Date as LocalDate
            LocalDate deadlineDate = Parser.getTaskDate(deadlineString);

            // Add Deadline Task
            Kitty.tasks.add(new Deadline(taskName, deadlineDate));
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " [by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + "]";
    }
}
