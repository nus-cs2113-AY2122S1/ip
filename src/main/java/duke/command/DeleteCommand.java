package duke.command;


import duke.Ui;
import duke.TaskList;
import duke.exceptions.DukeException;

public class DeleteCommand extends Command {

    private int chosenTaskIndex;

    /**
     * Deletes a task with the given index
     * @param ui UI to be used
     * @param taskList TaskList to be used
     * @param chosenTaskIndex index of task to be deleted
     */
    public DeleteCommand(Ui ui, TaskList taskList, int chosenTaskIndex) {
        super(ui, taskList);
        this.chosenTaskIndex = chosenTaskIndex;
    }

    /**
     * Executes DeleteCommand
     * @throws DukeException
     */
    @Override
    public void execute() throws DukeException {

        if (chosenTaskIndex < 0 || chosenTaskIndex > tasks.size()) {
            throw new DukeException("Index is not within range!");
        } else {
            String deletedTaskString = tasks.remove(chosenTaskIndex).toString();
            ui.printWithLines("deleted: " + deletedTaskString);
        }
    }
}
