package duke.command;

import duke.data.TaskList;

/**
 * Command to list all of Duke's features
 * A <code>Help</code> command can be called with the prefix 'help' in Duke.
 */
public class HelpCommand extends Command {
    public HelpCommand() {
        super(CommandPrefix.HELP);
    }

    @Override
    public void saveListAndPrintDone(TaskList tasks) {
        super.saveListAndPrintDone(tasks);
        System.out.println("printing features");
    }

    public void printFeatures() {
        for (CommandPrefix f : CommandPrefix.values()) {
            //Oopsie is left as a default command and thus not reflected
            if (!f.getPrefix().equals(CommandPrefix.OOPSIE.getPrefix())) {
                System.out.print(f.getPrefix() + " : " + f.getDescription());
                System.out.println("\n");
            }
        }
    }

    @Override
    public void execute(TaskList tasks) {
        printFeatures();
        saveListAndPrintDone(tasks);
    }
}
