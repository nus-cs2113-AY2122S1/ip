package bobby;

import bobby.manager.ResponseManager;
import bobby.manager.TaskManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Bobby {
    public static void main(String[] args) throws IOException {
        TaskManager taskManager = new TaskManager();
        ResponseManager.printWelcomeMessage();

        // load the saved data into taskList
        try {
            FileManager.bootUpData("data/bobby.txt", taskManager);
        } catch (IOException e) {
            ResponseManager.printIOExceptionMessage();
        }

        //obtain user input
        String rawUserInput;
        Scanner in = new Scanner(System.in);
        rawUserInput = in.nextLine().trim();

        while (!rawUserInput.equalsIgnoreCase("bye")) {
            taskManager.processInput(rawUserInput);

            //get the new input
            rawUserInput = in.nextLine();
        }

        //if bye is the input
        ResponseManager.printGoodByeMessage();
    }
}
