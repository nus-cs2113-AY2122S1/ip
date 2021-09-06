import task.type.Deadline;
import task.type.Event;
import task.type.Task;
import task.type.Todo;

import java.util.Scanner;

public class Duke {
    private static Task[] taskList;
    private static int taskCount;

    private static void printIncorrectCommandError(String input) throws DukeException {
        printWithLines("☹ OOPS!!! I cannot understand");
        throw new DukeException();
    }

    public static void addTask(String task) {
        boolean isValidCommand = true;
        try {
            if (task.startsWith("todo")) {
                addToDo(task);
            } else if (task.startsWith("event")) {
                addEvent(task);
            } else if (task.startsWith("deadline")) {
                addDeadline(task);
            } else {
                printIncorrectCommandError(task);
            }
        } catch (DukeException e) {
            isValidCommand = false;
        }
        if (isValidCommand) {
            printWithLines("Got it. I've added this task:\n" + taskList[taskCount].toString() + "\nNow you have " + (taskCount + 1) + " tasks in the list");
            taskCount++;
        }
    }

    private static void addToDo(String task) throws DukeException {
        String todoDescription;
        try {
            todoDescription = task.trim().split("todo")[1];
        } catch (IndexOutOfBoundsException e) {
            printWithLines("☹ OOPS!!! The description of todo cannot be empty.");
            throw new DukeException();
        }
        taskList[taskCount] = new Todo(todoDescription);
    }

    private static void addDeadline(String task) throws DukeException {
        String deadlineInput;
        try {
            deadlineInput = task.trim().split("deadline")[1];
        } catch (IndexOutOfBoundsException e) {
            printWithLines("☹ OOPS!!! The description of deadline cannot be empty.");
            throw new DukeException();
        }
        taskList[taskCount] = new Deadline(task.substring("deadline".length(), task.indexOf("/by")), task.substring(task.indexOf("/by") + "/by".length()));
    }

    private static void addEvent(String task) throws DukeException {
        String eventInput;
        try {
            eventInput = task.trim().split("event")[1];
        } catch (IndexOutOfBoundsException e) {
            printWithLines("☹ OOPS!!! The description of event cannot be empty.");
            throw new DukeException();
        }
        taskList[taskCount] = new Event(task.substring("event".length(), task.indexOf("/at")), task.substring(task.indexOf("/at") + "/at".length()));
    }

    public static void listTasks() {
        String tasksAsList = "";
        for (int i = 0; i < taskCount; i++) {
            tasksAsList = tasksAsList.concat((i + 1) + "." +
                    taskList[i].toString() + "\n");
        }
        tasksAsList = tasksAsList.substring(0, tasksAsList.length() - 1);
        printWithLines("Here are the tasks in your list:\n" + tasksAsList);
    }

    public static void markTaskAsDone(String task) {
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

    public static void main(String[] args) throws DukeException {
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
                listTasks();
            } else if (userInput.startsWith("done")) {
                markTaskAsDone(userInput);
            } else {
                addTask(userInput);
            }
            userInput = in.nextLine();
        }
        printWithLines("Bye. Hope to see you again soon!");
    }
}