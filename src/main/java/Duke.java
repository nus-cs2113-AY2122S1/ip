import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static final String DIVIDER = "    ____________________________________________________________";
    private static List<Task> store = new ArrayList<Task>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean exit = false;
        greet();

        while (true) {
            String command = scan.nextLine();
            HashMap<String, String> commandArgs = parseCommand(command);
            System.out.println(DIVIDER);

            switch (commandArgs.get("command")) {
            case "list":
                list();
                break;
            case "done":
                setTaskDone(Integer.parseInt(commandArgs.get("param")));
                break;
            case "todo":
                addTodo(commandArgs.get("param"));
                break;
            case "deadline":
                addDeadline(commandArgs.get("param"), commandArgs.get("by"));
                break;
            case "event":
                addEvent(commandArgs.get("param"), commandArgs.get("at"));
                break;
            case "bye":
                exit = true;
                bye();
                break;
            default:
                break;
            }

            System.out.println(DIVIDER);

            if (exit) {
                break;
            } else {
                System.out.println("");
            }
        }

        scan.close();
    }

    public static HashMap<String, String> parseCommand(String command) {
        String[] parts = command.split(" /");
        HashMap<String, String> map = new HashMap<String, String>();
        String[] commandParts = parts[0].split(" ", 2);
        map.put("command", commandParts[0]);
        map.put("param", commandParts.length > 1 ? commandParts[1] : "");
        for (int i = 1; i < parts.length; i++) {
            String[] argParts = parts[i].split(" ", 2);
            map.put(argParts[0], argParts[1]);
        }
        return map;
    }

    public static void greet() {
        System.out.println(DIVIDER);
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println(DIVIDER + "\n");
    }

    public static void bye() {
        System.out.println("     Bye. Hope to see you again soon!");
    }

    public static void addTodo(String task) {
        Todo todo = new Todo(task);
        store.add(todo);
        addSuccess(todo);
    }

    public static void addDeadline(String task, String by) {
        Deadline deadline = new Deadline(task, by);
        store.add(deadline);
        addSuccess(deadline);
    }

    public static void addEvent(String task, String at) {
        Event event = new Event(task, at);
        store.add(event);
        addSuccess(event);
    }

    public static void addSuccess(Task task) {
        System.out.println("     Got it. I've added this task: ");
        System.out.println(String.format("       %s", task));
        System.out.println(
                String.format("     Now you have %d task%s in the list.", store.size(), store.size() > 1 ? "s" : ""));
    }

    public static void list() {
        System.out.println("     Here are the tasks in your list:");
        int index = 1;
        for (Task item : store) {
            System.out.println(String.format("     %d.%s", index, item));
            index++;
        }
    }

    public static void setTaskDone(int index) {
        if (index > store.size()) {
            return;
        }
        Task task = store.get(index - 1);
        task.markAsDone();
        System.out.println("     Nice! I've marked this task as done: ");
        System.out.println(String.format("       %s", task));
    }
}
