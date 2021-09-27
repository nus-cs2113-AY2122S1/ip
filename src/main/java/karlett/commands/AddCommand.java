package karlett.commands;

import karlett.storage.StorageFile;
import karlett.task.Deadline;
import karlett.task.Event;
import karlett.task.Task;
import karlett.tasklist.TaskList;
import karlett.ui.TextUi;

import java.io.IOException;

public class AddCommand extends Command {

    String command;
    String taskDescription;
    String time;

    public AddCommand(String commandWord, String description, String timeInfo) {
        command = commandWord;
        taskDescription = description;
        time = timeInfo;
    }

    @Override
    public void execute(TaskList tasks, TextUi textUi, StorageFile storageFile) throws IOException {
        Task task;
        switch (command) {
        case "deadline":
            task = new Deadline(taskDescription, time);
            break;
        case "event":
            task = new Event(taskDescription, time);
            break;
        default: // "todo case"
            task = new Task(taskDescription);
        };
        tasks.add(task);
        storageFile.appendNewTaskToFile(task);
        ui = textUi;
        ui.printNewTaskAddedMessage(tasks, task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
