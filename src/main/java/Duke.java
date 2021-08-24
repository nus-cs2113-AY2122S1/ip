import java.util.Scanner;
public class Duke {

    private static final String line = "____________________________________________________________";
    private static Task[] tasks = new Task[100];

    public static void main(String[] args) {
        greetUser();
        handleCommands();
    }

    // greet the user and prints a line
    public static void greetUser() {
        printString("Hello! I'm Duke\n" +
                " What can I do for you?");
    }

    // handles all commands by directing them to helper functions
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
    public static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void printString(String response) {
        System.out.println(line);
        System.out.println(" " + response);
        System.out.println(line);
    }

    // all command handlers

    // exits the programme after printing the greeting and a line
    public static void exitChatbot() {
        printString("Bye. Hope to see you again soon!");
    }

    // adds commands to an array of commands
    public static void addTask(String command) {
        tasks[Task.taskCount] = new Task(command);
        printString("added: " + command);
    }

    // lists all tasks added to duke
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

    // marks a task as done
    public static void doneTask(String command) {
        int taskIndex = Integer.parseInt(command.substring(command.indexOf(" ") + 1));
        tasks[taskIndex - 1].setDone();
        printString("Nice! I've marked this task as done:\n" +
                "  [X] " + tasks[taskIndex - 1].getDescription());
    }
}
