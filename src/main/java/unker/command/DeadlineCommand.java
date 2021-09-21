package unker.command;

import java.util.regex.Matcher;
import unker.task.Deadline;
import unker.ui.UI;
import unker.task.Unker;
import unker.util.StringUtil;

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
        Matcher deadlineMatcher = StringUtil.parseUserInput(Deadline.DEADLINE_DATA_PATTERN, data);
        if (deadlineMatcher != null) {
            Deadline deadline = new Deadline(deadlineMatcher.group(1), deadlineMatcher.group(2));
            unker.addTask(deadline);
            unker.saveData();
            ui.printSection(ADDED_TASK_MESSAGE, "\t" + deadline);
        } else {
            throw new InvalidCommandException(INVALID_FORMAT_MESSAGE, this);
        }
    }
}
