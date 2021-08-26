import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner input = new Scanner(System.in);
        String command;
        TaskManager taskManager;
        do {
            command = input.nextLine();
            if (command.equals("list")) {
                TaskManager.list();
            }
            else if (command.equals("bye")) {
                break;
            }
            else  {
                TaskManager.add(command);
            }
        } while (!command.equalsIgnoreCase("Bye"));

        System.out.println("Bye. Hope to see you again soon!");
    }
}
