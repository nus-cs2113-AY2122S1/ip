import java.util.Scanner;

public class Duke {
    public static final String LINE = "    ____________________________________________________________";
    public static final String INDENT = "     ";
    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String LINE_AND_INDENT = LINE_SEPARATOR + INDENT;
    public static final String HELP_INSTRUCTIONS = "Learn how to use Duke:" +
            LINE_AND_INDENT + " To add a task: todo task_name" +
            LINE_AND_INDENT + " To add an event: event /on event_date" +
            LINE_AND_INDENT + " To add a deadline: deadline /by deadline_date" +
            LINE_AND_INDENT + " To view the list: list" +
            LINE_AND_INDENT + " To end the program: bye";

    public static void echo(String line) {
        System.out.println(LINE + LINE_SEPARATOR + INDENT + line + LINE_SEPARATOR + LINE);
    }

    public static void executeRequest() {
        String line;
        Scanner in = new Scanner(System.in);
        int taskCount = 0;
        Task[] tasks = new Task[100];

        line = in.nextLine();
        String[] words = line.split(" ");

        while (!line.equals("bye")) {
            System.out.println(LINE);
            switch (words[0]) {
            case "list":
                TaskHandler.printTaskList();
                break;
            case "done":
                TaskHandler.markTaskAsDone(words);
                break;
            case "deadline":
                TaskHandler.addDeadline(line);
                break;
            case "event":
                TaskHandler.addEvent(line);
                break;
            case "todo":
                TaskHandler.addTodo(line);
                break;
            case "@help":
                TaskHandler.showHelp();
                break;
            default:
                System.out.println(INDENT + "Please enter a valid command");
            }
            System.out.println(LINE);
            line = in.nextLine();
            words = line.split(" ");
        }
    }

    public static void greetUser() {
        echo("Hello! I'm Duke." + LINE_AND_INDENT + "What can I do for you?" +
                LINE_SEPARATOR + LINE_AND_INDENT + HELP_INSTRUCTIONS);
    }

    public static void farewellUser() {
        echo("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        greetUser();
        executeRequest();
        farewellUser();
    }
}
