import java.util.Scanner;

public class Duke {
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    public static void printList() {
        String line = "____________________________________________________________\n";
        System.out.print(line);
        int count = 1;
        for (Task item : tasks) {
            if (item == null) {
                break;
            }
            System.out.println(" " + count + ". " + item.description);
            count = count + 1;
        }
        System.out.println(line);
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String greet = "____________________________________________________________\n"
                + " Hello! I'm Duke\n"
                + " What can I do for you?\n"
                + "____________________________________________________________\n";
        System.out.println(greet);
        String command;
        Scanner in = new Scanner(System.in);
        String addition;
        String bye = "____________________________________________________________\n"
                +  " Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n";
        do {
            command = in.nextLine();
            switch (command) {
            case "bye":
                break;
            case "list":
                printList();
                break;
            default:
                tasks[taskCount] = new Task(command);
                taskCount = taskCount + 1;
                addition = "____________________________________________________________\n"
                        + " added: "
                        + command
                        + "\n"
                        + "____________________________________________________________\n";
                System.out.println(addition);
            }
        } while (!command.equals("bye"));
        System.out.println(bye);
    }
}
