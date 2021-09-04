import java.util.Scanner;

public class Duke {
    private static final int LENGTH_OF_BY = 4;
    private static final int LENGTH_OF_AT = 4;
    private static final int LENGTH_OF_EVENT = 6;
    private static final int LENGTH_OF_DEADLINE = 9;
    private static final int LENGTH_OF_TODO = 5;
    private static final String WAVING_EMOJI = "\uD83D\uDC4B";
    private static final String THUMBS_UP_EMOJI = "\uD83D\uDC4D";
    private static final String SMILEY_EMOJI = "\uD83D\uDE04";


    public static void main(String[] args) {
        String rawInput;
        Scanner scanner = new Scanner(System.in);
        TasksList tasks = new TasksList();

        printWelcomeBanner();

        rawInput = scanner.nextLine();
        while (true) {
            if (rawInput.equals("bye")) {  // Exit programme
                System.out.println("\tBye. Hope to see you again soon! " + WAVING_EMOJI);
                printHorizontalLine();
                break;
            } else if (rawInput.startsWith("done ")) {  // Mark task as done
                System.out.println("\tNice! " + THUMBS_UP_EMOJI + " I've marked this task as done:");
                int taskIndex = Integer.parseInt(rawInput.substring(5)) - 1;
                Task task = tasks.markTaskAsDone(taskIndex);
                System.out.println("\t\t[" + task.getStatusIcon() + "] " + task.getDescription());
            } else if (rawInput.equals("list")) {  // Print all tasks
                System.out.println("\tHere are the tasks in your list:");
                tasks.printTasks();
            } else {  // Add new task to tasks
                boolean isValidInput = true;  //  Remains true if raw_input is a ToDo, Event, or Deadline

                // Add new task to tasks
                if (rawInput.startsWith("todo ")) {  // Add new ToDo task
                    String description = rawInput.substring(LENGTH_OF_TODO);
                    tasks.addTask("todo", description, "");
                } else if (rawInput.startsWith("event ")) {  // Add new Event task
                    int idx = rawInput.indexOf("/at");
                    String description = rawInput.substring(LENGTH_OF_EVENT, idx-1);
                    String at = rawInput.substring(idx+LENGTH_OF_AT);
                    tasks.addTask("event", description, at);
                } else if (rawInput.startsWith("deadline ")) {  // Add new Deadline task
                    int idx = rawInput.indexOf("/by");
                    String description = rawInput.substring(LENGTH_OF_DEADLINE, idx - 1);
                    String by = rawInput.substring(idx+LENGTH_OF_BY);
                    tasks.addTask("deadline", description, by);
                } else {  // Bad input format
                    isValidInput = false;
                }

                // Print to console to indicate outcome of task addition
                if (isValidInput) {  // Successful task addition
                    System.out.println("\tGot it. I've added this task:");
                    System.out.println("\t\t" + tasks.getTaskString(tasks.getSize() - 1));
                    System.out.println("\tNow you have " + tasks.getSize() + " tasks in the list.");
                } else {  // Failed task addition: Bad input format
                    System.out.println("\t\tERROR, INVALID INPUT!");
                }
            }
            printHorizontalLine();
            rawInput = scanner.nextLine();
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
        System.out.println("\tHello! I'm Duke " + SMILEY_EMOJI);
        System.out.println("\tWhat can I do for you?");
        printHorizontalLine();
    }
}
