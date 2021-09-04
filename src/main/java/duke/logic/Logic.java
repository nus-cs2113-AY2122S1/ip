package duke.logic;

import duke.manager.command.CommandManager;
import duke.manager.input.InputManager;

public class Logic extends UserInterface {
    protected InputManager inputManager = new InputManager();
    protected CommandManager commandManager = new CommandManager();

    public void runOberon () {
        displayGreetingMessage();
        while (!commandManager.isExit()) {
            inputManager.readInput();
            commandManager.executeCommand(inputManager.getInputCommand(),
                    inputManager.getCommandArguments());
        }
        displayFarewellMessage();
    }
}
