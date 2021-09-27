package commands;

import tasklist.TaskList;
import ui.Ui;
import storage.Storage;
import tasks.Event;


public class AddEventTask extends Command {
    private final String input;

    /**
     * Changes the input attribute to the one given by the user.
     *
     * @param input The input by the user.
     */
    public AddEventTask(String input) {
        this.input = input;
    }

    /**
     * Adds an event task into the task list and updates the database accordingly.
     * If there is no input after the command word, the user will be prompted to include one in the correct format.
     * If there is no /at in the input, the user will be prompted to include it in the correct place.
     * If there is no description or time range given, the user will be prompted to include it.
     *
     * @param tasks task list to be updated when an event task is added.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (!input.toLowerCase().contains("/at")) {
                ui.showMissingAtMessage();
            } else {
                int slashIndex = input.indexOf("/at");
                String task = input.substring(6, slashIndex).trim();
                String timeRange = input.substring(slashIndex + 3).trim();
                if (task.length() <= 0) {
                    ui.showMissingTaskDescriptionMessage();
                } else if (timeRange.length() <= 0) {
                    ui.showMissingEventTimeRangeMessage();
                } else {
                    tasks.addTaskToList(new Event(task, timeRange));
                    int taskIndex = tasks.getListSize() - 1;
                    ui.showTaskAddedMessage(tasks.getTaskFromList(taskIndex), tasks.getListSize());
                    int indexOfAddedTask = tasks.getListSize() - 1;
                    Event addedEvent = (Event) tasks.getTaskFromList(indexOfAddedTask);
                    String eventInput = addedEvent.getDescription() + "/at" + addedEvent.getTimeRange();
                    Storage.appendDatabase("E", eventInput, "0");
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showMissingTaskOrTimeRangeMessage();
        }
    }
}
