import java.util.Scanner;

public class Duke {
    //initialise tasks array and itemCount tracker
    static Task[] tasks = new Task[100];
    static int itemCount = 0;

    public static void Echo(String message) {
        if (message.equalsIgnoreCase("bye")) {
            System.out.println("    ____________________________________________________________");
            System.out.println("     Bye. Hope to see you again soon!");
            System.out.println("    ___________________________________________________________");
            System.exit(0);//end of program
        }

        if (message.contains("done")) {
            String[] parts = message.split(" ");
            int index = Integer.parseInt(parts[1]) - 1;
            tasks[index].setDone();
            System.out.println("    ____________________________________________________________");
            System.out.println("     Nice! I've marked this task as done: ");
            System.out.printf("       %s", tasks[index]);
            System.out.println("    ____________________________________________________________");
            return;
        }

        if (message.equalsIgnoreCase("list")) {
            System.out.println("    ____________________________________________________________");
            System.out.println("     Here are the tasks in your list:");
            for (int i = 0; i < itemCount; i++) {
                System.out.printf("     %d.%s", i + 1, tasks[i]);
            }
            System.out.println("    ____________________________________________________________");
            return;
        }

        //else add item to tasks array
        tasks[itemCount] = new Task(message); //add to tasks
        itemCount++;
        System.out.println("    ____________________________________________________________");
        System.out.println("     added:" + message);
        System.out.println("    ____________________________________________________________");
    }

    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        //initialise variables for scanning
        String line = "";
        Scanner in = new Scanner(System.in);

        //while user input != bye, case insensitive
        while (!line.equalsIgnoreCase("bye")) {
            line = in.nextLine();
            Echo(line);
        }
    }
}
