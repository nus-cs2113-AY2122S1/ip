import java.util.Scanner;

public class Duke {
    public static final String LINE = "    ____________________________________________________________";
    public static final String INDENT = "     ";
    public static final String LINE_SEPARATOR = System.lineSeparator();

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
            default:
                System.out.println(INDENT + "Please enter a valid command");
            }
            System.out.println(LINE);
            line = in.nextLine();
            words = line.split(" ");
        }
    }

    public static void greetUser() {
        echo("Hello! I'm Duke." + LINE_SEPARATOR + INDENT + "What can I do for you?");
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
