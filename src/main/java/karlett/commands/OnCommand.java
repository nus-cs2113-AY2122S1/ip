package karlett.commands;

import karlett.storage.StorageFile;
import karlett.task.Deadline;
import karlett.task.Event;
import karlett.task.Task;
import karlett.tasklist.TaskList;
import karlett.ui.TextUi;

import java.io.IOException;
import java.time.LocalDateTime;

public class OnCommand extends Command {
    LocalDateTime time;

    public OnCommand(LocalDateTime inputTime) {
        time = inputTime;
    }

    @Override
    public void execute(TaskList tasks, TextUi ui, StorageFile storageFile) throws IOException {
        TaskList matchedTasks = new TaskList();
        for (int i = 0; i < tasks.getNumberOfTasks(); i++) {
            Task t = tasks.get(i);
            if (t instanceof Deadline) {
                if (((Deadline) t).isOnTheDay(time)) {
                    matchedTasks.add(tasks.get(i));
                }
            } else if (t instanceof Event) {
                if (((Event) t).isOnTheDay(time)) {
                    matchedTasks.add(tasks.get(i));
                }
            }
        }
        if (matchedTasks.getNumberOfTasks() == 0) {
            ui.printNoMatchedTaskFoundMessage(time);
            return;
        }
        ui.printMatchedTasksMessage(matchedTasks);

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
