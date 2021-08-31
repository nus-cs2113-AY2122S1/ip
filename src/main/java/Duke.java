import java.util.Scanner;

public class Duke {
    public static void printGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("type in your command please");
        System.out.println("____________________________________________________________");
    }

    public static void printBye() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void printEcho(String command) {
        System.out.println("____________________________________________________________");
        System.out.println(command.substring(5));
        System.out.println("____________________________________________________________");
    }

    public static void printToDo(Task task, int size) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task: \n" + "\t" + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public static void printDeadline(Task task, int size) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task: \n" + "\t" + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public static void printEvent(Task task, int size) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task: \n" + "\t" + task.toString());
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public static void printList(Task[] tasks, int size) {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < size; i++) {
            System.out.println(tasks[i].getStringNo() + "." + tasks[i].toString());
        }
        System.out.println("____________________________________________________________");
    }

    public static void setTaskDone(Task task) {
        task.markAsDone();
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("\t" + task.toString());
        System.out.println("____________________________________________________________");
    }

    public static void printDone(Task[] tasks, int size, String command) {
        String description = command.substring(5);
        for (int i = 0; i < size; i++) {
            if (description.equals(tasks[i].getDescription())){
                setTaskDone(tasks[i]);
                break;
            }
        }
    }

    public static void main(String[] args) {
        printGreeting();
        int size = 0;
        Task[] tasks = new Task[100];
        Scanner in = new Scanner(System.in);
        while (true) {
            String command = in.nextLine();
            if (command.equals("bye")) {
                printBye();
                break;
            } else if (command.startsWith("echo ")){
                printEcho(command);
            } else if (command.startsWith("todo ")) {
                size++;
                tasks[size-1] = new ToDo(command.substring(5), size);
                printToDo(tasks[size-1], size);
            }  else if (command.startsWith("deadline ")) {
                size++;
                int slashIndex = command.indexOf("/");
                String description = command.substring(9, slashIndex-1);
                String by = command.substring(slashIndex+4);
                tasks[size-1] = new Deadline(description, size, by);
                printDeadline(tasks[size-1], size);
            } else if (command.startsWith("event ")) {
                size++;
                int slashIndex = command.indexOf("/");
                String description = command.substring(6, slashIndex-1);
                String at = command.substring(slashIndex+4);
                tasks[size - 1] = new Event(description, size, at);
                printEvent(tasks[size-1], size);
            } else if (command.equals("list")) {
                printList(tasks, size);
            } else if (command.startsWith("done ")) {
                printDone(tasks, size, command);
            }
        }
    }
}

