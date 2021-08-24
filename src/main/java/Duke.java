import java.util.Locale;
import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    public static void echo(String line) {
        String separator = "____________________________________________________________";
        System.out.println(separator);
        System.out.println(" " + line);
        System.out.println(separator);
    }

    public static void showList(Task[] tasks) {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < tasks.length; i++) {
            System.out.println(" " + Integer.toString(i + 1) + "."
                    + "[" + tasks[i].getStatusIcon() + "] " + tasks[i].description);
        }
    }

    public static void main(String[] args) {
        String separator = "____________________________________________________________";
        String line;
        Scanner in = new Scanner(System.in);
        int taskCount = 0;
        Task[] tasks = new Task[100];

        System.out.println(separator);
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        System.out.println(separator);

        line = in.nextLine();
        while (!line.equals("bye")) {
            System.out.println(separator);
            if (line.equals("list")) {
                showList(Arrays.copyOf(tasks, taskCount));
            } else if (line.toLowerCase().startsWith("done")) {
                //"done" is not case-sensitive now
                String[] words = line.split(" ");
                //Assuming the 2nd word is the index of the task
                int taskIndex = Integer.parseInt(words[1]) - 1;
                tasks[taskIndex].markAsDone();
            } else {
                tasks[taskCount] = new Task(line);
                System.out.println(" added: " + line);
                taskCount++;
            }
            System.out.println(separator);
            line = in.nextLine();
        }

        System.out.println(separator);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(separator);
    }
}
