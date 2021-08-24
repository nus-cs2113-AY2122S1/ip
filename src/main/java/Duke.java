import java.util.Scanner;

/**
 * A personal assistant chatbot.
 */
public class Duke {
    private static final String INDENTED_HORIZONTAL_LINE = " ".repeat(4) + "_".repeat(60);
    /** Array of Task objects */
    private static final Task[] tasks = new Task[100];
    /** Number of tasks */
    private static int taskCount = 0;

    private static String indent(String text) {
        String[] lines = text.split(System.lineSeparator());
        for (int i = 0; i < lines.length; i++) {
            lines[i] = " ".repeat(5) + lines[i];
        }
        return String.join(System.lineSeparator(), lines);
    }

    /**
     * Prints out the specified text formatted as a response block.
     * Horizontal lines will be printed before and after the specified text, and the text will be indented.
     *
     * @param text Text to be printed out.
     */
    private static void printResponseBlock(String text) {
        System.out.println(INDENTED_HORIZONTAL_LINE);
        System.out.println(indent(text));
        System.out.println(INDENTED_HORIZONTAL_LINE);
        System.out.println();
    }

    private static void printGreeting() {
        String greeting = "Hello! I'm Duke" + System.lineSeparator()
                + "What can I do for you?";
        printResponseBlock(greeting);
    }

    /** Prints out the list of tasks (numbered) together with their status icons */
    private static void printTasks() {
        String[] tasksWithNumbersAndStatusIcons = new String[taskCount];
        for (int i = 0; i < taskCount; i++) {
            tasksWithNumbersAndStatusIcons[i] = String.format("%d.[%s] %s", i + 1, tasks[i].getStatusIcon(),
                    tasks[i].getDescription());
        }
        String formattedTaskList = String.join(System.lineSeparator(), tasksWithNumbersAndStatusIcons);
        printResponseBlock("Here are the tasks in your list:" + System.lineSeparator()
                + formattedTaskList);
    }

    private static void markTaskAsDone(int taskId) {
        Task task = tasks[taskId];
        task.setAsDone();
        String formattedTask = String.format("  [%s] %s", task.getStatusIcon(), task.getDescription());
        printResponseBlock("Nice! I've marked this task as done:" + System.lineSeparator()
                + formattedTask);
    }

    private static void addTask(String description) {
        tasks[taskCount] = new Task(description);
        taskCount++;
        printResponseBlock("added: " + description);
    }

    /**
     * Reads input commands from the user and executes the appropriate actions.
     * Stops waiting for user input and returns upon receiving the "bye" command.
     */
    private static void handleCommands() {
        Scanner in = new Scanner(System.in);
        while (true) {
            String line = in.nextLine();
            String[] words = line.split(" ");
            switch (words[0]) {
            case "bye":
                return;
            case "list":
                printTasks();
                break;
            case "done":
                int taskId = Integer.parseInt(words[1]) - 1;
                markTaskAsDone(taskId);
                break;
            default:
                addTask(line);
                break;
            }
        }
    }

    private static void printFarewell() {
        String farewell = "Bye. Hope to see you again soon!";
        printResponseBlock(farewell);
    }

    public static void main(String[] args) {
        printGreeting();
        handleCommands();
        printFarewell();
    }
}
