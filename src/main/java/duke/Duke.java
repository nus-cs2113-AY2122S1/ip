package duke;

import duke.task.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        // Initialize variables for program startup
        Ui.printGreetingMessage();
        String userInput;
        ArrayList<Task> list = new ArrayList<>();
        Storage.loadSaveData(list);
        Ui.printList(list);
        Scanner in = new Scanner(System.in);

        while (true) {
            userInput = in.nextLine().trim();
            Command command = Parser.parseUserCommand(userInput);
            String userInputLowerCase = userInput.toLowerCase();
            TaskList.executeCommand(userInput, list, command);
        }
    }

}
