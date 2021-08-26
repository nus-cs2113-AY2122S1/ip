import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner input = new Scanner(System.in);
        String command;

        while (true) {
            command = input.nextLine();
            if (command.equals("bye")) {
                break;
            }
            else if (command.equals("list")) {
                TaskManager.list();
            } else if (command.startsWith("done")) {
                int taskNo = Integer.parseInt(command.split(" ")[1]);
                TaskManager.mark(taskNo - 1);
            }
            else {
                TaskManager.add(command);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
