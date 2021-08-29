import java.util.regex.Matcher;

/**
 * Command to add a new {@link Deadline} into the task manager Unker. 
 *
 * Usage in UI: deadline description /by time 
 */
public class DeadlineCommand extends Command {

    public DeadlineCommand() {
        super("deadline");
    }
    
    @Override
    public void execute(UI ui, Unker unker, String data) {
        Matcher deadlineMatcher = parseUserInput("^(.+) /[bB][yY] (.+)$", data);
        if (deadlineMatcher != null  && !deadlineMatcher.group(1).isBlank() && !deadlineMatcher.group(2).isBlank()) {
            Deadline d = new Deadline(deadlineMatcher.group(1), deadlineMatcher.group(2));
            unker.addTask(d);
            ui.printSection(ADDED_TASK_MESSAGE, "\t" + d);
        } else {
            ui.printSection(INVALID_FORMAT_MESSAGE, "deadline <description> /by <time>");
        }
    }
}
