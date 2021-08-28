import java.util.Scanner;

public class Duke {
    private static final int LENGTH_OF_BY = 4;
    private static final int LENGTH_OF_AT = 4;
    private static final int LENGTH_OF_EVENT = 6;
    private static final int LENGTH_OF_DEADLINE = 9;
    private static final int LENGTH_OF_TODO = 5;

    public static void main(String[] args) {
        String raw_input;
        Scanner scanner = new Scanner(System.in);
        TasksList tasks = new TasksList();

        printWelcomeBanner();

        raw_input = scanner.nextLine();  // Get input
        while (true) {
            if (raw_input.equals("bye")) {  // Exit programme
                System.out.println("\tBye. Hope to see you again soon! \uD83D\uDC4B");  // Unicode is a waving emoji
                printHorizontalLine();
                break;
            } else if (raw_input.startsWith("done ")) {  // Mark task as done
                System.out.println("\tNice! \uD83D\uDC4D I've marked this task as done:");  // Unicode is a thumbs up emoji
                int taskIndex = Integer.parseInt(raw_input.substring(5))-1;
                Task task = tasks.markTaskAsDone(taskIndex);
                System.out.println("\t\t[" + task.getStatusIcon() + "] " + task.getDescription());
            } else if (raw_input.equals("list")) {  // Print all tasks
                System.out.println("\tHere are the tasks in your list:");
                tasks.printTasks();
            } else {  // Add new task to tasks
                boolean validInput = true;  //  Remains true if raw_input is a ToDo, Event, or Deadline

                // Add new task to tasks
                if (raw_input.startsWith("todo ")) {  // Add new ToDo task
                    String description = raw_input.substring(LENGTH_OF_TODO);
                    tasks.addTask("todo", description, "");
                } else if (raw_input.startsWith("event ")) {  // Add new Event task
                    int idx = raw_input.indexOf("/at");
                    String description = raw_input.substring(LENGTH_OF_EVENT, idx-1);
                    String at = raw_input.substring(idx+LENGTH_OF_AT);
                    tasks.addTask("event", description, at);
                } else if (raw_input.startsWith("deadline ")) {  // Add new Deadline task
                    int idx = raw_input.indexOf("/by");
                    String description = raw_input.substring(LENGTH_OF_DEADLINE, idx-1);
                    String by = raw_input.substring(idx+LENGTH_OF_BY);
                    tasks.addTask("deadline", description, by);
                } else {  // Bad input format
                    validInput = false;
                }

                // Print to console to indicate outcome of task addition
                if (validInput) {  // Successful task addition
                    System.out.println("\tGot it. I've added this task:");
                    System.out.println("\t\t" + tasks.getTaskString(tasks.getSize()-1));
                    System.out.println("\tNow you have " + tasks.getSize() + " tasks in the list.");
                } else {  // Failed task addition: Bad input format
                    System.out.println("\t\tERROR, INVALID INPUT!");
                }
            }
            printHorizontalLine();
            raw_input = scanner.nextLine();
        }
    }

    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }
    public static void printWelcomeBanner() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        printHorizontalLine();
        System.out.println("Hello from\n" + logo);
        System.out.println("\tHello! I'm Duke \uD83D\uDE04");  // Unicode is a smiley face emoji
        System.out.println("\tWhat can I do for you?");
        printHorizontalLine();
    }
}
