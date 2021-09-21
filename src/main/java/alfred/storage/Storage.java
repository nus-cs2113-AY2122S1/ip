package alfred.storage;

import alfred.exception.FileErrorException;
import alfred.task.TaskList;
import alfred.ui.TextUi;

import java.io.File;
import java.io.IOException;

public class Storage {
    private String filePath;
    private File taskFile;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.taskFile = new File(this.filePath);
    }

    private boolean hasExistingFile() throws FileErrorException {
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
