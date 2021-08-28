import java.util.Scanner;
import static java.lang.Integer.parseInt;

public class Duke {
    public static void main(String[] args) {
        final String LINE = "____________________________________________________________\n";
        printHelloMessage(LINE);
        Scanner input = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int index = 0;
        processUserInput(LINE, input, tasks, index);
    }

    /**
     * Processes user input after the app starts
     * @param LINE String constant to display line
     * @param input User input
     * @param tasks Task list represented by an array of Tasks
     * @param index Index number of newest Task added into tasks array
     */
    private static void processUserInput(String LINE, Scanner input, Task[] tasks, int index) {
        String command = "";
        while (!(command.equalsIgnoreCase("bye"))) {
            command = input.nextLine();
            if ((command.equalsIgnoreCase("bye"))) {
                break;
            }
            if (command.equalsIgnoreCase("list")) {
                System.out.println(LINE);
                printList(tasks, index);
                System.out.println(LINE);
            } else if ((command.toLowerCase()).contains("done")) {
                String[] extractedCommand = command.split(" ");
                int itemNo = parseInt(extractedCommand[1]);
                tasks[itemNo - 1].markAsDone();
                printTaskDone(LINE, tasks[itemNo - 1]);
            } else if ((command.toLowerCase()).contains("todo")) {
                //4 is index after todo in input string
                String toDoTask = command.substring(4);
                tasks[index] = new ToDos(toDoTask);
                printAddTaskMessage(LINE, index, tasks[index]);
                index++;
            } else if ((command.toLowerCase()).contains("deadline")) {
                if (command.contains("/by")) {
                    int endOfDescriptionIndex = command.indexOf("/");
                    //7 is index after "deadline" in input string
                    String deadlineTask = command.substring(7, endOfDescriptionIndex);
                    //3 is no of chars after 'by'
                    String by = command.substring(endOfDescriptionIndex + 3);
                    tasks[index] = new Deadline(deadlineTask, by);
                    printAddTaskMessage(LINE, index, tasks[index]);
                    index++;
                } else {
                    System.out.println("Please include deadline: /by deadline");
                }
            } else if ((command.toLowerCase()).contains("event")) {
                if (command.contains("/at")) {
                    int endOfDescriptionIndex = command.indexOf("/");
                    //5 is index after "event" in input string
                    String eventTask = command.substring(5, endOfDescriptionIndex);
                    //3 is no of chars after "at"
                    String at = command.substring(endOfDescriptionIndex + 3);
                    tasks[index] = new Events(eventTask, at);
                    printAddTaskMessage(LINE, index, tasks[index]);
                    index++;
                } else {
                    System.out.println("Please include event time! /at event time");
                }
            } else {
                tasks[index] = new Task(command);
                printAddTaskMessage(LINE, index, tasks[index]);
                index++;
            }
        }
        printByeMessage(LINE);
    }

    /**
     * Prints a short message with the task added when a task is added by the user
     * @param LINE String constant to display line
     * @param index Index number of current task in tasklist array
     * @param task Task added by user
     */
    private static void printAddTaskMessage(String LINE, int index, Task task) {
        System.out.println(LINE);
        System.out.println("Got it. I've added this task: ");
        System.out.println(task);
        System.out.println("Now you have " + (index + 1) + " tasks in the list.");
        System.out.println(LINE);
    }

    /**
     * Prints Goodbye message before app exits
     * @param line String constant to display line
     */
    private static void printByeMessage(String line) {
        String byeGreeting = "Bye. Hope to see you again soon!\n";
        System.out.println(line + byeGreeting + line);
    }

    /**
     * Prints the welcome message when app is launched
     * @param line String constant to display line
     */
    private static void printHelloMessage(String line) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String helloGreeting = "Hello! I'm Duke \n" + "What can I do for you?\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(line + helloGreeting + line);
    }

    /**
     * Displays a message after a task is done
     * @param line String constant to display line
     * @param x Task that was done
     */
    private static void printTaskDone(String line, Task x) {
        System.out.println(line);
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(x);
        System.out.println(line);
    }

    /**
     * Prints the task list
     *
     * @param list Task list represented in array.
     * @param index Index number of last task in list array.
     */
    public static void printList(Task[] list, int index) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < index; i++) {
            String taskStatus = list[i].getStatusIcon();
            System.out.println((i + 1) + "." + list[i]);
        }
    }

}
