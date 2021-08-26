import java.util.Scanner;
import java.util.regex.Pattern;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        Scanner scan = new Scanner(System.in);
        System.out.println("    Hello from\n" + logo);
        System.out.println("    _______________________________");
        System.out.println("    What up Dawg! I'm Duke");
        System.out.println("    What can I do for you?");

        int tasksCount = 0;
        Task[] tasks = new Task[100];

        String input = scan.nextLine();

        // inputs are all case-insensitive
        while (!input.equalsIgnoreCase("bye")) {
            if (input.equalsIgnoreCase("list")) {
                System.out.println("\t_______________________________");
                if (tasksCount > 0) {
                    for (int i = 0; i < tasksCount; i++) {
                        int index = i + 1;
                        System.out.println("\t" + index + ". " + tasks[i].getStatusIcon() + tasks[i].description);
                    }
                } else {
                    System.out.println("There are no tasks in your list.");
                }
                System.out.println("\t_______________________________");
            } else {
                if (Pattern.matches("(?i)done \\d+", input)) {
                    String number = input.replaceAll("\\D+", "");
                    int n = Integer.parseInt(number);
                    if (n >= 1 && n <= tasksCount) {
                        tasks[n - 1].markAsDone();
                        System.out.println("\t_______________________________");
                        System.out.println("\tNice! I've marked this as done.");
                        System.out.println("\t\t[X] " + tasks[n - 1].description);
                    }
                } else {
                    tasks[tasksCount] = new Task(input);
                    System.out.println("\t_______________________________");
                    System.out.println("\tadded: " + tasks[tasksCount].description);
                    System.out.println("\t_______________________________");
                    tasksCount++;
                }
            }
            input = scan.nextLine();
        }
        System.out.println("\t_______________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t______________________________");
    }
}
