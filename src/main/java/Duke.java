import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "------------------------------------";
        System.out.println("Hello from\n" + logo);
        System.out.println(line);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println(line);

        String input;
        Task[] tasks = new Task[100];
        int taskCount = 0;

        Scanner in = new Scanner(System.in);
        input = in.nextLine();

        while (!input.equals("bye")) {

            String keyword = input.split(" ")[0].toLowerCase();
            String description;
            String by;
            String at;
            switch (keyword) {
            case "bye":
                continue;
            case "list":
                for (int i = 0; i < taskCount; i++) {
                    tasks[i].printTask(i + 1);
                }
                break;
            case "todo":
                System.out.println("Got it. I've added this task:");
                tasks[taskCount] = new Todo(input);
                System.out.println(tasks[taskCount].getTaskInfo());
                taskCount++;
                System.out.println("Now you have " + taskCount + " tasks in the list");
                break;
            case "deadline":
                System.out.println("Got it. I've added this task:");
                description = input.split("/")[0];
                by = input.split("/")[1];
                tasks[taskCount] = new Deadline(description, by);
                System.out.println(tasks[taskCount].getTaskInfo());
                taskCount++;
                System.out.println("Now you have " + taskCount + " tasks in the list");
                break;
            case "event":
                System.out.println("Got it. I've added this task:");
                description = input.split("/")[0];
                at = input.split("/")[1];
                tasks[taskCount] = new Event(description, at);
                System.out.println(tasks[taskCount].getTaskInfo());
                taskCount++;
                System.out.println("Now you have " + taskCount + " tasks in the list");
                break;
            case "done":
                int index = Integer.parseInt(input.split(" ", 2)[1]);
                tasks[index - 1].completeTask();
                System.out.println("Nice! I have marked this task as done: ");
                System.out.println(tasks[index - 1].getTaskInfo());
                break;
            default:
                System.out.println("You need to specify the task type!");
                break;
            }
            input = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
