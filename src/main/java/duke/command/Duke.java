package duke.command;

import duke.datasaver.DataManager;
import duke.ui.Ui;

import java.util.Scanner;

public class Duke {

    // Constants
    public static final String EXIT_STRING = "bye";

    public static void main(String[] args) {
        Ui.printHeyMessage();

        TaskManager dukeTaskManager = new TaskManager();
        DataManager.loadData(dukeTaskManager.getTaskList());
        String userInput;
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();

        while (!userInput.trim().equalsIgnoreCase(EXIT_STRING)) {
            dukeTaskManager.handleUserInput(userInput);
            userInput = in.nextLine();
        }

        Ui.printByeMessage();
    }
}
