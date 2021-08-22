import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // Horizontal line used for improving readability
        String horizontalLine = "____________________________________________________________";

        // Greeting message
        String greeting = horizontalLine + System.lineSeparator() +
                " Hello! I'm Duke" + System.lineSeparator() +
                " What can I do for you?" + System.lineSeparator() +
                horizontalLine + System.lineSeparator();

        // Farewell message
        String farewell = " Bye. Hope to see you again soon!";

        // Scanner class for reading user input
        String line;
        Scanner in = new Scanner(System.in);

        // Print greeting message
        System.out.println(greeting);

        // Initialise boolean variable for tracking whether user has said "bye"
        boolean hasUserSaidBye = false;

        // Task list
        Task[] tasks = new Task[100];

        // While user has not said "bye", check user input repetitively
        while (!hasUserSaidBye) {
            line = in.nextLine();
            String[] words = line.split(" "); // Convert sentence into array of words
            System.out.println(horizontalLine);
            if (line.equals("bye")) { // Print farewell message and exit
                hasUserSaidBye = true;
                System.out.println(farewell);
            } else if (line.equals("list")) { // Print out task list
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < Task.getNoOfTasks(); i++) {
                    System.out.println(" " + (i + 1) + "." +
                            tasks[i].getStatusIcon() + " " + tasks[i].getDescription());
                }
            } else if (words[0].equals("done")) { // Mark the specified task as done
                int taskId = Integer.parseInt(words[1]);
                tasks[taskId - 1].markAsDone();
                System.out.println(" Nice! I've marked this task as done:" + System.lineSeparator() + "   " +
                        tasks[taskId - 1].getStatusIcon() + " " + tasks[taskId - 1].getDescription());
            } else { // Add new task to the task list
                Task newTask = new Task(line);
                tasks[newTask.getTaskId() - 1] = newTask;
                System.out.println(" added: " + line);
            }
            System.out.println(horizontalLine + System.lineSeparator());
        }
    }
}
