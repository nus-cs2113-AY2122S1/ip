package unker.command;

import java.util.regex.Matcher;
import unker.task.Deadline;
import unker.ui.UI;
import unker.Unker;

/**
 * Command to add a new {@link Deadline} into the task manager Unker. 
 *
 * Usage in UI: deadline description /by time 
 */
public class DeadlineCommand extends Command {

    public DeadlineCommand() {
        super("deadline", "deadline <description> /by <time>");
    }
    
    @Override
    public void execute(UI ui, Unker unker, String data) throws InvalidCommandException {
        Matcher deadlineMatcher = parseUserInput("^(.+) /[bB][yY] (.+)$", data);
        if (deadlineMatcher != null  && !deadlineMatcher.group(1).isBlank() && !deadlineMatcher.group(2).isBlank()) {
            Deadline d = new Deadline(deadlineMatcher.group(1), deadlineMatcher.group(2));
            unker.addTask(d);
            ui.printSection(ADDED_TASK_MESSAGE, "\t" + d);
        } else {
            //  
            throw new InvalidCommandException(INVALID_FORMAT_MESSAGE, this);
        }
    }
}
