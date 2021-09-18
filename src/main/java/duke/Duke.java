package duke;

import duke.command.TaskManager;
import duke.datasaver.DataManager;
import duke.ui.Ui;

import java.util.Scanner;

import static duke.constants.DukeCommandStrings.EXIT_COMMAND;

public class Duke {
    private TaskManager dukeTaskManager;

    public static void main(String[] args) {
        new Duke().run();
    }

    private void run() {
        start();
        runLoopUntilExitCommand(this.dukeTaskManager);
    }

    private void start() {
        this.dukeTaskManager = new TaskManager();
        DataManager.loadData(this.dukeTaskManager.getTaskList());
        Ui.printHeyMessage();
    }

    private void runLoopUntilExitCommand(TaskManager dukeTaskManager) {
        String userInput;
        do {
            userInput = Ui.readUserInput();
            dukeTaskManager.handleUserInput(userInput);
        } while (!userInput.trim().toLowerCase().startsWith(EXIT_COMMAND));
    }


}
