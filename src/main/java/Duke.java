import java.util.*;

public class Duke {
    private static Task[] taskList;
    private static int taskCount;

    public static void addTask(String task) {
        if (task.startsWith("todo")) {
            taskList[taskCount] = new Todo(task.substring("task".length()));
        } else if (task.startsWith("deadline")) {
            taskList[taskCount] = new Deadline(task.substring("deadline".length(), task.indexOf("/by")), task.substring(task.indexOf("/by") + "/by".length()));
        } else if (task.startsWith("event")) {
            taskList[taskCount] = new Event(task.substring("event".length(), task.indexOf("/at")), task.substring(task.indexOf("/at") + "/at".length()));
        }
        printWithLines("Got it. I've added this task:\n" + taskList[taskCount].toString() + "\nNow you have " + (taskCount + 1) + " tasks in the list");
        taskCount++;
    }

    public static void listTask() {
        String tasksAsList = "";
        for (int i = 0; i < taskCount; i++) {
            tasksAsList = tasksAsList.concat((i + 1) + "." +
                    taskList[i].toString() + "\n");
        }
        tasksAsList = tasksAsList.substring(0, tasksAsList.length() - 1);
        printWithLines("Here are the tasks in your list:\n" + tasksAsList);
    }

    public static void markTaskDone(String task) {
        int taskIndex = Integer.parseInt(task.replace("done ", "")) -  1;
        if (taskIndex > taskCount - 1) {
            printWithLines(" The task " + (taskIndex + 1) + " doesn't exist.\nMake sure a valid task number is entered.");
            return;
        }
        Task current = taskList[taskIndex];
        current.markAsDone();
        printWithLines("Nice! I've marked this task as done:\n" + current.toString());
    }

    public static void printWithLines(String output) {
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine);
        System.out.println(output);
        System.out.println(horizontalLine);
    }

    public static void main(String[] args) {
        taskList = new Task[100];
        taskCount = 0;
        String userInput;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printWithLines("Hello! I'm Duke\n" + "What can I do for you?");
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();
        while (!userInput.equals("bye"))
        {
            if (userInput.equals("list")) {
                listTask();
            } else if (userInput.startsWith("done")) {
                markTaskDone(userInput);
            } else {
                addTask(userInput);
            }
            userInput = in.nextLine();
        }
        printWithLines("Bye. Hope to see you again soon!");
    }
}