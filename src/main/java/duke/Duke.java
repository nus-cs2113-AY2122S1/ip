package duke;

import duke.command.CommandExecutor;
import duke.datasaver.DataManager;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {

    private final CommandExecutor commandExecutor;
    private final TaskList taskList;
    private final DataManager dataManager;

    public Duke() {
        this.commandExecutor = new CommandExecutor();
        this.taskList = new TaskList();
        this.dataManager = new DataManager();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    private void run() {
        start();
        runLoopUntilExitCommand(this.commandExecutor);
    }

    private void start() {
        dataManager.loadData(this.taskList.getTaskList());
        Ui.printHeyMessage();
    }

    private void runLoopUntilExitCommand(CommandExecutor commandExecutor) {
        String userInput;
        boolean isExit;

        do {
            userInput = Ui.readUserInput();
            commandExecutor.execute(userInput, taskList, dataManager);
            isExit = commandExecutor.isExit(userInput);
        } while (!isExit);
    }


}
