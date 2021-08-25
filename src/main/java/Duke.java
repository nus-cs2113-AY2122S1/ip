import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static final String DIVIDER = "    ____________________________________________________________";
    private static List<Task> store = new ArrayList<Task>();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        greet();
        while (true) {
            System.out.println("");
            String command = scan.nextLine();
            String[] parts = command.split(" ");
            if (parts[0].equals("bye")) {
                break;
            } else {
                switch (parts[0]) {
                case "list":
                    list();
                    break;
                case "done":
                    setTaskDone(Integer.parseInt(parts[1]));
                    break;
                default:
                    add(command);
                }
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

    public static void echo(String command) {
        System.out.println(DIVIDER);
        System.out.println("     " + command);
        System.out.println(DIVIDER);
    }

    public static void add(String description) {
        System.out.println(DIVIDER);
        Task task = new Task(description);
        store.add(task);
        System.out.println("     added: " + description);
        System.out.println(DIVIDER);
    }

    public static void list() {
        System.out.println(DIVIDER);
        System.out.println("     Here are the tasks in your list:");
        int index = 1;
        for (Task item : store) {
            System.out.print("     " + index + ". ");
            item.print();
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
        System.out.print("       ");
        task.print();
        System.out.println(DIVIDER);
    }
}
