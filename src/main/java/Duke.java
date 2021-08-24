import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "\t ____        _        \n"
                + "\t|  _ \\ _   _| | _____ \n"
                + "\t| | | | | | | |/ / _ \\\n"
                + "\t| |_| | |_| |   <  __/\n"
                + "\t|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("\t____________________________________________________________");
        System.out.println(logo);
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________\n");

        Scanner in = new Scanner(System.in);
        String user_input = in.nextLine().trim();

        Task[] tasks = new Task[100];

        int tasks_index = 0;
        while (!user_input.equals("bye")) {
            System.out.println("\t____________________________________________________________");

            if (user_input.equals("list")) {
                System.out.println("\tHere are the tasks in your list:");
                //listing out tasks if user_input == "list"
                for (int i = 0; i < tasks_index; i++) {
                    System.out.println("\t" + (i + 1) + "." +
                            "[" + tasks[i].getStatusIcon() + "] " +
                            tasks[i].getDescription());
                }
            } else if (user_input.startsWith("done")) {
                String task_number_str = user_input.substring(5);
                int task_number = Integer.parseInt(task_number_str);
                //task_number displayed starting with 1
                //but array starts with 0
                (tasks[task_number - 1]).markAsDone();

                System.out.println("\tNice! I've marked this task as done:");
                System.out.println("\t  [X] " + (tasks[task_number - 1]).getDescription());

            } else {
                //instantiate new Task, store in array
                tasks[tasks_index] = new Task(user_input);
                tasks_index++;
                System.out.println("\tadded: " + user_input);
            }
            System.out.println("\t____________________________________________________________");
            user_input = in.nextLine().trim();
        }

        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }
}
