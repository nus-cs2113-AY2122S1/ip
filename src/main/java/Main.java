import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Duke duke = new Duke();
        Scanner in = new Scanner(System.in);
        duke.greet();

        String command = in.nextLine();
        while (!command.equalsIgnoreCase("bye")) {
            switch (command.toLowerCase()) {
            case "list":
                duke.listTasks();
                break;
            default:
                duke.addTask(command);
                break;
            }
            command = in.nextLine();
        }
        duke.exit();
    }
}
