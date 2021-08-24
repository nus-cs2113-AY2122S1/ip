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

        do {
            Scanner in = new Scanner(System.in);
            input = in.nextLine();
            if (input.equals("bye")) {
                continue;
            }
            if (input.equals("list")) {
                for (int i = 0; i < taskCount; i++) {
                    tasks[i].printTask(i + 1);
                }
            } else if (input.startsWith("done")) {
                int finishedIndex = Integer.parseInt(input.substring(5));
                tasks[finishedIndex - 1].completeTask();
                System.out.println("Nice! I have marked this task as done: ");
                System.out.println(tasks[finishedIndex - 1].getTaskInfo());
            } else {
                tasks[taskCount] = new Task(input);
                taskCount++;
                System.out.println("added: " + input);
            }
        } while (!input.equals("bye"));
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
