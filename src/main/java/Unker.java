import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Unker {

    private static final ArrayList<Task> TASKS = new ArrayList<>();
    public static final String INVALID_FORMAT_MESSAGE = "Sorry, Unker need you to type this way for me to understand arh (no need brackets):";
    public static final String ADDED_TASK_MESSAGE = "Okay Unker help you add this to your to-do list:";

    public static void main(String[] args) {
        printBanner();

        // Initialize a scanner to read in user input
        Scanner scanner = new Scanner(System.in);

        // Keep listening for new commands
        while (true) {
            // Read the input of the user
            System.out.print("> ");
            String cmd = scanner.nextLine();
            System.out.println();

            // Check if the user wishes to exit the program.
            if (!parseCommands(cmd)) {
                break;
            }

            // Otherwise, ask the user for more commands
            System.out.println("--");
            System.out.println("Anything else you wan Unker to help you with?");
        }

        // Cleanup and print exit message
        printSection("Bye bye, see you soon again arh!");
    }

    private static void printBanner() {
        String logo =  "  _    _       _             \n"
                + " | |  | |     | |            \n"
                + " | |  | |_ __ | | _____ _ __ \n"
                + " | |  | | '_ \\| |/ / _ \\ '__|\n"
                + " | |__| | | | |   <  __/ |   \n"
                + "  \\____/|_| |_|_|\\_\\___|_|   \n";
        System.out.println("Loading your digital\n" + logo);
        printSection("Harlo, you can call me Unker.", "What can Unker do for you today?");
    }

    /**
     * Prints the bot name and given data provided.
     * If no data is provided, it will print a horizontal line with the bot name.
     *
     * @param data The string(s) to print to console.
     */
    private static void printSection(String ...data) {
        System.out.println("--// Unker //----------------------------------------------");
        for (String s: data) {
            System.out.println(s);
        }
    }

    /**
     * Interprets the commands sent by the user.
     *
     * @param cmdString The command sent by the user.
     * @return Returns true if the program should continue executing.
     */
    private static boolean parseCommands(String cmdString) {
        Pattern cmdPattern = Pattern.compile("^(?<cmd>\\w+?)(?:\\s+(?<cmdData>.+))?+$");
        Matcher cmdMatcher = cmdPattern.matcher(cmdString);
        if (!cmdMatcher.matches()) {
            printSection("Sorry, can ask something else? Unker don't know how help you.");
            return true;
        }
        String cmd = cmdMatcher.group("cmd");
        String cmdData = cmdMatcher.group("cmdData");
        switch (cmd.toLowerCase()) {
        case "bye":
            return false;
        case "list":
            if (TASKS.isEmpty()) {
                printSection("Wah seh, you got nothing in your to-do list leh.");
            } else {
                printSection("This is what you give me:");
                int toBeCompleted = 0;
                for (int i = 0; i < TASKS.size(); i++) {
                    Task task = TASKS.get(i);
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
            if (taskNo < 1 || taskNo > TASKS.size()) {
                printSection("Aiyo, you give me a task number that I don't have.");
            } else  {
                Task task = TASKS.get(taskNo - 1);
                if (!task.isDone()) {
                    task.setDone(true);
                    printSection("Good job, this task finish already:",
                            "\t" + task, "");
                } else {
                    printSection("You finish this task already leh:", "\t" + task, "");
                }
            }
            return true;
        case "todo":
            ToDo t = new ToDo(cmdData);
            TASKS.add(t);
            printSection(ADDED_TASK_MESSAGE, "\t" + t);
            return true;
        case "deadline":
            if (cmdData == null) {
                printSection(INVALID_FORMAT_MESSAGE, "deadline <description> /by <time>");
                return true;
            }
            String deadlineCmdPattern = "^(.+) /[bB][yY] (.+)$";
            Pattern deadlinePattern = Pattern.compile(deadlineCmdPattern);
            Matcher deadlineMatcher = deadlinePattern.matcher(cmdData);
            if (deadlineMatcher.matches() && !deadlineMatcher.group(1).isBlank() && !deadlineMatcher.group(2).isBlank()) {
                Deadline d = new Deadline(deadlineMatcher.group(1), deadlineMatcher.group(2));
                TASKS.add(d);
                printSection(ADDED_TASK_MESSAGE, "\t" + d);
            } else {
                printSection(INVALID_FORMAT_MESSAGE, "deadline <description> /by <time>");
            }
            return true;
        case "event":
            if (cmdData == null) {
                printSection(INVALID_FORMAT_MESSAGE, "event <description> /at <time>");
                return true;
            }
            String eventCmdPattern = "^(.+) /[aA][tT] (.+)$";
            Pattern eventPattern = Pattern.compile(eventCmdPattern);
            Matcher eventMatcher = eventPattern.matcher(cmdData);
            if (eventMatcher.matches() && !eventMatcher.group(1).isBlank() && !eventMatcher.group(2).isBlank()) {
                Event e = new Event(eventMatcher.group(1), eventMatcher.group(2));
                TASKS.add(e);
                printSection(ADDED_TASK_MESSAGE, "\t" + e);
            } else {
                printSection(INVALID_FORMAT_MESSAGE, "event <description> /at <time>");
            }
            return true;
        default:
            printSection("Sorry, can ask something else? Unker don't know how help you.");
            return true;
        }
    }
}
