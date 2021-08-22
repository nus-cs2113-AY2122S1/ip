import java.util.*;

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
        String[] tasks = new String[100];
        int taskCount = 0;
        do {
            Scanner in = new Scanner(System.in);
            input = in.nextLine();
            if (input.equals("bye")) {
                break;
            }
            if (input.equals("list")) {
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
            }
            else {
                tasks[taskCount] = input;
                System.out.println("Added: " + input);
                taskCount++;
            }

        } while (!input.equals("bye"));
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
