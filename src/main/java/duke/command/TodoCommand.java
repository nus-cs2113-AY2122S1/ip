package duke.command;

import duke.data.TaskList;
import duke.ui.Ui;

/**
 * An todo command is a simplified version of the add command
 * A <code>Todo</code> command can be called with the prefix 'todo' in Duke.
 */
public class TodoCommand extends Command {
    public TodoCommand() {
        super(CommandPrefix.TODO);
    }

    @Override
    public void saveListAndPrintDone(TaskList tasks) {
        super.saveListAndPrintDone(tasks);
    }

    @Override
    public void execute(TaskList tasks) {
        readLineAndAddTodo(tasks);
        saveListAndPrintDone(tasks);
    }

    private void readLineAndAddTodo(TaskList tasks) {
        String userInput = Ui.readLine();
        tasks.addTaskCheckDate(userInput);
    }
}