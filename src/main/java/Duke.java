import java.util.Scanner;

public class Duke {

    public static final String LINE = "____________________________________________________________";

    public static void printList(Task[] list) {
        System.out.println(LINE);
        if (list[0] == null) {
            System.out.println("No items added!");
        }
        int i = 0;
        while (list[i] != null) {
            System.out.println((i+1) + ". " + list[i]);
            i += 1;
        }
        System.out.println(LINE);
    }

    public static void markTaskAsDone(Task[] list, int taskIndex) {
        if (taskIndex < Task.numItemsAdded && taskIndex >= 0) {
            if (list[taskIndex].isDone()) {
                System.out.println(LINE + "\nThat task is already done!\n" + LINE);
            } else {
                list[taskIndex].markAsDone();
                System.out.println(LINE + "\nNice! I've marked this task as done:");
                System.out.println(list[taskIndex] + "\n" + LINE);
            }
        } else {
            System.out.println("That task does not exist!\n" + LINE);
        }
    }

    public static void printAddedTask(Task[] list) {
        System.out.println(LINE + "\nGot it. I've added this task:");
        System.out.println(list[Task.numItemsAdded-1]);
        System.out.format("Now you have %d tasks in the list.\n" + LINE + "\n", Task.numItemsAdded);
    }

    public static void addTask(Task[] list, String input, String[] inputWords) {
        String taskType = inputWords[0];
        if (taskType.equalsIgnoreCase("todo")) {
            String taskName = input.substring(5);
            list[Task.numItemsAdded] = new Task(taskName);
            Task.numItemsAdded += 1;
            printAddedTask(list);
        } else if (taskType.equalsIgnoreCase("deadline")) {
            if (!input.contains("/by")) {
                System.out.println(LINE + "\nIncorrect format for entering task!\n" + LINE);
                return;
            }
            int taskEndIndex = input.indexOf("/by") - 1;
            String taskName = input.substring(9, taskEndIndex);
            String deadline = input.substring(taskEndIndex + 5);
            list[Task.numItemsAdded] = new Deadline(taskName, deadline);
            Task.numItemsAdded += 1;
            printAddedTask(list);
        } else if (taskType.equalsIgnoreCase("event")) {
            if (!input.contains("/at")) {
                System.out.println(LINE + "\nIncorrect format for entering task!\n" + LINE);
                return;
            }
            int taskEndIndex = input.indexOf("/at") - 1;
            String taskName = input.substring(6, taskEndIndex);
            String at = input.substring(taskEndIndex + 5);
            list[Task.numItemsAdded] = new Event(taskName, at);
            Task.numItemsAdded += 1;
            printAddedTask(list);
        } else {
            System.out.println(LINE + "\nThat is not a valid task type!\n" + LINE);
        }
    }

    public static void main(String[] args) { 
        System.out.println(LINE + "\n Hello! I'm Duke\n What can I do for you?\n" + LINE);
        Boolean isCompleted = false;
        Task[] list = new Task[100];
        while (!isCompleted) {
            Scanner in = new Scanner(System.in);
            String input = in.nextLine();
            String[] inputWords = input.split(" ");
            if (input.equalsIgnoreCase("bye")) {
                isCompleted = true;
                in.close();
                continue;
            }
            if (input.equalsIgnoreCase("list")){
                printList(list);
                continue;
            }
            if (inputWords[0].equalsIgnoreCase("done")) {
                int taskIndex = Integer.parseInt(inputWords[1]) -1;
                markTaskAsDone(list, taskIndex);
                continue;
            }
            addTask(list, input, inputWords);
        }
        System.out.println(LINE + "\n Bye. Hope to see you again soon!\n" + LINE);
    }
}