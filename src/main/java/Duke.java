import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Horizontal line used for improving readability
        String horizontalLine = "____________________________________________________________";

        // Greeting message
        String greeting = horizontalLine +
                System.lineSeparator() +
                " Hello! I'm Duke" +
                System.lineSeparator() +
                " What can I do for you?" +
                System.lineSeparator() +
                horizontalLine +
                System.lineSeparator();

        // Farewell message
        String farewell = " Bye. Hope to see you again soon!";

        // Scanner class for reading user input
        String line;
        Scanner in = new Scanner(System.in);

        // Print greeting message
        System.out.println(greeting);

        // Initialise boolean variable for tracking whether user has said "bye"
        boolean hasUserSaidBye = false;

        // Task list and number of tasks
        String[] tasks = new String[100];
        int noOfTasks = 0;

        // While user has not said "bye", check user input repetitively
        while (!hasUserSaidBye) {
            line = in.nextLine();
            System.out.println(horizontalLine);
            if (line.equals("bye")) { // Print farewell message and exit
                hasUserSaidBye = true;
                System.out.println(farewell);
            } else if (line.equals("list")) { // Print out task list
                for (int i = 1; i <= noOfTasks; i++) {
                    System.out.println(" " + i + ". " + tasks[i - 1]);
                }
            } else { // Add new task to list
                tasks[noOfTasks] = line;
                noOfTasks++;
                System.out.println(" added: " + line);
            }
            System.out.println(horizontalLine + System.lineSeparator());
        }
    }
}
