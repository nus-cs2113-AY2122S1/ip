import java.util.Scanner;

public class Duke {

    public static final int END_OF_DONE_INDEX = 4;
    public static final int END_OF_EVENT_INDEX = 6;
    public static final int END_OF_DEADLINE_INDEX = 9;
    public static final int END_OF_TODO_INDEX = 5;
    public static String Logo = " _____         _____\n"
            + "|     \\ _____ |     \\ _____\n"
            + "|  o  /|     ||  o  /|     |\n"
            + "|  o  \\|  o  ||  o  \\|  o  |\n"
            + "|_____/|_____||_____/|_____|\n";
    public static String horizontalLine = "____________________________________________________________";
    public static boolean isRunning = true;
    public static Task[] tasks = new Task[100];
    public static int numberOfTasks = 0;

    public static void main(String[] args) {

        String line;
        Scanner in = new Scanner(System.in);

        sayHello();
        while (isRunning) {
            line = in.nextLine();

            if (line.equals("bye")) {
                exitProgram();
            } else if(line.equals("list")) {
                printList();
            } else if (line.startsWith("done")) {
                handleDone(line);
            } else if (line.startsWith("todo")) {
                addTodoTask(line);
                printTaskConfirmation();
            } else if (line.startsWith("deadline")) {
                addDeadlineTask(line);
                printTaskConfirmation();
            } else if (line.startsWith("event")) {
                addEventTask(line);
                printTaskConfirmation();
            } else {
                sayInvalid();
            }
        }
        sayBye();
    }

    private static void sayHello() {
        System.out.println(Logo);
        System.out.println(horizontalLine);
        System.out.println("Hello! I'm Bobo!");
        System.out.println("I'm a little blur, but I'd love to help!");
        System.out.println(horizontalLine);
    }

    private static void sayBye() {
        System.out.println(horizontalLine);
        System.out.println("Ok. Bye bye!");
        System.out.println(horizontalLine);
    }

    private static void sayInvalid() {
        System.out.println(horizontalLine);
        System.out.println("Sorry I don't understand that! Can you rephrase?");
        System.out.println(horizontalLine);
    }

    private static void printList() {
        System.out.println(horizontalLine);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < numberOfTasks; i++) {
            System.out.println((i + 1) + "." + tasks[i]);
        }
        System.out.println(horizontalLine);
    }

    private static void printDone(int taskNumber) {
        System.out.println(horizontalLine);
        System.out.println("Okie! Marked this as done:");
        System.out.println((taskNumber + 1) + "." + tasks[taskNumber]);
        System.out.println(horizontalLine);
    }

    private static void handleDone(String line) {
        int taskNumber = Integer.parseInt(line.substring(END_OF_DONE_INDEX).trim()) - 1;
        tasks[taskNumber].markAsDone();
        printDone(taskNumber);
    }

    private static void printTaskConfirmation() {
        System.out.println(horizontalLine);
        System.out.println("Umm ok added:");
        System.out.println("  " + tasks[numberOfTasks - 1]);
        System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
        System.out.println(horizontalLine);
    }

    private static void addEventTask(String line) {
        int endOfDescriptionIndex = line.indexOf("/at") - 1;
        int startOfAtIndex = line.indexOf("/at") + 4;
        String description = line.substring(END_OF_EVENT_INDEX, endOfDescriptionIndex);
        String at = line.substring(startOfAtIndex);

        tasks[numberOfTasks] = new Event(description, at);
        numberOfTasks++;
    }

    private static void addDeadlineTask(String line) {
        int endOfDescriptionIndex = line.indexOf("/by") - 1;
        int startOfByIndex = line.indexOf("/by") + 4;
        String description = line.substring(END_OF_DEADLINE_INDEX, endOfDescriptionIndex);
        String by = line.substring(startOfByIndex);

        tasks[numberOfTasks] = new Deadline(description, by);
        numberOfTasks++;
    }

    private static void addTodoTask(String line) {
        String description = line.substring(END_OF_TODO_INDEX);
        tasks[numberOfTasks] = new Todo(description);
        numberOfTasks++;
    }

    private static void exitProgram() {
        isRunning = false;
    }
}
