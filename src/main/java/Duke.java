import java.util.Objects;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  /\n"
                + "|____/ \\,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String userCommand;
        Task [] tasks = new Task[100];
        Scanner userType = new Scanner(System.in);
        int taskNum = 0;

        greet();
        do {
            userCommand = userType.nextLine();
            if (userCommand.equals("bye")) {
                break;
            }
            else if (userCommand.equals("list")) {
                printList(tasks, taskNum);
            }
            else if (userCommand.contains("done")) {
                int id = Integer.parseInt(userCommand.substring(5));
                taskDone(tasks[id - 1]);
            }
            else {
                taskNum++;
                Task t = new Task(userCommand, taskNum);
                addToList(t, tasks, taskNum);
            }
        } while (!Objects.equals(userCommand, "bye"));
        exit();
    }

    public static void greet() {
        printSign();
        System.out.println("Hello! I'm Duke\n");
        System.out.println("What can I do for you?\n");
        printSign();
    }

    public static void taskDone(Task args) {
        args.markAsDone();
        printSign();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + "[X] " + args.description);
        printSign();
    }

    public static void addToList(Task t, Task[] list, int taskNum) {
        list[taskNum - 1] = t;
        printSign();
        System.out.println("added: " + t.description);
        printSign();
    }

    public static void echo(String args) {
        if (Objects.equals(args, "bye")) {
            printSign();
            return;
        }
        printSign();
        System.out.println(args);
        printSign();
    }

    public static void printList(Task[] args, int length) {
        printSign();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < length; i++) {
            System.out.print(i + 1 + ".");
            System.out.print("[" + args[i].getStatusIcon() + "]");
            System.out.println(" " + args[i].description);
        }
        printSign();
    }

    public static void exit() {
        printSign();
        System.out.println("Bye. Hope to see you again soon!\n");
        printSign();
    }

    public static void printSign() {
        for (int i = 1; i <= 20; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}
