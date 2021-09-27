package duke.storage;

import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.task.TaskList;
import duke.data.task.Todo;
import duke.storage.exceptions.InvalidStorageDataException;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;

public class TaskListDecoder {

    private static Ui ui;

    public TaskListDecoder() {
        this.ui = new Ui();
    }

    public static TaskList decodeTaskList(List<String> encodedTaskList) {
        final ArrayList<Task> taskList = new ArrayList<>();
        for (String encodedTask : encodedTaskList) {
            try {
                taskList.add(decodeTaskFromString(encodedTask));
            } catch (InvalidStorageDataException e) {
                ui.showMessageFramedWithDivider(e.toString());
            }
        }
        return new TaskList(taskList);
    }

    private static Task decodeTaskFromString(String encodedTask) throws InvalidStorageDataException {
        Task task;
        boolean isDone;
        String[] words = encodedTask.split(" \\| "); //Length = 3 for Todo; 4 for Deadline, Event


        switch (words[1]) {
        case "0":
            isDone = false;
            break;
        case "1":
            isDone = true;
            break;
        default: //if second letter (done status) is not 0 or 1
            throw new InvalidStorageDataException(encodedTask);
        }

        switch (words[0]) {
        case Task.TODO_ACRONYM:
            return new Todo(words[2], isDone);
        case Task.DEADLINE_ACRONYM:
            return new Deadline(words[2], words[3], isDone);
        case Task.EVENT_ACRONYM:
            return new Event(words[2], words[3], isDone);
        default: //if first letter is not any of the valid task acronyms
            throw new InvalidStorageDataException(encodedTask);
        }
    }


}
