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

        String command = scanner.nextLine();
        String[] tasks = new String[100];
        int numOfTasks = 0;

        while (!command.equals("bye")) {
            if (!command.equals("list")) {
                tasks[numOfTasks] = command;
                System.out.println("    _______________________________");
                System.out.println("    added: " + tasks[numOfTasks]);
                System.out.println("    _______________________________");
                numOfTasks++;
            }
            else {
                int index = 1;
                System.out.println("    _______________________________");
                for (int i = 0; i < numOfTasks; i++, index++) {
                    System.out.println("    " + index + ". " + tasks[i]);
                }
                System.out.println("    _______________________________");
            }
            command = scanner.nextLine();
        }

        System.out.println("    _______________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ______________________________");
    }
}
