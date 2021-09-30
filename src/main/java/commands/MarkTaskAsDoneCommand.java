package commands;

import storage.Storage;
import tasks.Task;
import TextUi.TextUi;

public class MarkTaskAsDoneCommand extends Command{

    public static final String COMMAND_WORD = "done";
    public static final String COMMAND_DESC = "Marks a task as complete.";

    /** Offset required to convert between 1-indexing and 0-indexing.  */
    public static final int DISPLAYED_INDEX_OFFSET = 1;

    private final String targetVisibleIndex;

    public MarkTaskAsDoneCommand(String targetVisibleIndex) {
        this.targetVisibleIndex = targetVisibleIndex;
    }

    @Override
    public void execute() {
        try {
            int taskIndex = Integer.parseInt(targetVisibleIndex) - DISPLAYED_INDEX_OFFSET;
            Task taskToMarkAsDone = taskList.getTaskList().get(taskIndex);
            taskToMarkAsDone.markAsDone();
            TextUi.showTaskCompletedMessage(taskToMarkAsDone);
            Storage.updateDataFile();
        } catch (NumberFormatException e) {
            TextUi.showInvalidTaskIndexMessage(COMMAND_WORD);
        } catch (IndexOutOfBoundsException e) {
            TextUi.showExceedTaskIndexMessage(taskList);
        }
    }

}
