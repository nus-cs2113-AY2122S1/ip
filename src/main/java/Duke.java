import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Duke {

    private static final String LOGO = " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String LINE_SEPARATOR = "------------------------------------";
    private static final String HELLO_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String ERROR_MESSAGE = "You need to specify the task type!";

    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        printHelloMessage();
        handleCommand();
        printByeMessage();
    }

    private static void handleCommand() {
        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        while (!input.equals("bye")) {
            String keyword = input.split(" ")[0].toLowerCase();
            switch (keyword) {
            case "bye":
                continue;
            case "list":
                listTasks();
                break;
            case "todo":
                addTodo(input);
                break;
            case "deadline":
                addDeadline(input);
                break;
            case "event":
                addEvent(input);
                break;
            case "done":
                finishTask(input);
                break;
            default:
                System.out.println(ERROR_MESSAGE);
                break;
            }
            input = in.nextLine();
        }
    }

    private static void finishTask(String input) {
        int index = Integer.parseInt(input.split(" ", 2)[1].trim());
        tasks.get(index - 1).completeTask();
        System.out.println("Nice! I have marked this task as done: ");
        System.out.println(tasks.get(index - 1).getTaskInfo());
    }

    private static void addEvent(String input) {
        String at;
        String description;
        System.out.println("Got it. I've added this task:");
        description = input.split("/")[0];
        at = input.split("/")[1];
        tasks.add(new Event(description, at));
        System.out.println(tasks.get(tasks.size() - 1).getTaskInfo());
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }

    private static void addDeadline(String input) {
        String by;
        String description;
        System.out.println("Got it. I've added this task:");
        description = input.split("/")[0];
        by = input.split("/")[1];
        tasks.add(new Deadline(description, by));
        System.out.println(tasks.get(tasks.size() - 1).getTaskInfo());
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }

    private static void addTodo(String input) {
        System.out.println("Got it. I've added this task:");
        tasks.add(new Todo(input));
        System.out.println(tasks.get(tasks.size() - 1).getTaskInfo());
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
    }

    private static void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            tasks.get(i).printTask(i + 1);
        }
    }

    private static void printHelloMessage() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println(LINE_SEPARATOR);
        System.out.println(HELLO_MESSAGE);
        System.out.println(LINE_SEPARATOR);
    }

    private static void printByeMessage() {
        System.out.println(BYE_MESSAGE);
        System.out.println(LINE_SEPARATOR);
    }
}
