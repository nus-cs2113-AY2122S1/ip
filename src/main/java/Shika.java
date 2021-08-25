import java.util.Scanner;
import java.util.ArrayList;

import shikabot.task.Task;
import shikabot.task.Todo;
import shikabot.task.Deadline;
import shikabot.task.Event;

import shikabot.exception.InvalidCommandException;
import shikabot.exception.TaskNegativeException;
import shikabot.exception.TaskNotFoundException;
import shikabot.exception.InvalidEventException;
import shikabot.exception.InvalidDeadlineException;

public class Shika {

    public static String line = "____________________________________________________________________________\n";

    /**
     * Main function that calls other functions to run Shika.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        greetUser();
        runShika();
    }

    /**
     * Function that prints Shika logo and a greeting message.
     */
    public static void greetUser() {
        String logo = "  _________.__    .__ __            \n"
                + " /   _____/|  |__ |__|  | _______   \n"
                + " \\_____  \\ |  |  \\|  |  |/ /\\__  \\  \n"
                + " /        \\|   Y  \\  |    <  / __ \\_\n"
                + "/_______  /|___|  /__|__|_ \\(____  /\n"
                + "        \\/      \\/        \\/     \\/ \n";

        System.out.println(logo + "\nHello, friend! Shika at your service! o7\n");
    }

    /**
     * Function that calls getCommand() in a loop to run Shika. Loop can be exited by inputting "bye".
     */
    public static void runShika() {
        ArrayList<Task> tasks = new ArrayList<>();
        Task.count = 0;
        Scanner in = new Scanner(System.in);
        String text;
        while(in.hasNextLine()) {
            text = in.nextLine();
            if (text.trim().equals("bye")) {
                System.out.print(line + "> Bye friend!\n> See you again! :)\n" + line);
                return;
            }
            try {
                getCommand(tasks, text);
            } catch (InvalidCommandException e) {
                System.out.print(line + "> Sorry friend, I don't know what that means. :/\n" + line);
            }
        }
    }

    /**
     * Function that checks if the string is an add task command.
     * @param text String containing user input.
     * @return true if string starts with "todo", "deadline" or "event" and false otherwise.
     */
    public static boolean isAddCommand(String text) {
        return text.startsWith("todo") || text.startsWith("deadline") || text.startsWith("event");
    }

    /**
     * Function that waits for user input, then executes user command.
     * "list" will list all current tasks.
     * "done x" will mark task x as done, where x is the number of the task.
     * "todo", "deadline" or "event" will attempt to add the task.
     * Any other string will print an error message.
     * @param tasks Arraylist containing all recorded tasks.
     * @param text String containing user input.
     * @throws InvalidCommandException thrown when command is invalid.
     */
    public static void getCommand(ArrayList<Task> tasks, String text) throws InvalidCommandException {
        text = text.trim();
        if (text.equals("list")) {
            printTasks(tasks);
        } else if (text.startsWith("done")) {
            doTask(tasks, text);
        } else if (text.startsWith("delete")) {
            deleteTask(tasks, text);
        } else if (isAddCommand(text)) {
            addTask(tasks, text);
        } else {
            throw new InvalidCommandException();
        }
    }

    public static void deleteTask(ArrayList<Task> tasks, String text) {
        String str = text.substring(text.indexOf("delete") + 6).trim();
        try {
            int index = Integer.parseInt(str) - 1;
            System.out.println(line + "> You've removed: " + "\n\t"
                    + (index + 1) + ". " + tasks.get(index).toString());
            tasks.remove(index);
            Task.count -= 1;
            printTaskCount();
            System.out.print(line);
        } catch (NumberFormatException e) {
            System.out.print(line + "> Please key in a number.\n" + line);
        } catch (IndexOutOfBoundsException e) {
            System.out.print(line + "> That task does not exist.\n" + line);
        }
    }

    /**
     * This function attempts to add the task specified by the user to the list and catches exceptions if the input
     * is invalid, printing error messages.
     * @param tasks Arraylist containing all recorded tasks.
     * @param text String containing user input.
     */
    public static void addTask(ArrayList<Task> tasks, String text) {
        if (text.startsWith("todo")) {
            addTodo(tasks, text);
        } else if (text.startsWith("deadline")) {
            try {
                addDeadline(tasks, text);
            } catch (InvalidDeadlineException e) {
                System.out.print(line + "Please follow the format [NAME] /by [DEADLINE]. " +
                        "Thank you!\n" + line);
            }
        } else {
            try {
                addEvent(tasks, text);
            } catch (InvalidEventException e) {
                System.out.print(line + "Please follow the format [NAME] /at [DURATION]. " +
                        "Thank you!\n" + line);
            }
        }
    }

