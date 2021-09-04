public class HelpCommand extends Command {
    @Override
    public void runCommand() {
        TaskManager.printHelpMessage();
    }
}
