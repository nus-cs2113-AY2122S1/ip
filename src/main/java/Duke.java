import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        MessageManager.printWelcomeMessage();

        String rawUserInput;
        Scanner in = new Scanner(System.in);
        rawUserInput = in.nextLine().trim();



        while (!rawUserInput.equalsIgnoreCase("bye")) {
            taskManager.processInput(rawUserInput);

            //get the new input
            rawUserInput = in.nextLine();
        }

        //if bye is the input
        MessageManager.printGoodByeMessage();
    }
}
