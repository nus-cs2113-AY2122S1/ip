package duke;

import duke.command.TaskManager;
import duke.datasaver.DataManager;
import duke.ui.Ui;

import java.util.Scanner;

import static duke.constants.DukeCommandStrings.EXIT_COMMAND;

public class Duke {

    public static void main(String[] args) {
        Ui.printHeyMessage();

        TaskManager dukeTaskManager = new TaskManager();
        DataManager.loadData(dukeTaskManager.getTaskList());
        String userInput;
        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();

        while (!userInput.trim().equalsIgnoreCase(EXIT_COMMAND)) {
            dukeTaskManager.handleUserInput(userInput);
            userInput = in.nextLine();
        }

        Ui.printByeMessage();
    }
}
