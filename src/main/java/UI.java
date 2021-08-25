import java.util.Scanner;

public class UI {
    private TaskManager taskmanager;
    private Scanner scanner;

    public UI(TaskManager taskmanager, Scanner scanner) {
        this.taskmanager = taskmanager;
        this.scanner = scanner;
    }

    public void start() {
        String linebreak = "    ____________________________________________________________\n";
        String greetings = linebreak
                + "     Welcome to the Duke Chatbot\n"
                + "     What would you like to do today?\n"
                + linebreak;
        String farewell = "     Bye. Hope to see you again soon!";
        System.out.println(greetings);

        while (true) {
            String input = scanner.nextLine();
            String command[] = input.split(" ");
            String firstword = command[0];
            Task t = new Task(input);

            System.out.print(linebreak);
            switch (firstword) {
            case "bye":
                System.out.println(farewell);
                break;
            case "list":
                System.out.println("     Here are the tasks in your list:");
                taskmanager.list();
                break;
            case "done":
                System.out.println("     Nice! I've marked this task as done: ");
                int tasknumber = Integer.parseInt(command[1]);
                System.out.println("       [X] " + taskmanager.getName(tasknumber));
                taskmanager.checkDone(tasknumber);
                break;
            default:
                System.out.println("     added: " + input);
                taskmanager.add(t);
            }
            System.out.print(linebreak);
        }
    }
}
