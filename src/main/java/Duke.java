import java.util.Scanner;

public class Duke {

    public static final int MAX_TASKS = 100;
    public static String horizontal = ("____________________________________________________________\n");

    public static void sayHello() {
        System.out.println(horizontal + "Hello! I'm Duke\n" + "What can I do for you?\n" + horizontal);
    }

    public static void sayBye() {
        System.out.println(horizontal + "Bye. Hope to see you again soon!\n" + horizontal);
    }

    public static void addTask(String line, Task[] tasks, int numberOfTasks) {
        if (line.contains("todo")) {
            int startOfTask = 4;
            String task = line.trim().substring(startOfTask);
            tasks[numberOfTasks] = new ToDo(task);
            System.out.println(horizontal + "Got it. I've added this task: \n  " + tasks[numberOfTasks] +
                    "\n" + "Now you have " + (numberOfTasks + 1) + " task" + (numberOfTasks == 0? " " : "s ") +
                    "in the list. \n" + horizontal);
        } else if (line.contains("deadline")) {
            int startOfTask = 8;
            int slash = line.indexOf("/by");
            String task = line.trim().substring(startOfTask, slash);
            String dueDate = line.trim().substring(slash + 3);
            tasks[numberOfTasks] = new Deadline(task, dueDate);
            System.out.println(horizontal + "Got it. I've added this task: \n  " + tasks[numberOfTasks] +
                    "\n" + "Now you have " + (numberOfTasks + 1) + " task" + (numberOfTasks == 0? " " : "s ") +
                    "in the list. \n" + horizontal);
        } else if (line.contains("event")) {
            int startOfTask = 5;
            int slash = line.indexOf("/at");
            String task = line.trim().substring(startOfTask, slash);
            String eventTime = line.trim().substring(slash + 3);
            tasks[numberOfTasks] = new Event(task, eventTime);
            System.out.println(horizontal + "Got it. I've added this task: \n  " + tasks[numberOfTasks] +
                    "\n" + "Now you have " + (numberOfTasks + 1) + " task" + (numberOfTasks == 0? " " : "s ") +
                    "in the list. \n" + horizontal);
        } else {
            System.out.println(horizontal + "Invalid command entered. \n" + horizontal);
        }
    }

    public static void listTasks(Task[] tasks, int numberOfTasks) {
        System.out.print(horizontal + "Here are the tasks in your list: \n");
        for (int i = 1; i <= numberOfTasks; i++) {
            System.out.println(i + "." + tasks[i - 1]);
        }
        System.out.println(horizontal);
    }

    public static void markDone(Task[] tasks, int taskDone) {
        tasks[taskDone - 1].markAsDone();
        System.out.println(horizontal + "Nice! I've marked this task as done: \n  "
                + tasks[taskDone - 1] + "\n" + horizontal);
    }

    public static void main(String[] args) {
        Task[] tasks = new Task[MAX_TASKS];
        int numberOfTasks = 0;
        sayHello();
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                listTasks(tasks, numberOfTasks);
            } else if (line.contains("done")) {
                int endOfDone = 4;
                int taskDone = Integer.parseInt(line.substring(endOfDone).trim());
                markDone(tasks, taskDone);
            } else {
                addTask(line, tasks, numberOfTasks);
                if (line.contains("todo") || line.contains("deadline") || line.contains("event")) {
                    numberOfTasks++;
                }
            }
            line = in.nextLine();
        }
        sayBye();
    }
}
