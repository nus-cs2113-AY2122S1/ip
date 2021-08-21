import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void printTaskList(Task[] tasks) {
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.length; i++) {
            System.out.println((i + 1) + ". " + "[" + tasks[i].getStatusIcon() +"] " + tasks[i].description);
        }
        System.out.println("____________________________________________________________");
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println(" Hey there! I'm Duke.");
        System.out.println(" How may I help you?");

        String line;
        Scanner in = new Scanner(System.in);
        String exitString = "bye";

        int taskCount = 0;
        Task[] tasks = new Task[100];
        line = in.nextLine();

        while (!line.equalsIgnoreCase(exitString)) {
            if (line.equalsIgnoreCase("list")) {
                printTaskList(Arrays.copyOf(tasks, taskCount));
            } else if (line.toLowerCase().startsWith("done")) {
                int taskToMarkDone = Integer.parseInt(line.split(" ")[1]);
                if (taskToMarkDone > taskCount || taskToMarkDone <= 0) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Sorry, the task is not in the list! Try again.");
                    System.out.println("____________________________________________________________");
                } else {
                    tasks[taskToMarkDone - 1].markDone();
                    System.out.println("____________________________________________________________");
                    System.out.println(" Great! I have marked the following task as done: ");
                    System.out.println("   [" + tasks[taskToMarkDone - 1].getStatusIcon() + "] " + tasks[taskToMarkDone - 1].description);
                    System.out.println("____________________________________________________________");
                }
            } else {
                tasks[taskCount] = new Task(line);
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println(" I have added a task: " + line);
                System.out.println("____________________________________________________________");
            }
            line = in.nextLine();
        }

        System.out.println("____________________________________________________________");
        System.out.println(" Goodbye! Hope to see you again soon.");
        System.out.println("____________________________________________________________");
    }
}
