package duke.manager;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TaskManager {

    public static final String TASK_TODO = "todo";
    public static final String TASK_EVENT = "event";
    public static final String TASK_DEADLINE = "deadline";
    public static final String TASK_ERROR = "I really cannot understand what you wrote";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_FIND = "find";
    public static final String COMMAND_BYE = "bye";
    public static final String COMMAND_DONE = "done";
    public static final String COMMAND_LIST = "list";

    /**
     * Prints a line on the console
     */
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Determines the task type,
     * assuming it is the first
     * word in the message and
     * returns it
     *
     * @param message The input by the user.
     * @return The first word of the string.
     */
    private static String taskType(String message) {
        String[] type = message.split(" ");
        return type[0];
    }


    /**
     * The function adds the task input by the user
     *
     * @param tasks   the array of tasks
     * @param message the input string containing
     */
    private static void addTask(ArrayList<Task> tasks, String message) {
        switch (taskType(message)) {
        case TASK_TODO:
            addTodo(tasks, message);
            break;
        case TASK_DEADLINE:
            addDeadline(tasks, message);
            break;
        case TASK_EVENT:
            addEvent(tasks, message);
            break;
        default:
            taskError();
            // Fallthrough
        }
    }

    /**
     * Error for wrong task type
     */
    private static void taskError() {
        printLine();
        System.out.println(TASK_ERROR);
        printLine();
    }

    /**
     * The function prints the recently added task
     */
    private static void printAddedTask(Task task, ArrayList<Task> tasks) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task.getDescription());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        printLine();
    }

    /**
     * The function prints the recently added task
     */
    private static void printDeletedTask(Task task, ArrayList<Task> tasks) {
        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.getDescription());
        System.out.println("Now you have " + (tasks.size() - 1) + " tasks in the list.");
        printLine();
    }

    /**
     * The function adds the todo task input by the user
     *
     * @param tasks   the array of tasks
     * @param message the input string containing
     */
    private static void addEvent(ArrayList<Task> tasks, String message) {
        try {
            message = message.substring(6);
            String[] eventData = message.split("/", 2);
            tasks.add(new Event(eventData[0], eventData[1].substring(3)));
            printAddedTask(tasks.get(tasks.size() - 1), tasks);
            FileManager.saveTasksToFile(tasks);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("YOU IDIOT !!??!! The description of an event cannot be empty.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("YOU IDIOT !!??!! The input format should be : ");
            System.out.println("event description /on date or time");
        }
    }

    /**
     * The function adds the deadline task input by the user
     *
     * @param tasks   the array of tasks
     * @param message the input string containing
     */
    private static void addDeadline(ArrayList<Task> tasks, String message) {
        try {
            message = message.substring(9);
            String[] deadlineData = message.split("/", 2);
            tasks.add(new Deadline(deadlineData[0], deadlineData[1].substring(3)));
            printAddedTask(tasks.get(tasks.size() - 1), tasks);
            FileManager.saveTasksToFile(tasks);
        } catch (StringIndexOutOfBoundsException e) {
            printLine();
            System.out.println("YOU IDIOT !!??!! The description of a deadline cannot be empty.");
            printLine();
        } catch (ArrayIndexOutOfBoundsException d) {
            printLine();
            System.out.println("YOU IDIOT !!??!! The input format should be : ");
            System.out.println("deadline description /by date or time");
            printLine();
        }
    }

    /**
     * The function adds the event input by the user
     *
     * @param tasks   the array of tasks
     * @param message the input string containing
     */
    private static void addTodo(ArrayList<Task> tasks, String message) {
        try {
            tasks.add(new Todo(message.substring(5)));
            printAddedTask(tasks.get(tasks.size() - 1), tasks);
            FileManager.saveTasksToFile(tasks);
        } catch (StringIndexOutOfBoundsException e) {
            printLine();
            System.out.println("OOPS!!! The description of a todo cannot be empty.");
            printLine();
        }
    }

    /**
     * Prints all the tasks
     *
     * @param tasks the array of tasks
     */
    private static void printTasks(ArrayList<Task> tasks) {
        printLine();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).getDescription());
        }
        if (tasks.size() == 0) {
            System.out.println("Smartass, you need to add tasks before listing them !!!");
        }
        printLine();
    }

    /**
     * Marks the task done based
     * on the integer value provided
     * by the user in the String message.
     *
     * @param tasks   the array of tasks
     * @param message the input string containing
     */
    private static void markDone(ArrayList<Task> tasks, String message) {
        try {
            String[] arrOfStr = message.split(" ");
            int index = Integer.parseInt(arrOfStr[arrOfStr.length - 1]) - 1;
            tasks.get(index).isDone();
            FileManager.saveTasksToFile(tasks);
        } catch (NullPointerException e) {
            printLine();
            System.out.println("OH MY GOD, can you maybe type a task that exists ?");
            printLine();
        }
    }

    /**
     * The function processes the inputs
     * by the user and calls the necessary
     * functions to add, print and manipulate
     * tasks.
     */
    public static void processInput() {

        ArrayList<Task> tasks = new ArrayList<>();
        FileManager.getStoredData(tasks);
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();

        while (!message.equals(COMMAND_BYE)) {
            if (message.equals(COMMAND_LIST)) {
                printTasks(tasks);
            } else if (message.contains(COMMAND_FIND)) {
                findTask(tasks, message);
            } else if (message.contains(COMMAND_DONE)) {
                markDone(tasks, message);
            } else if (message.contains(COMMAND_DELETE)) {
                deleteTask(tasks, message);
            } else {
                addTask(tasks, message);
            }
            message = scanner.nextLine();
        }
        scanner.close();
    }

    /**
     * Finds the matching tasks according to string
     * input by the user
     *
     * @param tasks   the array of tasks
     * @param message the input string
     */
    private static void findTask(ArrayList<Task> tasks, String message) {
        try {
            String filter = message.substring(5);
            ArrayList<Task> filteredTasks = (ArrayList<Task>) tasks.stream()
                    .filter((t) -> t.getDescription().contains(filter)).collect(Collectors.toList());
            printLine();
            System.out.println("Here are the matching tasks in your list:");
            for (Task task : filteredTasks) {
                System.out.println(task.getDescription());
            }
            printLine();
        } catch (NullPointerException e) {
            printLine();
            System.out.println("No tasks added yet");
            printLine();
        } catch (StringIndexOutOfBoundsException e) {
            printLine();
            System.out.println("find cannot be empty");
            printLine();
        }
    }

    /**
     * Deletes the task of the provided index
     *
     * @param tasks   the array of tasks
     * @param message the input string
     */
    private static void deleteTask(ArrayList<Task> tasks, String message) {
        try {
            String[] arrOfStr = message.strip().split(" ");
            int index = Integer.parseInt(arrOfStr[1]);
            printDeletedTask(tasks.get(index - 1), tasks);
            tasks.remove(index - 1);
            FileManager.saveTasksToFile(tasks);
        } catch (NullPointerException e) {
            printLine();
            System.out.println("OH MY GOD, can you maybe type things properly ?");
            System.out.println("Its delete {index}");
            printLine();
        } catch (IndexOutOfBoundsException e) {
            printLine();
            System.out.println("Dude there's no task at that index");
            printLine();
        }
    }
}
