package duke.command;

import duke.data.Storage;
import duke.data.TaskList;
import duke.startup.Ui;

import java.util.Scanner;

public class EchoCommand extends Command{
    public EchoCommand() {
        super(CommandPrefix.echo);
    }
    @Override
    public void saveListAndPrintDone(TaskList tasks) {
        super.saveListAndPrintDone(tasks);
        System.out.println("echoing!");
    }

    public static String readInputEchoCommand() {
        Scanner in = new Scanner(System.in);
        String command = in.nextLine();
        System.out.println("    " + command);
        return command;
    }

    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        readInputEchoCommand();
        saveListAndPrintDone(tasks);
    }
}
