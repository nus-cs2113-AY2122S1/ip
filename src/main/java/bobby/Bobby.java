package bobby;

import bobby.manager.FileManager;
import bobby.manager.ResponseManager;
import bobby.manager.TaskManager;

import java.io.IOException;
import java.util.Scanner;

public class Bobby {
    public static void main(String[] args) throws IOException {
        TaskManager taskManager = new TaskManager();
        ResponseManager.printWelcomeMessage();
        FileManager.bootUpData(taskManager);

        do {
            String rawUserInput = taskManager.getUserInput();
            taskManager.processInput(rawUserInput);
        } while (taskManager.getIsRunning());
    }
}
