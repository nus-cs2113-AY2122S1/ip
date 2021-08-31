import java.util.Scanner;

public class Duke {
    
    public static int taskCounter = 0;
    public static Task[] scheduledTasks = new Task[100];

    public static void main(String[] args) {
        greet();

        runDuke();

        greetBye();
    }

    private static void runDuke() {
        int i;
        String userInput;
        String taskCompletionStatus = "";
        Scanner in = new Scanner(System.in);

        userInput = in.nextLine();

        while (!(userInput.equals("bye"))) {
            if (userInput.equals("list")) {
                List();
            } else if (userInput.startsWith("done")) {
                markTaskAsDone(userInput);
            } else {
                addTaskToList(userInput);
            }
            printLine();
            userInput = in.nextLine();
        }
    }

    private static void greet() {
        printLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printLine();
    }

    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    private static void greetBye() {
        printLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printLine();
    }

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
            System.out.println(scheduledTasks[taskCounter - 1]);
            System.out.println("Now you have " + taskCounter + " tasks in the list.");
        }
    }

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

    private static void List() {
        int i;
        String taskCompletionStatus;
        printLine();
        if (taskCounter == 0) {
            System.out.println("Sorry, no tasks have been added to the list as yet! \n" +
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

