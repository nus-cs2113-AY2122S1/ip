import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        taskManager.greet();
        Scanner line = new Scanner(System.in);
        while(true) {
            String[] input = line.nextLine().split(" ", 2);
            switch (input[0]) {
            case "bye":
                taskManager.exitMessage();
                System.exit(0);
            case "list":
                taskManager.listTasks();
                break;
            case "done":
                taskManager.markAsDone(Integer.parseInt(input[1]));
                break;
            case "todo":
                taskManager.addTodoTask(input[1]);
                break;
            case "deadline":
                taskManager.addDeadlineTask(input[1]);
                break;
            case "event":
                taskManager.addEventTask(input[1]);
                break;
            default:
                taskManager.handleUnknownCommand();
                break;
            }
        }
    }
}
