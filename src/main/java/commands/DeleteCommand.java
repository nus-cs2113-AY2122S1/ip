package commands;

import storage.Storage;
import tasks.Task;
import ui.TextUi;

public class DeleteCommand extends Command{

    public static final String COMMAND_WORD = "delete";
    public static final String COMMAND_DESC = "Deletes a task";

    private final String targetVisibleIndex;

    /** Offset required to convert between 1-indexing and 0-indexing.  */
    public static final int DISPLAYED_INDEX_OFFSET = 1;

    public DeleteCommand(String targetVisibleIndex) {
        this.targetVisibleIndex = targetVisibleIndex;
    }


    @Override
    public void execute() {
        try {
            int taskIndex = Integer.parseInt(targetVisibleIndex) - DISPLAYED_INDEX_OFFSET;
            Task taskToDelete = taskList.getTask(taskIndex);
            taskList.deleteTask(taskIndex);
            TextUi.showTaskDeletedMessage(taskToDelete, taskList.getSize());
            Storage.updateDataFile();
        } catch (NumberFormatException e) {
            TextUi.showInvalidTaskIndexMessage(COMMAND_WORD);
        } catch (IndexOutOfBoundsException e) {
            TextUi.showExceedTaskIndexMessage(taskList);
        }
    }
}
