import java.util.Locale;
import java.util.Scanner;

public class Duke {

    public static int taskCount = 1;

    public static void addTask(String input, Task[] tasks) {
        printHorizontalLine();
        int dividePos = input.trim().indexOf(" ");
        String taskType = input.trim().substring(0, dividePos).toLowerCase();

        if (taskType.equalsIgnoreCase("todo")) {
            addTodo(input, tasks);
        } else if (taskType.equalsIgnoreCase("deadline")) {
            addDeadline(input, tasks);
        } else if (taskType.equalsIgnoreCase("event")) {
            addEvent(input, tasks);
        } else {
            System.out.println("Invalid task type, please try again. Valid task types: todo, deadline, event");
            printHorizontalLine();
            return;
        }

        System.out.println("Understood. I've added this task:");
        System.out.println(tasks[taskCount]);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        taskCount++;
        printHorizontalLine();
    }

    public static void addTodo(String input, Task[] tasks) {
        int dividePos = input.trim().indexOf(" ");
        String taskName = input.trim().substring(dividePos);
        Task t = new Todo(taskName);
        tasks[taskCount] = t;
    }

    public static void addDeadline(String input, Task[] tasks) {
        int dividePos = input.trim().indexOf(" ");
        int deadlinePos = input.trim().indexOf("/");
        String taskName = input.trim().substring(dividePos, deadlinePos);
        String deadlineDay = input.trim().substring(deadlinePos + 3);
        tasks[taskCount] = new Deadline(taskName, deadlineDay);
    }

    public static void addEvent(String input, Task[] tasks) {
        int dividePos = input.trim().indexOf(" ");
        int eventPos = input.trim().indexOf("/");
        String taskName = input.trim().substring(dividePos, eventPos);
        String deadlineDay = input.trim().substring(eventPos + 3);
        tasks[taskCount] = new Event(taskName, deadlineDay);
    }

    public static void requestList(Task[] tasks) {
        printHorizontalLine();
        if (taskCount == 0) {
            System.out.println("There are no tasks!");
            printHorizontalLine();
            return;
        }

        //CHANGE THIS
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i < taskCount; i++) {
            System.out.println(i + "." + tasks[i]);
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
        System.out.println(t);
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
        System.out.println(t);
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

        Scanner in = new Scanner(System.in);
        while (!isBye) {
            line = in.nextLine();
            if (line.equalsIgnoreCase("bye")) {
                inputBye();
                isBye = true;
            } else if (line.equalsIgnoreCase("list")) {
                requestList(tasks);
            } else if (line.contains("done")) {
                inputDone(line, tasks);
            } else if (line.contains("undo")) {
                undoDone(line, tasks);
            } else {
                addTask(line, tasks);
            }
        }
    }
}