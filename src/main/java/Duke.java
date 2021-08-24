import java.util.Scanner;
public class Duke {

    private static final String line = "____________________________________________________________";
    private static Task[] tasks = new Task[100];

    public static void main(String[] args) {
        greetUser();
        handleCommands();
    }

    /**
     * Prints a greeting to standard output
     */
    public static void greetUser() {
        printString("Hello! I'm Duke\n" +
                " What can I do for you?");
    }

    /**
     * Reads commands to Duke from standard input and passes
     * them to separate functions to handle them
     */
    public static void handleCommands() {
        Scanner in = new Scanner(System.in);
        String command = "";

        while (!command.equals("bye")) {
            command = in.nextLine();
            if (command.equals("list")) {
                listTasks();
            } else if (command.startsWith("done")) {
                doneTask(command);
            } else if (command.equals("bye")) {
                exitChatbot();
            } else {
                addTask(command);
            }
        }
    }

    // helper functions used in command handlers

    /**
     * Prints Duke logo to the standard output with a greeting
     */
    public static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Formats and prints strings to standard output
     *
     * @param response Duke's response to be printed out
     */
    public static void printString(String response) {
        System.out.println(line);
        System.out.println(" " + response);
        System.out.println(line);
    }

    // all command handlers

    /**
     * Prints goodbye to output
     */
    public static void exitChatbot() {
        printString("Bye. Hope to see you again soon!");
    }

    /**
     * Adds tasks to the array of tasks stored by Duke
     *
     * @param command name of the task to be added
     */
    public static void addTask(String command) {
        tasks[Task.taskCount] = new Task(command);
        printString("added: " + command);
    }

    /**
     * Lists out all tasks stored by Duke, printing to standard output
     * Tasks marked as completed will have an [X] beside their names
     */
    public static void listTasks() {
        System.out.println(line);
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < Task.taskCount; i++) {
            System.out.println(" " + (i + 1) +
                    ".[" + tasks[i].getStatusIcon() +
                    "] " + tasks[i].getDescription());
        }
        System.out.println(line);
    }

    /**
     * Marks task n from the stored array of tasks as done when Duke
     * receives the command "done n" and prints an acknowledgement
     * to the standard output
     *
     * @param command the "done n", command, such that the nth command is marked as done
     */
    public static void doneTask(String command) {
        int taskIndex = Integer.parseInt(command.substring(command.indexOf(" ") + 1));
        tasks[taskIndex - 1].setDone();
        printString("Nice! I've marked this task as done:\n" +
                "  [X] " + tasks[taskIndex - 1].getDescription());
    }
}
