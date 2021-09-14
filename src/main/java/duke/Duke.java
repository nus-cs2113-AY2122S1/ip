package duke;

import exception.DukeException;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    /**
     * Number of tasks scheduled
     */
    public static int taskCounter = 0;

    /**
     * Task type 100 objects to store the tasks the user will create
     */

    private static ArrayList<Task> scheduledTasks = new ArrayList<>();

    /**
     * This is the main function which is responsible for executing all the functions
     */
    public static void main(String[] args) {
        greet();
        runDuke();
        greetBye();
    }

    /**
     * Greets the user by printing some introductory messages
     */
    private static void greet() {
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
    }

    /**
     * Prints a line on the screen
     */
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Takes in input from the user and executes the given instructions.
     * If user input is "list", it calls the list() function to list all the tasks.
     * If user input is done x, where x is a valid task number, it calls the MarkTaskAsDone() function to mark the task as done.
     * If user has scheduled a task by writing a statement beginning with "event", "deadline" or "todo",
     * then it adds the task in the tasks list by calling the addTaskToList() function.
     *
     * @param userInput            userInput stores the input String entered by the user.
     * @param taskCompletionStatus taskCompletionStatus Stores the status of the task, true if completed, false otherwise.
     */
    private static void runDuke() {
        int i;
        String userInput;
        String taskCompletionStatus = "";
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();

        while (!(userInput.equalsIgnoreCase("bye"))) {
            try {
                if (userInput.equalsIgnoreCase("list")) {
                    list();
                } else if (userInput.startsWith("done")) {
                    markTaskAsDone(userInput);
                } else if (userInput.startsWith("delete")) {
                    deleteTask(userInput);
                } else {
                    addTaskToList(userInput);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            printLine();
            userInput = in.nextLine();
        }

    }

    /**
     * Greets the user goodbye and the code finishes its execution.
     */
    private static void greetBye() {
        printLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printLine();
    }

    /**
     * Adds the task to the task list if it is a valid task creation statement given by the user.
     * Displays appropriate message if task is valid and is created successfully, vice versa.
     * Displays the total number of tasks in the list as well.
     *
     * @param isTaskValid IsTaskValid stores true if the task statement entered by the user is a valid task creation statement and false, otherwise.
     * @param index       Index stores the index of the "/" in the entered String
     */
    private static void addTaskToList(String userInput) throws DukeException {

        int index;
        boolean isTaskValid = true;
        String firstWord;
        String[] split = userInput.split(" ", 2);
        String taskDescription;
        String timeDueAt;
        String timeDueBy;
        firstWord = split[0].toLowerCase();

        switch (firstWord) {
        case "todo":
            if (split.length < 2 || split[1].isEmpty() == true) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            } else {
                scheduledTasks.add(new Todo(userInput));
            }
            break;

        case "deadline":
            index = userInput.indexOf("/");
            if (split.length < 2 || split[1].isEmpty() == true || index == -1) {
                throw new DukeException("☹ OOPS!!! The description or the deadline of the task cannot be empty.");
            }
            taskDescription = split[1].split("/by", 2)[0];
            timeDueBy = split[1].split("/by", 2)[1];
            if (taskDescription.isEmpty() == true || timeDueBy.isEmpty() == true) {
                throw new DukeException("☹ OOPS!!! The description of the task seems incomplete.");
            }
            scheduledTasks.add(new Deadline(userInput.substring(0, index), userInput.substring(index + 3)));
            break;

        case "event":
            index = userInput.indexOf("/");
            if (split.length < 2 || split[1].isEmpty() == true || index == -1) {
                throw new DukeException("☹ OOPS!!! The description and time schedule of the event cannot be empty.");
            }
            taskDescription = split[1].split("/at", 2)[0];
            timeDueAt = split[1].split("/at", 2)[1];
            if (taskDescription.isEmpty() == true || timeDueAt.isEmpty() == true) {
                throw new DukeException("☹ OOPS!!! The description or time schedule of the event seems incomplete.");
            }
            scheduledTasks.add(new Event(userInput.substring(0, index), userInput.substring(index + 3)));
            break;

        default:
            isTaskValid = false;
            throw new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        if (isTaskValid) {
            printLine();
            System.out.println("Got it. I've added this task:");
            System.out.println(" " + scheduledTasks.get(scheduledTasks.size() - 1));
            taskCounter++;
            System.out.println("Now you have " + scheduledTasks.size() + " tasks in the list.");
        }
    }


    /**
     * Updates the status of the task by marking it as done in the task list.
     *
     * @param taskNumberCompleted TaskNumberCompleted stores the task number which has been completed by the user.
     */
    private static void markTaskAsDone(String userInput) throws DukeException {
        printLine();
        int taskNumberCompleted = Integer.parseInt(userInput.substring(userInput.indexOf(" ") + 1));

        if ((taskNumberCompleted <= scheduledTasks.size()) && (taskNumberCompleted > 0)) {
            scheduledTasks.get(taskNumberCompleted - 1).markAsDone();
            System.out.println("Nice! I have marked this task as done:");
            System.out.println(scheduledTasks.get(taskNumberCompleted - 1));
        } else {
            throw new DukeException("Sorry, no task is assigned at this number, you might want to re-check?");
        }
    }


    /**
     * Deletes the task from the task list.
     *
     * @param deleteTask DeleteTask stores the task number which is supposed to be deleted.
     */
    private static void deleteTask(String userInput) throws DukeException {
        printLine();
        int deleteTask = Integer.parseInt(userInput.substring(userInput.indexOf(" ") + 1));

        if ((deleteTask <= scheduledTasks.size()) && (deleteTask > 0)) {
            Task taskToBeDeleted = scheduledTasks.get(deleteTask - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(taskToBeDeleted);
            scheduledTasks.remove(deleteTask - 1);
            System.out.println("Now you have " + scheduledTasks.size() + " tasks in the list.");
        } else {
            throw new DukeException("Sorry, no task is assigned at this number, you might want to re-check?");
        }
    }


    /**
     * Lists all the tasks in the task list along with their task completion status.
     * The tasks are enlisted which reveal if they are a "todo", "event" or a "deadline".
     *
     * @param taskCompletionStatus TaskCompletionStatus stores true if the task is completed, false otherwise.
     */
    private static void list() throws DukeException {
        int i;
        String taskCompletionStatus;
        printLine();
        if (scheduledTasks.size() == 0) {
            throw new DukeException("Sorry, no tasks have been added to the list as yet!\n" +
                    "You can add tasks to this list simply by typing and pressing \"Enter\"!!");
        } else {
            System.out.println("Here are the tasks in your list:");
            i = 0;
            for (Task task : scheduledTasks) {
                taskCompletionStatus = task.getStatus();
                System.out.print((i + 1) + ".");
                System.out.println(task);
                i++;
            }
        }
    }
}

