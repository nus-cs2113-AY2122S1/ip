package bobby;

import bobby.manager.FileManager;
import bobby.manager.ResponseManager;
import bobby.manager.TaskManager;

public class Bobby {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        FileManager.bootUpData(taskManager);
        ResponseManager.printWelcomeMessage(taskManager.getIsFirstRun());

        do {
            String rawUserInput = taskManager.getUserInput();
            taskManager.processInput(rawUserInput);
        } while (taskManager.getIsRunning());
    }
}
