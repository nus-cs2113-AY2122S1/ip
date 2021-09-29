package duke.command;

import duke.type.Mascot;
import duke.data.TaskList;
import duke.ui.Ui;

/**
 * Command to have a Mascot echo user input.
 * A Mascot is in fact a Penguin in our method, but can represent other Objects in the near future.
 * A <code>Mascot</code> command can be called with the prefix 'mascot' in Duke.
 */
public class MascotCommand extends Command{
    public MascotCommand() {
        super(CommandPrefix.MASCOT);
    }

    @Override
    public void saveListAndPrintDone(TaskList tasks) {
        super.saveListAndPrintDone(tasks);
        System.out.println("saying stuff!");
    }

    private static void mascotSay() {
        String text = Ui.readLine();
        Mascot.penguinSay(text);
    }

    @Override
    public void execute(TaskList tasks) {
        mascotSay();
        saveListAndPrintDone(tasks);
    }
}
