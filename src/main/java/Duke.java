import java.util.Scanner;

public class Duke {

    public static void addTask(String input, Task[] tasks, int taskCount) {
        Task t = new Task(input);
        tasks[taskCount] = t;
        System.out.println("added: " + input);
    }

    public static void requestList(Task[] tasks, int taskCount) {
        if (taskCount == 1) {
            System.out.println("There are no tasks!");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < taskCount; i++) {
            System.out.println(tasks[i].taskId + "." + tasks[i].getStatusIcon() + " " + tasks[i].description);
        }
    }

    public static void inputBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void inputDone(String line, Task[] tasks) {
        int dividePos = line.indexOf(" ");
        int taskNumber = Integer.parseInt(line.trim().substring(dividePos + 1));
        Task t = tasks[taskNumber];
        t.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t.getStatusIcon() + " " + t.description);
    }

    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        String line;
        boolean isBye = false;

        Task[] tasks = new Task[100];
        int taskCount = Task.getNumberOfTasks();

        Scanner in = new Scanner(System.in);
        while (!isBye) {
            line = in.nextLine();
            if (line.equalsIgnoreCase("bye")) {
                inputBye();
                isBye = true;
            } else if (line.equalsIgnoreCase("list")) {
                requestList(tasks, taskCount);
            } else if (line.contains("done")) {
                inputDone(line, tasks);
            } else {
                addTask(line, tasks, taskCount);
                taskCount++;
            }
        }
    }
}