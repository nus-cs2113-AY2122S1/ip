package duke.logic;

import duke.manager.command.CommandManager;
import duke.manager.input.InputManager;

public class Logic extends UserInterface {
    private InputManager inputManager = new InputManager();
    private CommandManager commandManager = new CommandManager();

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
