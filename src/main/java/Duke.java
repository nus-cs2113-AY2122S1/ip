import java.util.Scanner;

public class Duke {

    /**
     * Number of tasks scheduled
     */
    public static int taskCounter = 0;

    /**
     * Task type 100 objects to store the tasks the user will create
     */
    public static Task[] scheduledTasks = new Task[100];

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
            if (userInput.equalsIgnoreCase("list")) {
                list();
            } else if (userInput.startsWith("done")) {
                markTaskAsDone(userInput);
            } else {
                addTaskToList(userInput);
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
     * @param taskDescription TaskDescription Stores the description of the task.
     * @param taskDueBy       TaskDueBy stores the deadline of the task which is created as a deadline.
     * @param taskDueAt       TaskDueAt stores the event timing of the task created as an event.
     * @param isTaskValid     IsTaskValid stores true if the task statement entered by the user is a valid task creation statement and false, otherwise.
     * @param index           Index stores the index of the "/" in the entered String
     */
    private static void addTaskToList(String userInput) {

        String taskDescription;
        String taskDueBy;
        String taskDueAt;
        int index;
        boolean isTaskValid = true;

        if (userInput.startsWith("todo")) {
            scheduledTasks[taskCounter] = new Todo(userInput);
        } else if (userInput.startsWith("deadline")) {
            index = userInput.indexOf("/");
            scheduledTasks[taskCounter] = new Deadline(userInput.substring(0, index), userInput.substring(index + 3));
        } else if (userInput.startsWith("event")) {
            index = userInput.indexOf("/");
            scheduledTasks[taskCounter] = new Event(userInput.substring(0, index), userInput.substring(index + 3));
        } else {
            isTaskValid = false;
            System.out.println("Invalid task creation statement");
        }

        if (isTaskValid) {
            taskCounter++;
            printLine();
            System.out.println("Got it. I've added this task:");
            System.out.println(" " + scheduledTasks[taskCounter - 1]);
            System.out.println("Now you have " + taskCounter + " tasks in the list.");
        }
    }

    /**
     * Updates the status of the task by marking it as done in the task list.
     *
     * @param taskNumberCompleted TaskNumberCompleted stores the task number which has been completed by the user.
     */
    private static void markTaskAsDone(String userInput) {
        printLine();
        int taskNumberCompleted = Integer.parseInt(userInput.substring(userInput.indexOf(" ") + 1));

        if ((taskNumberCompleted <= taskCounter) && (taskNumberCompleted > 0)) {
            scheduledTasks[taskNumberCompleted - 1].markAsDone();
            System.out.println("Nice! I have marked this task as done:");
            System.out.println(scheduledTasks[taskNumberCompleted - 1]);
        } else {
            System.out.println("Sorry, no task is assigned at this number, you might want to re-check?");
        }
    }

    /**
     * Lists all the tasks in the task list along with their task completion status.
     * The tasks are enlisted which reveal if they are a "todo", "event" or a "deadline".
     *
     * @param taskCompletionStatus TaskCompletionStatus stores true if the task is completed, false otherwise.
     */
    private static void list() {
        int i;
        String taskCompletionStatus;
        printLine();
        if (taskCounter == 0) {
            System.out.println("Sorry, no tasks have been added to the list as yet!\n" +
                    "You can add tasks to this list simply by typing and pressing \"Enter\"!!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (i = 0; i < taskCounter; i++) {
                taskCompletionStatus = scheduledTasks[i].getStatus();
                System.out.print((i + 1) + ".");
                System.out.println(scheduledTasks[i]);
            }
        }
    }
}

