package duke.command;

import Type.Task;
import duke.data.Storage;
import duke.data.TaskList;
import duke.startup.Ui;

import java.util.List;
import java.util.stream.Collectors;

public class DateCommand extends Command{
    public DateCommand() {
        super(CommandPrefix.date);
    }

    @Override
    public void saveListAndPrintDone(TaskList tasks) {
        super.saveListAndPrintDone(tasks);
        System.out.println("getting date!");
    }

    //assume date is given, if not don't print
    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        tasks.printTasksWithValidDate();
        saveListAndPrintDone(tasks);
    }
}
