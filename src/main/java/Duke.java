import java.util.Scanner;

public class Duke {

    private static Task[] tasks = new Task[100];
    private static int tasksCount = 0;

    public static void main(String[] args) {
        greetUser();
        executeResponse();
    }

    // Template code to check if IDE is working
    public static void printDukeLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    // Prints a separator line
    public static void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    // Greeting message
    public static void greetUser() {
        printLine();
        System.out.println("     Hello! I'm Duke\n     What can I do for you?");
        printLine();
    }

    // Exit message
    public static void exitDuke() {
        printLine();
        System.out.println("     Bye. Hope to see you again soon!");
        printLine();
    }

    // Add items to list
    public static void addToList(String item) {
        tasks[tasksCount] = new Task(item);
        tasksCount++;
        printLine();
        System.out.println("     added: " + item);
        printLine();
    }

    // Mark list item as completed
    public static void markAsCompleted(int itemNumber) {
        printLine();
        if (itemNumber > tasksCount) {
            System.out.println("     Invalid task selected.");
        } else {
            tasks[itemNumber - 1].markTaskAsDone();
            System.out.println("     Nice! I've marked this task as done:\n       [X] " + tasks[itemNumber - 1].getDescription());
        }
        printLine();
    }

    // Print out items in list
    public static void printList() {
        printLine();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < tasksCount; i++) {
            System.out.println("     " + (i + 1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
        }
        printLine();
    }

    // Executes an appropriate response based on the input message
    public static void executeResponse() {
        String line;
        Scanner in = new Scanner(System.in);
        do {
            System.out.println();
            line = in.nextLine();
            if (line.equals("bye")) {
                exitDuke();
            } else if (line.equals("list")) {
                printList();
            } else if (line.contains("done")) {
                markAsCompleted(Integer.parseInt(line.substring(line.indexOf(" ") + 1)));
            } else {
                addToList(line);
            }
        } while (!line.equals("bye"));
    }

}
