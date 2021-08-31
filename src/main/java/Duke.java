import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        int size = 0;
        Task[] tasks = new Task[100];
        for (int i = 0; i < 100; i++) {
            tasks[i] = new Task();
        }
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
            if (command.startsWith("echo ")){
                System.out.println("____________________________________________________________");
                System.out.println(command.substring(5));
                System.out.println("____________________________________________________________");
                command = in.nextLine();
            } else if (command.startsWith("add ")) {
                tasks[size].setDescription(command.substring(4));
                tasks[size].setNoOfTask(size+1);
                size++;
                System.out.println("____________________________________________________________");
                System.out.println("added: " + command.substring(4));
                System.out.println("____________________________________________________________");
                command = in.nextLine();
            } else if (command.equals("show list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < size; i++) {
                    tasks[i].printTask();
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
                        System.out.println(description);
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

