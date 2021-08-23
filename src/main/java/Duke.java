import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        // Prints welcome message
        String welcomeMessage = "Hello! I'm Duke\n";
        welcomeMessage += "     What can I do for you?";
        customPrint(welcomeMessage);

        // ArrayList to store all the tasks
        ArrayList<Task> tasks = new ArrayList<>();

        // String to store user's input
        String description = "";

        Scanner in = new Scanner(System.in);

        while (true) {
            // Reads user input
            description = in.nextLine();

            // Handle the bye case
            if (description.equals("bye")) {
                customPrint("Bye. Hope to see you again soon!");
                break;
            } else if (description.equals("list")) {
                // Handle list case
                listTasks(tasks);
            } else if (description.startsWith("done")) {
                // Handle done case
                int taskId = Integer.parseInt(description.split(" ")[1]);
                if (taskId <= 0 || taskId > tasks.size()) {
                    customPrint("You have entered an invalid task ID!");
                    continue;
                }
                // Checks if task has been completed
                Task currentTask = tasks.get(taskId - 1);
                if (currentTask.isDone()) {
                    customPrint("You have already completed the task [" + currentTask.getDescription() + "]!");
                } else {
                    markDone(tasks, taskId - 1); // -1 as index starts from 0
                }
            } else {
                // Add new task
                tasks.add(new Task(description));
                customPrint("added: " + description);
            }
        }
    }

    /**
     * Prints out a statement with borders.
     *
     * @param statement Statement to be printed.
     */
    public static void customPrint(String statement) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     " + statement);
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints out all the Tasks with borders.
     *
     * @param tasks Arraylist of tasks entered by user.
     */
    public static void listTasks(ArrayList<Task> tasks) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            System.out.println("     " + (i + 1) + ".[" + currentTask.getStatusIcon() + "] "
                    + currentTask.getDescription());
        }
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Marks a task as done and calls customPrint to print the completed message.
     *
     * @param tasks  Arraylist of tasks entered by user.
     * @param taskId ID of task to be marked as completed.
     */
    public static void markDone(ArrayList<Task> tasks, int taskId) {
        Task currentTask = tasks.get(taskId);
        currentTask.markAsDone(); // Mark current task as done
        String textToPrint = "Nice! I've marked this task as done:\n";
        textToPrint += "       [" + currentTask.getStatusIcon() + "] " + currentTask.getDescription();
        customPrint(textToPrint);
    }
}
