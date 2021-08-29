import java.util.Arrays;

public class TaskHandler {
    protected static final int MAX_TASK_COUNT = 100;
    protected static int taskCount = 0;
    protected static Task[] tasks = new Task[MAX_TASK_COUNT];

    public static void markTaskAsDone(String[] words) {
        int taskIndex = Integer.parseInt(words[1]) - 1;
        tasks[taskIndex].markAsDone();
    }

    public static void printTaskList() {
        System.out.println(Duke.INDENT + "Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(Duke.INDENT + (i + 1) + "." + tasks[i]);
        }
    }

    public static void printAddedTask() {
        System.out.println(Duke.INDENT + "Got it. I've added this task:" + Duke.LINE_SEPARATOR +
                Duke.INDENT + tasks[taskCount] + Duke.LINE_SEPARATOR +
                Duke.INDENT + "Now you have " + (taskCount + 1) + " tasks in the list.");
    }

    public static void addDeadline(String line) {
        String[] words = line.split("deadline");
        words = words[1].split("/by");
        tasks[taskCount] = new Deadline(words[0].trim(), words[1].trim());
        printAddedTask();
        taskCount++;
    }

    public static void addEvent(String line) {
        String[] words = line.split("event");
        words = words[1].split("/on");
        tasks[taskCount] = new Event(words[0].trim(), words[1].trim());
        printAddedTask();
        taskCount++;
    }

    public static void addTodo(String line) {
        String[] words = line.split("todo");
        tasks[taskCount] = new Todo(words[1].trim());
        printAddedTask();
        taskCount++;
    }




}
