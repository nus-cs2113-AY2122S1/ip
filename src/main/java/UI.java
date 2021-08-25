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
        String farewell = "     Bye. Hope to see you again soon!\n"
                + linebreak;

        System.out.println(greetings);

        while (true) {
            String command = scanner.nextLine();
            switch (command) {
            case "bye":
                System.out.println(farewell);
                break;
            case "list":
                System.out.print(linebreak);
                taskmanager.list();
                System.out.println(linebreak);
                break;
            default:
                System.out.println(linebreak + "     added: " + command + "\n" + linebreak);
                taskmanager.add(command);
            }
        }
    }
}
