package commands;

import tasklist.TaskList;
import ui.Ui;
import storage.Storage;
import tasks.Event;


public class AddEvent extends Command {
    protected Ui ui = new Ui();
    private final String input;

    public AddEvent(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks) {
        if (input.trim().length() <= 5) {
            ui.showMissingTaskOrTimeRangeMessage();
        } else {
            int slashIndex = input.toLowerCase().indexOf("/at");
            String task = input.substring(6, slashIndex).trim();
            String timeRange = input.substring(slashIndex + 3).trim();
            if (task.length() <= 0) {
                ui.showMissingTaskDescriptionMessage();
            } else if (!input.toLowerCase().contains("/at")) {
                ui.showMissingAtMessage();
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
    }
}
