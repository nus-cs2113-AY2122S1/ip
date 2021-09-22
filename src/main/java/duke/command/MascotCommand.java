package duke.command;

import Type.Mascot;
import duke.data.Storage;
import duke.data.TaskList;
import duke.startup.Ui;

public class MascotCommand extends Command{
    public MascotCommand() {
        super(CommandPrefix.mascot);
    }

    @Override
    public void saveListAndPrintDone(TaskList tasks) {
        super.saveListAndPrintDone(tasks);
        System.out.println("saying stuff!");
    }

    private static void mascotSay() {
        Mascot jim = new Mascot();
        String text = Ui.readCommand();
        Mascot.penguinSay(text);
    }

    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        mascotSay();
        saveListAndPrintDone(tasks);
    }
}
