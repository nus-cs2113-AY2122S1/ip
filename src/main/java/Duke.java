import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printSection("Hello! I'm Duke", "What can I do for you?");

        // Initialize a scanner to read in user input
        Scanner scanner = new Scanner(System.in);

        // Keep listening for new commands
        while (true) {
            // Read the input of the user
            System.out.print("> ");
            String cmd = scanner.nextLine();
            System.out.println();

            // Check if the user wishes to exit the program.
            if (!parseCommands(cmd)) {
                break;
            }

            // Otherwise, ask the user for more commands
            System.out.println("--");
            System.out.println("Anything else I can help you with?");
        }

        // Cleanup and print exit message
        printSection("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the bot name and given data provided.
     * If no data is provided, it will print a horizontal line with the bot name.
     *
     * @param data The string(s) to print to console.
     */
    private static void printSection(String ...data) {
        System.out.println("--// Duke //-----------------------------------------------");
        for (String s: data) {
            System.out.println(s);
        }
    }

    /**
     * Interprets the commands sent by the user.
     *
     * @param cmdString The command sent by the user.
     * @return Returns true if the program should continue executing.
     */
    private static boolean parseCommands(String cmdString) {
        String[] cmdParts = cmdString.split(" ");
        switch (cmdParts[0].toLowerCase()) {
        case "bye":
            return false;
        case "list":
            if (tasks.isEmpty()) {
                printSection("You have nothing in your to-do list!");
            } else {
                printSection("Here is your to-do list:");
                int toBeCompleted = 0;
                for (int i = 0; i < tasks.size(); i++) {
                    Task task = tasks.get(i);
                    if (!task.isCompleted()) {
                        toBeCompleted++;
                    }
                    System.out.printf("%d. %s\n", i + 1, task.getStatusString());
                }
                System.out.printf("\nYou have %d task(s) left to do.\n", toBeCompleted);
            }
            return true;
        case "done":
            int taskNo = Integer.parseInt(cmdParts[1]);
            if (taskNo < 1 || taskNo > tasks.size()) {
                printSection("That is not a valid task number :(");
            } else  {
                Task task = tasks.get(taskNo - 1);
                if (task.markAsCompleted()) {
                    printSection("Nice! I have marked the following task as completed: ",
                            "\t" + task.getStatusString(), "");
                } else {
                    printSection("The task is already done:", "\t" + task.getStatusString(), "");
                }
            }
            return true;
        default:
            tasks.add(new Task(cmdString));
            printSection("I have added \"" + cmdString + "\" to your to-do list.");
            return true;
        }
    }
}
