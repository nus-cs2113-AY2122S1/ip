package unker.command;

import java.util.regex.Matcher;
import unker.task.Event;
import unker.ui.UI;
import unker.Unker;

/**
 * Command to add a new {@link unker.task.Event} into the task manager Unker. 
 *
 * Usage in UI: event description /at time 
 */
public class EventCommand extends Command {

    public EventCommand() {
        super("event", "event <description> /at <time>");
    }
    
    @Override
    public void execute(UI ui, Unker unker, String data) throws InvalidCommandException {
        Matcher eventMatcher = parseUserInput("^(.+) /[aA][tT] (.+)$", data);
        if (eventMatcher != null) {
            Event e = new Event(eventMatcher.group(1), eventMatcher.group(2));
            unker.addTask(e);
            ui.printSection(ADDED_TASK_MESSAGE, "\t" + e);
        } else {
            throw new InvalidCommandException(INVALID_FORMAT_MESSAGE, this);
        }
    }
}
