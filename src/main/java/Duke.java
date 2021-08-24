import java.util.Scanner;

public class Duke {
    private static final String INDENTED_HORIZONTAL_LINE = "    ____________________________________________________________";
    private static final Task[] tasks = new Task[100];
    private static int taskCount = 0;

    private static String indent(String text) {
        String[] lines = text.split(System.lineSeparator());
        for (int i = 0; i < lines.length; i++) {
            lines[i] = " ".repeat(5) + lines[i];
        }
        return String.join(System.lineSeparator(), lines);
    }

    private static void printTextWithHorizontalLineAndIndentation(String text) {
        System.out.println(INDENTED_HORIZONTAL_LINE);
        System.out.println(indent(text));
        System.out.println(INDENTED_HORIZONTAL_LINE);
        System.out.println();
    }

    private static void printGreeting() {
        String greeting = "Hello! I'm Duke" + System.lineSeparator()
                + "What can I do for you?";
        printTextWithHorizontalLineAndIndentation(greeting);
    }

    private static void printTasks() {
        String[] tasksWithNumbers = new String[taskCount];
        for (int i = 0; i < taskCount; i++) {
            tasksWithNumbers[i] = (i + 1) + ". " + tasks[i].getDescription();
        }
        String formattedTaskList = String.join(System.lineSeparator(), tasksWithNumbers);
        printTextWithHorizontalLineAndIndentation(formattedTaskList);
    }

    private static void addTask(String description) {
        tasks[taskCount] = new Task(description);
        taskCount++;
        printTextWithHorizontalLineAndIndentation("added: " + description);
    }

    private static void handleCommands() {
        Scanner in = new Scanner(System.in);
        while (true) {
            String command = in.nextLine();
            switch (command) {
            case "list":
                printTasks();
                break;
            case "bye":
                return;
            default:
                addTask(command);
                break;
            }
        }
    }

    private static void printFarewell() {
        String farewell = "Bye. Hope to see you again soon!";
        printTextWithHorizontalLineAndIndentation(farewell);
    }

    public static void main(String[] args) {
        printGreeting();
        handleCommands();
        printFarewell();
    }
}
