import java.util.Scanner;

public class Duke {

    public static void addTask(String input, Task[] tasks, int taskCount) {
        Task t = new Task(input);
        tasks[taskCount] = t;
        printHorizontalLine();
        System.out.println("Understood. I've added this task: " + System.lineSeparator() + input);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        printHorizontalLine();
    }

    public static void requestList(Task[] tasks, int taskCount) {
        printHorizontalLine();
        if (taskCount == 1) {
            System.out.println("There are no tasks!");
            printHorizontalLine();
            return;
        }

        //CHANGE THIS
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < taskCount; i++) {
            System.out.println(tasks[i]);
        }
        printHorizontalLine();
    }

    public static void inputBye() {
        printHorizontalLine();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontalLine();
    }

    public static void inputDone(String line, Task[] tasks) {
        int dividePos = line.indexOf(" ");
        int taskNumber = Integer.parseInt(line.trim().substring(dividePos + 1));
        Task t = tasks[taskNumber];
        if (t.isDone) {
            printHorizontalLine();
            System.out.println("This task has already been done, complete something else!");
            printHorizontalLine();
            return;
        }
        t.markAsDone();
        printHorizontalLine();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t.getStatusIcon() + " " + t.description);
        printHorizontalLine();
    }

    public static void undoDone(String line, Task[] tasks) {
        int dividePos = line.indexOf(" ");
        int taskNumber = Integer.parseInt(line.trim().substring(dividePos + 1));
        Task t = tasks[taskNumber];
        if (!t.isDone) {
            printHorizontalLine();
            System.out.println("This task has not been done yet!");
            printHorizontalLine();
            return;
        }
        t.markAsNotDone();
        printHorizontalLine();
        System.out.println("I've undone this task for you: ");
        System.out.println(t.getStatusIcon() + " " + t.description);
        printHorizontalLine();
    }

    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {

        String logo = "    #    ####### #          #     #####  \n"
                + "   # #      #    #         # #   #     # \n"
                + "  #   #     #    #        #   #  #       \n"
                + " #     #    #    #       #     #  #####  \n"
                + " #######    #    #       #######       # \n"
                + " #     #    #    #       #     # #     # \n"
                + " #     #    #    ####### #     #  #####  \n";

        System.out.println("Hello from\n" + logo);

        printHorizontalLine();
        System.out.println("Hello! I'm Atlas!");
        System.out.println("What can I do for you today?");
        printHorizontalLine();

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
            } else if (line.contains("undo")) {
                undoDone(line, tasks);
            } else {
                addTask(line, tasks, taskCount);
                taskCount++;
            }
        }
    }
}