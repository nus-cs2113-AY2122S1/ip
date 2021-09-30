package duke.command;

import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents the command to mark a task as done
 */
public class TaskDoneCommand extends Command{

    private static final int DONE_SUBSTRING_INDEX = 5;
    
    private String input;

    public TaskDoneCommand(String input) {
        this.input = input;
    }

    /**
     * Marks the specified task as done
     *
     * @param list The tasklist instance to handle interactions with the ArrayList of task
     * @param ui The ui instance to handle interactions with the user
     * @param storage The storage instance to handle interactions with the text file
     */
    @Override
     public void execute(TaskList list, Ui ui, Storage storage) {
        try {
            int taskIndex = Integer.parseInt(input.substring(DONE_SUBSTRING_INDEX).trim()) - 1;
            if (taskIndex < list.size() && taskIndex >= 0) {
                if (list.getList().get(taskIndex).isDone()) {
                    ui.printTaskIsDoneMessage();
                } else {
                    list.markTaskAsDone(taskIndex);
                    storage.updateFile();
                    ui.printMarkTaskAsDoneMessage(list.getList().get(taskIndex));
                }
            } else {
                ui.printTaskDoesNotExistMessage();
            }
        } catch(StringIndexOutOfBoundsException e) {
            ui.printWrongCommandFormatMessage();
        }
     }

    /**
     * @return returns true if the command to exit the application is given
     */
    @Override
    public boolean isExit() {
        return false;
    }
}