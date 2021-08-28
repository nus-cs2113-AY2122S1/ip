import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventCommand extends Command {

    public EventCommand() {
        super("event");
    }

    @Override
    public void execute(UI ui, Unker unker, String data) {
        if (data == null) {
            ui.printSection(INVALID_FORMAT_MESSAGE, "event <description> /at <time>");
            return;
        }
        String eventCmdPattern = "^(.+) /[aA][tT] (.+)$";
        Pattern eventPattern = Pattern.compile(eventCmdPattern);
        Matcher eventMatcher = eventPattern.matcher(data);
        if (eventMatcher.matches() && !eventMatcher.group(1).isBlank() && !eventMatcher.group(2).isBlank()) {
            Event e = new Event(eventMatcher.group(1), eventMatcher.group(2));
            unker.addTask(e);
            ui.printSection(ADDED_TASK_MESSAGE, "\t" + e);
        } else {
            ui.printSection(INVALID_FORMAT_MESSAGE, "event <description> /at <time>");
        }
    }
}
