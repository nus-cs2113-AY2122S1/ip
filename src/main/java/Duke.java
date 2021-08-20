import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void printTaskList(String[] tasks) {
        System.out.println("____________________________________________________________");
        System.out.println(" Task list:");
        for (int i = 0; i < tasks.length; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
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
        String[] tasks = new String[100];
        line = in.nextLine();

        while (!line.equals(exitString)) {
            if (line.equals("list")) {
                printTaskList(Arrays.copyOf(tasks, taskCount));
            } else {
                tasks[taskCount] = line;
                taskCount++;
                System.out.println("____________________________________________________________");
                System.out.println(" Added task: " + line);
                System.out.println("____________________________________________________________");
            }
            line = in.nextLine();
        }

        System.out.println("____________________________________________________________");
        System.out.println(" Goodbye! Hope to see you again soon.");
        System.out.println("____________________________________________________________");
    }
}
