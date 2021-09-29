import java.util.Scanner;

/**
 * Duke Ui Class
 * @author husysg
 * @version 1.1
 */
public class Ui {
    /**
     * This function is used to get user commands in a loop
     * to exit it, enter "bye"
     */
    public static void getCommand() {
        String line;
        Scanner in = new Scanner(System.in);
        // get user input
        line = in.nextLine();
        while (!line.equals("bye")) {
            if (line.equals("list")) {
                TaskList.showList();
                line = in.nextLine();
                continue;
            }
            if (line.startsWith("done")) {
                int number = Integer.parseInt(line.substring(line.length() - 1));
                TaskList.markAsDone(number);
                Storage.saveTasks();
                line = in.nextLine();
                continue;
            }
            if (line.startsWith("delete")) {
                int number = Integer.parseInt(line.substring(line.length() - 1));
                TaskList.deleteTask(number);
                Storage.saveTasks();
                line = in.nextLine();
                continue;
            }
            if (line.startsWith("find")) {
                String keyword = line.substring(5);
                Data.search(keyword);
                line = in.nextLine();
                continue;
            }
            // Check whether exception exists here
            try {
                Parser.checkCommand(line);
            } catch (InvalidCommandException | EmptyDescriptionException e) {
                line = in.nextLine();
                continue;
            }
            // Command is valid, handle the command
            Ui.showLine();
            System.out.println("     Got it. I've added this task: ");
            Parser.handleCommand(line);
            Storage.saveTasks();
            System.out.println("     Now you have " + Data.descriptions.size() + " tasks in the list.");
            Ui.showLine();
            line = in.nextLine();
        }
        Ui.showLine();
        System.out.println("     Bye. Hope to see you again soon!");
        Ui.showLine();
    }

    /**
     * This function is used to print a split line
     */
    public static void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    public static void echo() {
        String line;
        Scanner in = new Scanner(System.in);
        // get user input
        line = in.nextLine();
        while (!line.equals("bye")) {
            Ui.showLine();
            System.out.println("     " + line);
            Ui.showLine();
            line = in.nextLine();
        }
        Ui.showLine();
        System.out.println("     Bye. Hope to see you again soon!");
        Ui.showLine();
    }

    /**
     * This function is used to greet the user at startup
     */
    public static void greeting() {
        Ui.showLine();
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        Ui.showLine();
        System.out.println();
    }
}
