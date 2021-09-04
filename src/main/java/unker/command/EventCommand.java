package unker.command;

import java.util.regex.Matcher;
import unker.task.Event;
import unker.ui.UI;
import unker.Unker;

/**
 * Command to add a new {@link Event} into the task manager Unker. 
 *
 * Usage in UI: event description /at time 
 */
public class EventCommand extends Command {

    public EventCommand() {
        super("event");
    }
    
    @Override
    public void execute(UI ui, Unker unker, String data) {
        Matcher eventMatcher = parseUserInput("^(.+) /[aA][tT] (.+)$", data);
        if (eventMatcher != null && !eventMatcher.group(1).isBlank() && !eventMatcher.group(2).isBlank()) {
            Event e = new Event(eventMatcher.group(1), eventMatcher.group(2));
            unker.addTask(e);
            ui.printSection(ADDED_TASK_MESSAGE, "\t" + e);
        } else {
            ui.printSection(INVALID_FORMAT_MESSAGE, "event <description> /at <time>");
        }
    }
}
