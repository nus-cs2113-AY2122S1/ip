import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Unker {

    private final ArrayList<Task> tasks;
    private static final Unker UNKER_INSTANCE = new Unker();
    public static final String INVALID_FORMAT_MESSAGE =
            "Sorry, Unker need you to type this way for me to understand arh (no need brackets):";
    public static final String ADDED_TASK_MESSAGE = "Okay Unker help you add this to your to-do list:";

    private Unker() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public static void main(String[] args) {
        UI ui = UI.getUiInstance();
        ui.printBanner();
        Unker unker = getUnkerInstance();

        // Keep listening for new commands
        while (true) {
            String cmd = ui.getUserInput();
            // Check if the user wishes to exit the program.
            if (!unker.parseCommands(cmd)) {
                break;
            }

            // Otherwise, ask the user for more commands
            ui.printRequestMoreCommandsMessage();
        }

        // Cleanup and print exit message
        ui.printByeMessage();
    }

    public static Unker getUnkerInstance() {
        return UNKER_INSTANCE;
    }

    /**
     * Interprets the commands sent by the user.
     *
     * @param cmdString The command sent by the user.
     * @return Returns true if the program should continue executing.
     */
    private boolean parseCommands(String cmdString) {
        UI ui = UI.getUiInstance();
        Pattern cmdPattern = Pattern.compile("^(?<cmd>\\w+?)(?:\\s+(?<cmdData>.+))?+$");
        Matcher cmdMatcher = cmdPattern.matcher(cmdString);
        if (!cmdMatcher.matches()) {
            ui.printSection("Sorry, can ask something else? Unker don't know how help you.");
            return true;
        }
        String cmd = cmdMatcher.group("cmd");
        String cmdData = cmdMatcher.group("cmdData");
        switch (cmd.toLowerCase()) {
        case "bye":
            return false;
        case "list":
            if (tasks.isEmpty()) {
                ui.printSection("Wah seh, you got nothing in your to-do list leh.");
            } else {
                ui.printSection("This is what you give me:");
                int toBeCompleted = 0;
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    if (!task.isDone()) {
                        toBeCompleted++;
                    }
                    System.out.printf("%d. %s\n", i + 1, task);
                }
                System.out.printf("\nYou still got %d task(s) left to do.\n", toBeCompleted);
            }
            return true;
        case "done":
            int taskNo = Integer.parseInt(cmdData);
            if (taskNo < 1 || taskNo > tasks.size()) {
                ui.printSection("Aiyo, you give me a task number that I don't have.");
            } else  {
                Task task = tasks.get(taskNo - 1);
                if (!task.isDone()) {
                    task.setDone(true);
                    ui.printSection("Good job, this task finish already:",
                            "\t" + task, "");
                } else {
                    ui.printSection("You finish this task already leh:", "\t" + task, "");
                }
            }
            return true;
        case "todo":
            ToDo t = new ToDo(cmdData);
            tasks.add(t);
            ui.printSection(ADDED_TASK_MESSAGE, "\t" + t);
            return true;
        case "deadline":
            if (cmdData == null) {
                ui.printSection(INVALID_FORMAT_MESSAGE, "deadline <description> /by <time>");
                return true;
            }
            String deadlineCmdPattern = "^(.+) /[bB][yY] (.+)$";
            Pattern deadlinePattern = Pattern.compile(deadlineCmdPattern);
            Matcher deadlineMatcher = deadlinePattern.matcher(cmdData);
            if (deadlineMatcher.matches() && !deadlineMatcher.group(1).isBlank() && !deadlineMatcher.group(2).isBlank()) {
                Deadline d = new Deadline(deadlineMatcher.group(1), deadlineMatcher.group(2));
                tasks.add(d);
                ui.printSection(ADDED_TASK_MESSAGE, "\t" + d);
            } else {
                ui.printSection(INVALID_FORMAT_MESSAGE, "deadline <description> /by <time>");
            }
            return true;
        case "event":
            if (cmdData == null) {
                ui.printSection(INVALID_FORMAT_MESSAGE, "event <description> /at <time>");
                return true;
            }
            String eventCmdPattern = "^(.+) /[aA][tT] (.+)$";
            Pattern eventPattern = Pattern.compile(eventCmdPattern);
            Matcher eventMatcher = eventPattern.matcher(cmdData);
            if (eventMatcher.matches() && !eventMatcher.group(1).isBlank() && !eventMatcher.group(2).isBlank()) {
                Event e = new Event(eventMatcher.group(1), eventMatcher.group(2));
                tasks.add(e);
                ui.printSection(ADDED_TASK_MESSAGE, "\t" + e);
            } else {
                ui.printSection(INVALID_FORMAT_MESSAGE, "event <description> /at <time>");
            }
            return true;
        default:
            ui.printSection("Sorry, can ask something else? Unker don't know how help you.");
            return true;
        }
    }
}
