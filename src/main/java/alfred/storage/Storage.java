package alfred.storage;

import alfred.exception.FileErrorException;
import alfred.task.TaskList;
import alfred.ui.TextUi;

import java.io.File;
import java.io.IOException;

public class Storage {
    private TaskList storage;
    private final String DEFAULT_FILE_PATH = "data/alfred.txt";
    private File taskFile;

    public Storage() {
        storage = new TaskList();
    }

    private boolean hasExistingFile() throws FileErrorException {
        taskFile = new File(DEFAULT_FILE_PATH);
        try {
            if (!taskFile.exists()) {
                taskFile.getParentFile().mkdirs();
            }
            if (taskFile.createNewFile()) {
                TextUi.createNewFileMessage();
                return false;
            }
        } catch (IOException e) {
            throw new FileErrorException();
        }
        return true;
    }

    private TaskList load() throws FileErrorException {
        if (!hasExistingFile()) {
            return new TaskList();
        }
        return TaskDecoder.scanTasks(taskFile);
    }
}
