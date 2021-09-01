import java.util.Scanner;

/***
 * TODO:
 *       * FIX addTask STRING SPLITTING
 *       * CREATE MORE FUNCTIONS TO STREAMLINE
 *       * REFACTOR CODE
 *       * ERROR CODES
 */
public class Duke {
    public static void main(String[] args) {
        welcomeMessage();
        processInput();
        byeMessage();
    }

    public static void printMessage(String text) {
        System.out.println(System.lineSeparator() + "____________________________________________________________");
        System.out.println(text);
        System.out.println("____________________________________________________________" + System.lineSeparator());
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static String taskType(String message) {
        String[] type = message.split(" ");
        return type[0];
    }

    public static void addTask(Task[] tasks, String message) {
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

    public static void printTasks(Task[] tasks) {
        printLine();
        for (int i = 0; i < Task.COUNT; i++) {
            System.out.println((i + 1) + ". " + tasks[i].getDescription());
        }
        printLine();
    }

    public static void markDone(Task[] tasks, String message) {
        String[] arrOfStr = message.split(" ");
        int index = Integer.parseInt(arrOfStr[arrOfStr.length - 1]) - 1;
        tasks[index].isDone();
    }

    private static void processInput() {
        Task[] tasks = new Task[Task.MAX];
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

    private static void byeMessage(){
        printMessage("Bye. Hope to serve you again master!");
    }
    private static void welcomeMessage() {
        printLine();
        System.out.println("Howdy there! I'm Fluke");
        System.out.println("What can I do for you today master?");
        printLine();
    }
}
