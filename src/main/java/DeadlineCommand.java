import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeadlineCommand extends Command {

    public DeadlineCommand() {
        super("deadline");
    }

    @Override
    public void execute(UI ui, Unker unker, String data) {
        if (data == null) {
            ui.printSection(INVALID_FORMAT_MESSAGE, "deadline <description> /by <time>");
            return;
        }
        String deadlineCmdPattern = "^(.+) /[bB][yY] (.+)$";
        Pattern deadlinePattern = Pattern.compile(deadlineCmdPattern);
        Matcher deadlineMatcher = deadlinePattern.matcher(data);
        if (deadlineMatcher.matches() && !deadlineMatcher.group(1).isBlank() && !deadlineMatcher.group(2).isBlank()) {
            Deadline d = new Deadline(deadlineMatcher.group(1), deadlineMatcher.group(2));
            unker.addTask(d);
            ui.printSection(ADDED_TASK_MESSAGE, "\t" + d);
        } else {
            ui.printSection(INVALID_FORMAT_MESSAGE, "deadline <description> /by <time>");
        }
    }
}
