import alfred.manager.TaskManager;

import java.util.Scanner;

public class Alfred {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        String userInput;
        while (true) {
            userInput = scanner.nextLine().trim();
            if (userInput.equalsIgnoreCase("bye")) {
                taskManager.shutdownMessage();
                return;
            }
            taskManager.processInput(userInput);
        }
    }
}
