import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        Scanner scanner = new Scanner(System.in);
        System.out.println("    Hello from\n" + logo);
        System.out.println("    _______________________________");
        System.out.println("    What up Dawg! I'm Duke");
        System.out.println("    What can I do for you?");

        String input = scanner.nextLine();

        // Initialise tasks
        int tasksCount = 0;
        Task[] tasks = new Task[100];

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println("\t_______________________________");
                for (int i = 0; i < tasksCount; i++) {
                    int index = i + 1;
                    System.out.println("\t" + index + ". " + tasks[i].getStatusIcon() + tasks[i].description);
                }
                System.out.println("\t_______________________________");
            } else {
                if (input.length() >= 6 && input.substring(0,3) == "done") {
                    //int a = Integer.parseInt(arr[1]) - 1;
                    System.out.println("ok");
                    /*
                    if (a >= 1 && a <= tasksCount) {
                        tasks[a].markAsDone();
                        System.out.println("\t_______________________________");
                        System.out.println("\tNice! I've marked this as done.");
                        System.out.println("\t\t[X] " + tasks[a].description);
                    }

                     */
                } else { // add task
                    tasks[tasksCount] = new Task(input);
                    System.out.println("\t_______________________________");
                    System.out.println("\tadded: " + tasks[tasksCount].description);
                    System.out.println("\t_______________________________");
                    tasksCount++;
                }
            }
            input = scanner.nextLine();
        }

        System.out.println("\t_______________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t______________________________");
    }
}