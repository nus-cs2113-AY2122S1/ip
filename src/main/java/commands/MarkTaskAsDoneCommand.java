package commands;

import storage.Storage;
import tasks.Task;
import ui.TextUi;

public class MarkTaskAsDoneCommand extends Command{

    public static final String COMMAND_WORD = "done";
    public static final String COMMAND_DESC = "Marks a task as complete.";

    /** Offset required to convert between 1-indexing and 0-indexing.  */
    public static final int DISPLAYED_INDEX_OFFSET = 1;

    private final String targetVisibleIndex;

    public MarkTaskAsDoneCommand(String targetVisibleIndex) {
        this.targetVisibleIndex = targetVisibleIndex;
    }

    /**
     * Marks a task as done and update the data file.
     *
     * Prints an error message if user does not specify a task index or inputs a non-integer value
     *
     * Prints an error message if user specifies a task index which is not within the taskList
     */
    @Override
    public void execute() {
        try {
            int taskIndex = Integer.parseInt(targetVisibleIndex) - DISPLAYED_INDEX_OFFSET;
            Task taskToMarkAsDone = taskList.getTaskList().get(taskIndex);
            boolean isDone = taskToMarkAsDone.getIsDone();
            if (isDone) {
                TextUi.showTaskAlreadyCompletedMessage();
            }
            else {
                taskToMarkAsDone.markAsDone();
                TextUi.showTaskMarkedAsDoneMessage(taskToMarkAsDone);
                Storage.updateDataFile();
            }
        } catch (NumberFormatException e) {
            TextUi.showInvalidTaskIndexMessage(COMMAND_WORD);
        } catch (IndexOutOfBoundsException e) {
            TextUi.showExceedTaskIndexMessage(taskList);
        }
    }

}
