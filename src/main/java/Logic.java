public class Logic extends UserInterface{
    protected InputManager inputManager = new InputManager();
    protected CommandManager commandManager = new CommandManager();

    public void runOberon () {
        displayGreetingMessage();
        while (!commandManager.isExit) {
            inputManager.readInput();
            commandManager.executeCommand(inputManager.inputCommand, inputManager.commandArguments);
        }
        displayFarewellMessage();
    }
}
