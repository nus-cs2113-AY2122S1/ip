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
        System.out.println("        1. Add a new To Do by typing \"todo {content of your to do}\".");
        System.out.println("        2. Add a new Deadline by typing \"deadline {content of your deadline} /by {date of deadline}\".");
        System.out.println("        3. Add a new Event by typing \"event {content of your event} /at {date of event}\".");
        System.out.println("        4. Mark a task as done by typing in \"done\" and the index of the task on the list.");
        System.out.println("        5. Check all the tasks you have added by typing in \"list\". Done tasks will be marked with an X.");
        System.out.println("    _____________________________________________________________");

        String line;

        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        TaskManager t1 = new TaskManager();

        while (!line.equalsIgnoreCase("bye")) {
            String[] words = line.split(" ");
            if (line.equalsIgnoreCase("list")) {
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