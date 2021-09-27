package duke.manager;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
    public static DateTimeFormatter stringFormatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd, uuuu hh:mm a");


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
        Ui.printLine();
        System.out.println(TASK_ERROR);
        Ui.printLine();
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
            tasks.add(new Event(eventData[0], processDateAndTime(eventData[1].substring(3)), eventData[1].substring(3)));
            Ui.printAddedTask(tasks.get(tasks.size() - 1), tasks);
            Storage.saveTasksToFile(tasks);
        } catch (StringIndexOutOfBoundsException e) {
            Ui.printLine();
            System.out.println("YOU IDIOT !!??!! The description of an event cannot be empty.");
            Ui.printLine();
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printLine();
            System.out.println("YOU IDIOT !!??!! The input format should be : ");
            System.out.println("event description /on date or time");
            Ui.printLine();
        } catch (DateTimeParseException e) {
            Ui.printLine();
            System.out.println("Please enter both date and time in the format");
            System.out.println("YYYY-MM-DD HH:MM");
            Ui.printLine();
        }
    }

    /**
     * Returns the LocalDateTime object after processing
     * the input string
     *
     * @param message input string containing the date and time
     * @return LocalDateTime object "yyyy-MM-dd HH:mm"
     */
    private static LocalDateTime processDateAndTime(String message) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime info = LocalDateTime.parse(message.strip(), formatter);
        return info;
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
            tasks.add(new Deadline(deadlineData[0], processDateAndTime(deadlineData[1].substring(3)), deadlineData[1].substring(3)));
            Ui.printAddedTask(tasks.get(tasks.size() - 1), tasks);
            Storage.saveTasksToFile(tasks);
        } catch (StringIndexOutOfBoundsException e) {
            Ui.printLine();
            System.out.println("YOU IDIOT !!??!! The description of a deadline cannot be empty.");
            Ui.printLine();
        } catch (ArrayIndexOutOfBoundsException d) {
            Ui.printLine();
            System.out.println("YOU IDIOT !!??!! The input format should be : ");
            System.out.println("deadline description /by date or time");
            Ui.printLine();
        } catch (DateTimeParseException e) {
            Ui.printLine();
            System.out.println("Please enter both date and time in the format");
            System.out.println("YYYY-MM-DD HH:MM");
            Ui.printLine();
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
            Ui.printAddedTask(tasks.get(tasks.size() - 1), tasks);
            Storage.saveTasksToFile(tasks);
        } catch (StringIndexOutOfBoundsException e) {
            Ui.printLine();
            System.out.println("OOPS!!! The description of a todo cannot be empty.");
            Ui.printLine();
        }
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
            Storage.saveTasksToFile(tasks);
        } catch (NullPointerException e) {
            Ui.printLine();
            System.out.println("OH MY GOD, can you maybe type a task that exists ?");
            Ui.printLine();
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
        Storage.getStoredData(tasks);
        Scanner scanner = new Scanner(System.in);
        String message = scanner.nextLine();

        while (!message.equals(COMMAND_BYE)) {
            if (message.equals(COMMAND_LIST)) {
                Ui.printTasks(tasks);
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
            Ui.printLine();
            System.out.println("Here are the matching tasks in your list:");
            for (Task task : filteredTasks) {
                System.out.println(task.getDescription());
            }
            Ui.printLine();
        } catch (NullPointerException e) {
            Ui.printLine();
            System.out.println("No tasks added yet");
            Ui.printLine();
        } catch (StringIndexOutOfBoundsException e) {
            Ui.printLine();
            System.out.println("find cannot be empty");
            Ui.printLine();
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
            Ui.printDeletedTask(tasks.get(index - 1), tasks);
            tasks.remove(index - 1);
            Storage.saveTasksToFile(tasks);
        } catch (NullPointerException e) {
            Ui.printLine();
            System.out.println("OH MY GOD, can you maybe type things properly ?");
            System.out.println("Its delete {index}");
            Ui.printLine();
        } catch (IndexOutOfBoundsException e) {
            Ui.printLine();
            System.out.println("Dude there's no task at that index");
            Ui.printLine();
        }
    }
}
