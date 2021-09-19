package kitty.task;

import kitty.Kitty;
import kitty.KittyException;
import kitty.Parser;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected LocalDate deadline;

    public Deadline(String taskName, LocalDate deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public static void addDeadlineTask(String line) throws KittyException {
        if (!Parser.hasDeadline(line)) {
            throw new KittyException("Deadline formatting is incorrect!");
        } else {
            try {
                // Get Deadline Name
                String taskName = Parser.getDeadlineTaskName(line);

                // Get Deadline Date as String
                String deadlineString = Parser.getDeadlineDateString(line);

                // Get Deadline Date as LocalDate
                LocalDate deadlineDate = Parser.getTaskDate(deadlineString);

                // Add Deadline Task
                Kitty.tasks.add(new Deadline(taskName, deadlineDate));
            } catch (KittyException e) {
                throw e;
            }
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " [by: " + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + "]";
    }
}
