import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        //print welcome message
        String welcomeMessage = "Hello! I'm Duke\n";
        welcomeMessage += "     What can I do for you?";
        custom_print(welcomeMessage);

        ArrayList<Task> tasks = new ArrayList<>();

        String description = "";

        Scanner in = new Scanner(System.in);

        while (true) {
            description = in.nextLine();

            // Handle the bye case
            if (description.equals("bye")) {
                custom_print("Bye. Hope to see you again soon!");
                break;
            } else if (description.equals("list")) { //handle list case
                print_list(tasks);
            } else if (description.startsWith("done")) { //handle done case
                int taskId = Integer.parseInt(description.split(" ")[1]);
                mark_done(tasks, taskId - 1); //-1 as index starts from 0
            } else {
                tasks.add(new Task(description));
                custom_print("added: " + description);
            }
        }
    }

    public static void custom_print(String statement) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     " + statement);
        System.out.println("    ____________________________________________________________");
    }

    public static void print_list(ArrayList<Task> tasks) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            System.out.println("     " + String.valueOf(i + 1) + ".[" + currentTask.getStatusIcon() + "] " + currentTask.getDescription());
        }
        System.out.println("    ____________________________________________________________");
    }

    public static void mark_done(ArrayList<Task> tasks, int taskId) {
        Task currentTask = tasks.get(taskId);
        currentTask.markAsDone(); //mark task as done
        String textToPrint = "Nice! I've marked this task as done:\n";
        textToPrint += "       [" + currentTask.getStatusIcon() + "] " + currentTask.getDescription();
        custom_print(textToPrint);
    }
}
