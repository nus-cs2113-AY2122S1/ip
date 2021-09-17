package duke.logic;

import duke.manager.command.CommandManager;
import duke.manager.input.InputManager;
import duke.manager.task.TaskManager;
import duke.storage.UserData;
import duke.ui.UserInterface;

public class Logic {

    private InputManager inputManager;
    private CommandManager commandManager;

    public Logic() {
        inputManager = new InputManager();
        commandManager = new CommandManager(new TaskManager());
    }

    public void run() {
        UserInterface.printGreetingMessage();
        UserData.initDataOnStartUp();
        while (!commandManager.isExit()) {
            inputManager.readInput();
            commandManager.executeCommand(inputManager.getInputCommand(),
                    inputManager.getCommandArguments());
        }
        UserInterface.printFarewellMessage();
    }
}
