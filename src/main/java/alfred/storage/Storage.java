package alfred.storage;

import alfred.exception.FileErrorException;
import alfred.task.TaskList;
import alfred.ui.TextUi;

import java.io.File;
import java.io.IOException;

public class Storage {
    private String filePath;
    private File taskFile;
    public static final String SEPARATOR = ",";

    /**
     * This constructor instantiates a new File object to track local data persistence
     * with the specified filePath.
     * @param filePath Specified filePath to track local data
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.taskFile = new File(this.filePath);
    }

    /**
     * This method checks if there is already an existing local storage file, and attempts to create a new local
     * storage file for data persistence if one does not exist.
     * @return boolean State of existing local storage file
     * @throws FileErrorException If there are errors writing or checking for local files
     */
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

    /**
     * This method attempts to decode and retrieve the list of Tasks from the instantiated taskFile, and returns a
     * new TaskList if the taskFile is not found.
     * @return TaskList This returns the loaded TaskList from save file
     * @throws FileErrorException If there are errors reading from local file
     */
    public TaskList load() throws FileErrorException {
        if (!hasExistingFile()) {
            return new TaskList();
        }
        return TaskDecoder.scanTasks(taskFile);
    }
}
