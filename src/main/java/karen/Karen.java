package karen;

import karen.manager.FileManager;
import karen.manager.ResponseManager;
import karen.manager.TaskManager;

public class Karen {
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

