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
                System.out.println("_________________________________________\n"
                        + "I'm sorry I didn't understand.\n"
                        + "_________________________________________\n");
                break;
            }
        } while (!isExit);
    }
}
