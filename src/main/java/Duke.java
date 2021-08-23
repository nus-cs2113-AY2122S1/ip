import java.util.Scanner;

public class Duke {
    private static final String LOGO =
            "      ____        _        \n"     +
            "     |  _ \\ _   _| | _____ \n"    +
            "     | | | | | | | |/ / _ \\\n"    +
            "     | |_| | |_| |   <  __/\n"     +
            "     |____/ \\__,_|_|\\_\\___|\n";

    private static final String LINE =
            "    ____________________________________________________________" + "\n";

    private static final String PADDING = "     ";

    // Duke greeting
    private static void greet() {
        System.out.print(LINE);
        System.out.println(LOGO);
        System.out.println(PADDING + "Hello! I'm Duke");
        System.out.println(PADDING + "What can I do for you?");
        System.out.println(LINE);
    }

    // Duke exits
    private static void exit() {
        System.out.print(LINE);
        System.out.println(PADDING + "Bye. Hope to see you again soon!");
        System.out.print(LINE);
    }

    // An array of tasks (no more than 100)
    private static Task[] tasks = new Task[100];
    private static int taskNumber = 0;

    // Add user command to the tasks array
    private static void add(String command) {
        tasks[taskNumber] = new Task(command);
        taskNumber++;
        System.out.print(LINE);
        System.out.println(PADDING + "added: " + command);
        System.out.println(LINE);
    }

    // List all the tasks
    private static void list() {
        System.out.print(LINE);
        for (int i = 1; i <= taskNumber; i++) {
            System.out.println(PADDING + i + "." + tasks[i - 1]);
        }
        System.out.println(LINE);
    }

    // Mark a task as done and display it
    private static void done(int number) {
        if (number <= taskNumber) {
            tasks[number - 1].markAsDone();
            System.out.print(LINE);
            System.out.println(PADDING + "Nice! I've marked this task as done:");
            System.out.println(PADDING + "  " + tasks[number - 1]);
            System.out.println(LINE);
        } else {
            System.out.println(LINE);
            System.out.println(LINE);
        }
    }

    // Duke main program
    public static void main(String[] args) {
        greet();
        while (true) {
            Scanner sc = new Scanner(System.in);
            String command = sc.nextLine();
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")) {
                list();
            } else if (command.split(" ")[0].equals("done")) {
                done(Integer.parseInt(command.split(" ")[1]));
            } else {
                add(command);
            }
        }
        exit();
    }
}
