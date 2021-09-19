package duke;

import duke.save.SaveTaskListToText;

import java.io.IOException;

public class Storage {
    public static SaveTaskListToText getSaveTaskListToText() throws IOException {
        SaveTaskListToText dukeTaskText = new SaveTaskListToText();
        TaskList.numberOfTasks = dukeTaskText.loadTask(TaskList.tasks);
        return dukeTaskText;
    }
}
