import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        boolean isExit = false;
        TaskManager taskManager = new TaskManager();
        taskManager.greet();

        do {
            Scanner line = new Scanner(System.in);
            String[] input = line.nextLine().split(" ", 2);
            switch (input[0]) {
            case "bye":
                taskManager.exitMessage();
                isExit = true;
                break;
            case "list":
                taskManager.listTasks();
                break;
            default:
                taskManager.addTask(input[0] + " " + input[1]);
                break;
            }
        } while (!isExit);
    }
}
