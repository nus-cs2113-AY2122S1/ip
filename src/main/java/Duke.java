import java.util.Locale;
import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    public static final String LINE = "    ____________________________________________________________";
    public static final String INDENT = "     ";
    public static final String LINE_SEPARATOR = System.lineSeparator();

    public static void echo(String line) {
        System.out.println(LINE + LINE_SEPARATOR + INDENT + line + LINE_SEPARATOR + LINE);
    }

    public static void showList(Task[] tasks) {
        System.out.println(INDENT + "Here are the tasks in your list:");
        for (int i = 0; i < tasks.length; i++) {
            System.out.println(INDENT + Integer.toString(i + 1) + "."
                    + "[" + tasks[i].getStatusIcon() + "] " + tasks[i].description);
        }
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
                showList(Arrays.copyOf(tasks, taskCount));
                break;
            case "done":
                int taskIndex = Integer.parseInt(words[1]) - 1;
                tasks[taskIndex].markAsDone();
                break;
            default:
                tasks[taskCount] = new Task(line);
                System.out.println(INDENT + "added: " + line);
                taskCount++;
                break;
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
