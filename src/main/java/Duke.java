import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static final String DIVIDER = "    ____________________________________________________________";
    private static List<Task> store = new ArrayList<Task>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        greet();
        boolean exit = false;
        while (true) {
            if (exit) {
                break;
            }

            System.out.println("");
            String command = scan.nextLine();
            String[] commandParts = command.split(" /");
            String[] taskInfo = commandParts[0].split(" ", 2);

            switch (taskInfo[0]) {
            case "list":
                list();
                break;
            case "done":
                setTaskDone(Integer.parseInt(taskInfo[1]));
                break;
            case "todo":
                addTodo(taskInfo[1]);
                break;
            case "deadline":
                String[] deadlineInfo = commandParts[1].split(" ", 2);
                addDeadline(taskInfo[1], deadlineInfo[1]);
                break;
            case "event":
                String[] eventInfo = commandParts[1].split(" ", 2);
                addEvent(taskInfo[1], eventInfo[1]);
                break;
            case "bye":
                exit = true;
                break;
            default:
                break;
            }
        }

        bye();
        scan.close();
    }

    public static void greet() {
        System.out.println(DIVIDER);
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println(DIVIDER);
    }

    public static void bye() {
        System.out.println(DIVIDER);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
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
        System.out.println(DIVIDER);
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + task.toString());
        System.out.println(String.format("     Now you have %d tasks in the list.", store.size()));
        System.out.println(DIVIDER);
    }

    public static void list() {
        System.out.println(DIVIDER);
        System.out.println("     Here are the tasks in your list:");
        int index = 1;
        for (Task item : store) {
            System.out.println("     " + index + ". " + item.toString());
            index++;
        }
        System.out.println(DIVIDER);
    }

    public static void setTaskDone(int index) {
        if (index > store.size()) {
            return;
        }
        Task task = store.get(index - 1);
        task.markAsDone();
        System.out.println(DIVIDER);
        System.out.println("     Nice! I've marked this task as done: ");
        System.out.println("       " + task.toString());
        System.out.println(DIVIDER);
    }
}
