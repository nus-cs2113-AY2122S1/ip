import java.util.Scanner;

public class Duke {
    // Horizontal line used for improving readability
    private static String horizontalLine = "____________________________________________________________";

    // Print greeting message
    private static void printGreeting() {
        // Greeting message
        String greeting = horizontalLine + System.lineSeparator() +
                " Hello! I'm Duke" + System.lineSeparator() +
                " What can I do for you?" + System.lineSeparator() +
                horizontalLine + System.lineSeparator();
        System.out.println(greeting);
    }

    // Print farewell message
    private static void printFarewell() {
        // Farewell message
        String farewell = " Bye. Hope to see you again soon!";
        System.out.println(farewell);
    }

    private static void executeList(TaskManager taskManager) {
        System.out.println(" Here are the tasks in your list:");
        taskManager.listTasks();
    }

    private static void executeDone(TaskManager taskManager, String taskId) {
        int num = Integer.parseInt(taskId);
        Task task = taskManager.markAsDone(num - 1);
        System.out.println(" Nice! I've marked this task as done:" + System.lineSeparator() +
                "   " + task.getStatusIcon() + " " + task.getDescription());
    }

    private static void executeAdd(TaskManager taskManager, String line) {
        taskManager.addTask(line);
        System.out.println(" added: " + line);
    }

    private static void execute(String line, TaskManager taskManager) {
        String[] words = line.split(" "); // Convert sentence into array of words
        if (line.equals("bye")) { // Print farewell message and exit
            printFarewell();
        } else if (line.equals("list")) { // Print out task list
            executeList(taskManager);
        } else if (words[0].equals("done")) { // Mark the specified task as done
            executeDone(taskManager, words[1]);
        } else { // Add new task to the task list
            executeAdd(taskManager, line);
        }
    }

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        printGreeting();

        // Scanner class for reading user input
        String line;
        Scanner in = new Scanner(System.in);

        // While user has not said "bye", check user input repetitively
        do {
            line = in.nextLine();
            System.out.println(horizontalLine);
            execute(line, taskManager);
            System.out.println(horizontalLine + System.lineSeparator());
        } while (!line.equals("bye"));
    }
}
