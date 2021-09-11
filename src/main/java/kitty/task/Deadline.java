package kitty.task;

import kitty.Kitty;
import kitty.KittyException;
import kitty.Parser;
import kitty.io.IO;

public class Deadline extends Task{
    protected String deadline;

    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    public static void addDeadlineTask(String line) throws KittyException {
        if (!Parser.hasDeadline(line)) {
            throw new KittyException("Deadline formatting is incorrect!");
        } else {
            try {
                // Get Deadline Name
                String taskName = Parser.getDeadlineTaskName(line);

                // Get Deadline Date
                String deadline = Parser.getDeadlineDate(line);

                // Add Deadline Task
                Kitty.tasks.add(new Deadline(taskName, deadline));
                IO.writeNewLine("D|0|" + taskName + "|" + deadline);
            } catch (KittyException e) {
                throw e;
            }
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " [by: " + deadline + "]";
    }
}
