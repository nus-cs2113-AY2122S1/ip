package duke.storage;

import duke.data.task.TaskList;
import duke.storage.exceptions.CannotReadFromFileException;
import duke.storage.exceptions.UnableToWriteToFileException;
import duke.ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles all read/write operations to the storage file containing user data of Tasks.
 */
public class Storage {

    /** File path to store task data */
    private static final String DATA_FILE_PATH = "./data/dude.txt";

    private static TaskListEncoder taskListEncoder;
    private static TaskListDecoder taskListDecoder;


    public Storage() {
        this.taskListEncoder = new TaskListEncoder();
        this.taskListDecoder = new TaskListDecoder();
    }

    /**
     * Create data file if it does not exist.
     * As a precaution, this function should be run before every read/write operation to the file.
     *
     * @throws UnableToWriteToFileException If system does not have permission to write to this directory/file
     */
    private void createFileIfDoesNotExist() throws UnableToWriteToFileException{
        File f = new File(DATA_FILE_PATH);
        try {
            f.getParentFile().mkdirs(); //Make the directory for the file if it does not exist
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch (IOException e) {
           throw new UnableToWriteToFileException();
        }
    }

    /**
     * Rewrite file with updated list of tasks
     *
     * @param tasks TaskList containing all the tasks to be written into the storage file
     * @throws UnableToWriteToFileException If system does not have permission to write to this directory/file
     */
    public void rewriteTaskListToFile(TaskList tasks) throws UnableToWriteToFileException {
        createFileIfDoesNotExist();
        try {
            FileWriter fw = new FileWriter(DATA_FILE_PATH);
            List<String> encodedTaskList = new TaskListEncoder().encodeTaskList(tasks);
            for (String encodedTask : encodedTaskList) {
                fw.write(encodedTask + Ui.LS);
            }
            fw.close();
        } catch (IOException e) {
           throw new UnableToWriteToFileException();
        }
    }

    /**
     * Load tasks from file into TaskList
     *
     * @return TaskList containing tasks stored in the storage file
     * @throws UnableToWriteToFileException If system does not have permission to write to this directory/file (To create the storage file if it does not exist)
     * @throws CannotReadFromFileException If system does not have permission to read from this directory/file
     */
    public TaskList loadTasksFromFile() throws UnableToWriteToFileException, CannotReadFromFileException {
        createFileIfDoesNotExist();
        File f = new File(DATA_FILE_PATH);
        final List<String> encodedTaskList = new ArrayList<String>();
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                encodedTaskList.add(s.nextLine());
            }
            return taskListDecoder.decodeTaskList(encodedTaskList);
        } catch (IOException e) {
            throw new CannotReadFromFileException();
        }
    }


}
