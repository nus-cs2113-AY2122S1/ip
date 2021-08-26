import java.util.Locale;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";


        System.out.println("Hello from\n" + logo);
        System.out.println("    _____________________________________________________________");
        System.out.println("    Hello! I'm Duke!\n    What can I do for you?");
        System.out.println("    _____________________________________________________________");
        System.out.println("    Here are the performable actions:");
        System.out.println("    1. Add a new task by typing in the details of the task");
        System.out.println("    2. Mark a task as done by typing in \"done\" and the index of the task");
        System.out.println("    3. List all the tasks you have added to and check which are done by typing in \"list\". Done tasks will be marked with an X");
        System.out.println("    _____________________________________________________________");

        String line;

        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        TaskManager t1 = new TaskManager();

        while (!line.toLowerCase().equals("bye")) {
            String[] words = line.split(" ");
            if (line.toLowerCase().equals("list")) {
                t1.listTasks();
            } else if (words[0].equalsIgnoreCase("done")) {
                t1.markAsDone(Integer.parseInt(words[1]));
            } else {
                t1.addTask(line);
            }
            line = in.nextLine();
        }

        System.out.println("    _____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    _____________________________________________________________");

    }
}