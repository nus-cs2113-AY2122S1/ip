package bobby;

import bobby.manager.ResponseManager;
import bobby.manager.TaskManager;

import java.util.Scanner;

public class Bobby {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        ResponseManager.printWelcomeMessage();

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
