import java.util.Scanner;

public class TaskManager {

    Task[] tasks = new Task[Task.MAX];
    /**
     * Prints a line on the console
     */
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Determines the task type,
     * assuming it is the first
     * word in the message and
     * returns it
     *
     * @param message The input by the user.
     * @return The first word of the string.
     */
    private static String taskType(String message) {
        String[] type = message.split(" ");
        return type[0];
    }

    /**
     * The function adds the task input by the user
     *
     * @param tasks   the array of tasks
     * @param message the input string containing
     *                the task description
     */
    private static void addTask(Task[] tasks, String message) {
        switch (taskType(message)) {
        case "todo":
            tasks[Task.COUNT] = new Todo(message.substring(5));
            Task.COUNT++;
            break;
        case "deadline":
            message = message.substring(9);
            String[] deadlineData = message.split("/", 2);
            tasks[Task.COUNT] = new Deadline(deadlineData[0], deadlineData[1].substring(3));
            Task.COUNT++;
            break;
        case "event":
            message = message.substring(6);
            String[] eventData = message.split("/", 2);
            tasks[Task.COUNT] = new Event(eventData[0], eventData[1].substring(3));
            Task.COUNT++;
            break;
        default:
        }
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[Task.COUNT - 1].getDescription());
        System.out.println("Now you have " + Task.COUNT + " tasks in the list.");
        printLine();
    }

    /**
     * Prints all the tasks
     */
    private static void printTasks(Task[] tasks) {
        printLine();
        for (int i = 0; i < Task.COUNT; i++) {
            System.out.println((i + 1) + ". " + tasks[i].getDescription());
        }
        printLine();
    }

    /**
     * Marks the task done based
     * on the integer value provided
     * by the user in the String message.
     *
     * @param tasks   the array of tasks
     * @param message the input string containing
     *                the task description
     */
    private static void markDone(Task[] tasks, String message) {
        String[] arrOfStr = message.split(" ");
        int index = Integer.parseInt(arrOfStr[arrOfStr.length - 1]) - 1;
        tasks[index].isDone();
    }

    /**
     * The function processes the inputs
     * by the user and calls the necessary
     * functions to add, print and manipulate
     * tasks.
     */
    public static void processInput() {

        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();

        while (!message.equals("bye")) {
            if (message.equals("list")) {
                printTasks(tasks);
            } else if (message.contains("done")) {
                markDone(tasks, message);
            } else {
                addTask(tasks, message);
            }
            message = scanner.nextLine();
        }
        scanner.close();
    }
}
