package duke.logic;

import duke.manager.command.CommandManager;
import duke.manager.input.InputManager;
import duke.storage.UserData;
import duke.ui.UserInterface;

public class Logic extends UserInterface {

    private InputManager inputManager = new InputManager();
    private CommandManager commandManager = new CommandManager();

    public void runOberon () {
        printGreetingMessage();
        UserData.initDataOnStartUp();
        while (!commandManager.isExit()) {
            inputManager.readInput();
            commandManager.executeCommand(inputManager.getInputCommand(),
                    inputManager.getCommandArguments());
        }
        printFarewellMessage();
    }
}
