package karlett.commands;

import karlett.storage.StorageFile;
import karlett.task.Deadline;
import karlett.task.Event;
import karlett.task.Task;
import karlett.tasklist.TaskList;
import karlett.ui.TextUi;

import java.io.IOException;
import java.time.LocalDateTime;

public class AddCommand extends Command {

    String command;
    String taskDescription;
    LocalDateTime time;

    public AddCommand(String commandWord, String description, LocalDateTime timeInfo) {
        command = commandWord;
        taskDescription = description;
        time = timeInfo;
    }

    /**
     * Add the task (todo/deadline/event) to the TaskList and the storage file.
     *
     * @param tasks a TaskList that is already stored
     * @param textUi text user interface
     * @param storageFile file to which command can write to
     * @throws IOException input or output exception
     */
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
        default: // "todo"
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
