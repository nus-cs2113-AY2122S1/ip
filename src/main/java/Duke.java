import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        int size = 0;
        Task[] tasks = new Task[100];
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
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();
        while (true) {
            if (command.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (command.startsWith("echo ")){
                System.out.println("____________________________________________________________");
                System.out.println(command.substring(5));
                System.out.println("____________________________________________________________");
                command = in.nextLine();
            } else if (command.startsWith("todo ")) {
                size++;
                tasks[size-1] = new ToDo(command.substring(5), size);
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task: \n" + "\t" + tasks[size-1].toString());
                System.out.println("Now you have " + size + " tasks in the list.");
                System.out.println("____________________________________________________________");
                command = in.nextLine();
            }  else if (command.startsWith("deadline ")) {
                size++;
                int slashIndex = command.indexOf("/");
                String description = command.substring(9, slashIndex-1);
                String by = command.substring(slashIndex+4);
                tasks[size-1] = new Deadline(description, size, by);
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task: \n" + "\t" + tasks[size-1].toString());
                System.out.println("Now you have " + size + " tasks in the list.");
                System.out.println("____________________________________________________________");
                command = in.nextLine();
            } else if (command.startsWith("event ")) {
                size++;
                int slashIndex = command.indexOf("/");
                String description = command.substring(6, slashIndex-1);
                String at = command.substring(slashIndex+4);
                tasks[size - 1] = new Event(description, size, at);
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task: \n" + "\t" + tasks[size - 1].toString());
                System.out.println("Now you have " + size + " tasks in the list.");
                System.out.println("____________________________________________________________");
                command = in.nextLine();
            } else if (command.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < size; i++) {
                    System.out.println(tasks[i].getStringNo() + "." + tasks[i].toString());
                }
                System.out.println("____________________________________________________________");
                command = in.nextLine();
            } else if (command.startsWith("done ")) {
                String description = command.substring(5);
                int j = 0;
                while (j < size) {
                    if (description.equals(tasks[j].getDescription())) {
                        tasks[j].markAsDone();
                        System.out.println("____________________________________________________________");
                        System.out.println("Nice! I've marked this task as done: ");
                        System.out.println("\t" + tasks[j].toString());
                        System.out.println("____________________________________________________________");
                        break;
                    }
                    j++;
                }
                if (j == size) {
                    System.out.println("____________________________________________________________");
                    System.out.println("no such task found");
                    System.out.println("____________________________________________________________");
                }
                command = in.nextLine();
            } else {
                System.out.println("____________________________________________________________");
                System.out.println("invalid command, pls try again");
                System.out.println("____________________________________________________________");
                command = in.nextLine();
            }
        }
    }
}

