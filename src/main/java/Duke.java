import java.util.Scanner;

/**
 * Chatbot program Duke that tracks user tasks.
 *
 * @author Leow Yuan Yang
 * @version 1.0
 * @since 2021-08-25
 */
public class Duke {
    public static void main(String[] args) {
        String SEPARATOR = "\t____________________________________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        // Introduction of chatbot
        System.out.println("Hello from\n" + logo);
        System.out.println(SEPARATOR);
        System.out.println("\tHello I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println(SEPARATOR);

        boolean isDone = false;
        Task[] taskList = new Task[100];

        do {
            // Read inputs
            Scanner in = new Scanner(System.in);
            String command;
            command = in.nextLine();

            if (command.startsWith("done")) {
                // Find item number and update item status
                int itemNum = Integer.parseInt(command.replaceAll("[^0-9]", "")) - 1;
                taskList[itemNum].setDone();

                // Print output
                System.out.println(SEPARATOR);
                System.out.println("\tNice! I've marked this task as done:");
                System.out.printf("\t  [%s] %s\n", taskList[itemNum].getStatusIcon(),
                        taskList[itemNum].getDescription());
                System.out.println(SEPARATOR);
            } else {
                switch (command) {
                case "bye":
                    // Exit bot
                    isDone = true;

                    // Print output
                    System.out.println(SEPARATOR);
                    System.out.println("\tBye. Hope to see you again soon!");
                    System.out.println(SEPARATOR);
                    break;
                case "list":
                    // Print output
                    System.out.println(SEPARATOR);
                    for (int i = 0; i < Task.getNumOfTasks(); i++) {
                        System.out.printf("\t%d.[%s] %s\n", i + 1, taskList[i].getStatusIcon(),
                                taskList[i].getDescription());
                    }
                    System.out.println(SEPARATOR);
                    break;
                default:
                    // Add task
                    taskList[Task.getNumOfTasks()] = new Task(command);

                    // Print output
                    System.out.println(SEPARATOR);
                    System.out.println("\tadded: " + command);
                    System.out.println(SEPARATOR);
                    break;
                }
            }
        } while (!isDone);
    }
}
