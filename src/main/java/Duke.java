import java.util.Scanner;

public class Duke {

    public static String horizontal = ("____________________________________________________________\n");

    public static void sayHello() {
        System.out.println(horizontal + "Hello! I'm Duke\n" + "What can I do for you?\n" + horizontal);
    }

    public static void sayBye() {
        System.out.println(horizontal + "Bye. Hope to see you again soon!\n" + horizontal);
    }

    public static void addTask(String task, Task[] tasks, int numberOfTasks) {
        System.out.println(horizontal + "added: " + task + "\n" + horizontal);
        tasks[numberOfTasks] = new Task(task);
    }

    public static void listTasks(Task[] tasks, int numberOfTasks) {
        System.out.print(horizontal);
        for (int i = 1; i <= numberOfTasks; i++) {
            System.out.println(i + ".[" + tasks[i - 1].getStatusIcon() + "] " + tasks[i - 1].getDescription());
        }
        System.out.println(horizontal);
    }

    public static void markDone(Task[] tasks, int taskDone) {
        System.out.println(horizontal + "Nice! I've marked this task as done: \n  [X] "
                + tasks[taskDone - 1].getDescription() + "\n" + horizontal);
        tasks[taskDone - 1].markAsDone();
    }

    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int numberOfTasks = 0;
        sayHello();
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                listTasks(tasks, numberOfTasks);
            } else if (line.contains("done")) {
                int taskDone = Integer.parseInt(line.substring(5));
                markDone(tasks, taskDone);
            } else {
                addTask(line, tasks, numberOfTasks);
                numberOfTasks++;
            }
            line = in.nextLine();
        }
        sayBye();
    }
}
