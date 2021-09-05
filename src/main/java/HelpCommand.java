public class HelpCommand extends Command {

    /**
     * Runs a command to print a lis of possible commands.
     */
    @Override
    public void runCommand() {
        TaskManager.printHelpMessage();
    }
}