    /**
     * This function adds the todo specified by the user to the list.
     * @param tasks Arraylist containing all recorded tasks.
     * @param text String containing user input.
     */
    public static void addTodo(ArrayList<Task> tasks, String text) {
        String str = text.substring(text.indexOf("todo") + 4).trim();
        tasks.add(new Todo(str));
        Task.count += 1;
        System.out.println(line + "> Added: " + "\n\t"
                + Task.count + ". " + tasks.get(Task.count - 1).toString());
        printTaskCount();
        System.out.print(line);
    }

    /**
     * This function adds the deadline specified by the user to the list.
     * @param tasks Arraylist containing all recorded tasks.
     * @param text String containing user input.
     * @throws InvalidDeadlineException is thrown when command syntax is not followed.
     */
    public static void addDeadline(ArrayList<Task> tasks, String text) throws InvalidDeadlineException  {
        if (!text.contains("/by")) {
            throw new InvalidDeadlineException();
        }
        String str = text.substring(text.indexOf("deadline") + 8, text.indexOf("/")).trim();
        String by = text.substring(text.indexOf("/by") + 3).trim();
        tasks.add(new Deadline(str, by));
        Task.count += 1;
        System.out.println(line + "> Added: " + "\n\t"
                + Task.count + ". " + tasks.get(Task.count - 1).toString());
        printTaskCount();
        System.out.print(line);
    }

    /**
     * This function adds the event specified by the user to the list.
     * @param tasks Arraylist containing all recorded tasks.
     * @param text String containing user input.
     * @throws InvalidEventException is thrown when command syntax is not followed.
     */
    public static void addEvent(ArrayList<Task> tasks, String text) throws InvalidEventException {
        if (!text.contains("/at")) {
            throw new InvalidEventException();
        }
        String str = text.substring(text.indexOf("event") + 5, text.indexOf("/")).trim();
        String at = text.substring(text.indexOf("/at") + 3).trim();
        tasks.add(new Event(str, at));
        Task.count += 1;
        System.out.println(line + "> Added: " + "\n\t"
                + Task.count + ". " + tasks.get(Task.count - 1).toString());
        printTaskCount();
        System.out.print(line);
    }

    /**
     * Function that attempts to mark a task done by calling markAsDone. It prints error messages if any exceptions
     * are caught from both parseInt and markAsDone.
     * If the String given is not a number or is out of bounds, it will catch the exception and print an error message.
     * @param tasks Arraylist containing all recorded tasks.
     * @param text String that is supposed to be the number of the task.
     */
    public static void doTask(ArrayList<Task> tasks, String text) {
        String str = text.substring(text.indexOf("done") + 4).trim();
        try {
            int index = Integer.parseInt(str);
            markAsDone(tasks, index - 1);
        } catch (NumberFormatException e) {
            System.out.print(line + "> Please key in a number.\n" + line);
        } catch (TaskNegativeException e) {
            System.out.print(line + "> ...Stop trying to break me...\n" + line);
        } catch (TaskNotFoundException e) {
            System.out.print(line + "> Oops! That task does not exist yet!\n" + line);
        }
    }

    /**
     * Function throws exceptions if the index of the task is invalid and marks task as done if it is valid.
     * @param tasks Arraylist containing all recorded tasks.
     * @param index Index of the task to be marked as done.
     * @throws TaskNegativeException If index is negative.
     * @throws TaskNotFoundException If index is of a task that has not been created yet.
     */
    public static void markAsDone(ArrayList<Task> tasks, int index) throws TaskNegativeException, TaskNotFoundException {
        if (index < 0) {
            throw new TaskNegativeException();
        } else if (index >= Task.count) {
            throw new TaskNotFoundException();
        }
        tasks.get(index).markAsDone();
        System.out.println(line + "> You've done: " + "\n\t"
                + (index + 1) + ". " + tasks.get(index).toString());
        System.out.print(line);
    }

    /**
     * Function to print all tasks in tasks.
     * @param tasks Arraylist containing all recorded tasks.
     */
    public static void printTasks(ArrayList<Task> tasks) {
        System.out.println(line + "> Here is your list of tasks: ") ;
        for (int i = 0; i < Task.count; i++) {
            System.out.println("\t" + (i + 1) + ". " + tasks.get(i).toString());
        }
        printTaskCount();
        System.out.print(line);
    }

    public static void printTaskCount() {
        String taskForm = (Task.count == 1) ? "task" : "tasks";
        System.out.println("> You have " + Task.count + " " + taskForm + " on your list. -w-") ;
    }
}


