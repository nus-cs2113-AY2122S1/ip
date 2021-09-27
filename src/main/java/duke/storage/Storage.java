package duke.storage;

import duke.data.task.TaskList;
import duke.storage.exceptions.CannotReadFromFileException;
import duke.storage.exceptions.UnableToWriteToFileException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static duke.ui.Ui.LS;

public class Storage {

    /** File path to store task data */
    private static final String DATA_FILE_PATH = "./data/duke.txt";
    private static final String MESSAGE_ERROR_DATA_FILE_MISSING = "Hmm? Your data file suddenly got deleted... I'll add this task to the new file," + LS
            + "but all the tasks above this will not be stored! :(";

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
     */
    public void rewriteTaskListToFile(TaskList tasks) throws UnableToWriteToFileException {
        createFileIfDoesNotExist();
        try {
            FileWriter fw = new FileWriter(DATA_FILE_PATH);
            List<String> encodedTaskList = new TaskListEncoder().encodeTaskList(tasks);
            for (String encodedTask : encodedTaskList) {
                fw.write(encodedTask + LS);
            }
            fw.close();
        } catch (IOException e) {
           throw new UnableToWriteToFileException();
        }
    }

    /**
     * Load tasks from file into TaskList
     */
    public TaskList loadTasksFromFile() throws CannotReadFromFileException, UnableToWriteToFileException{
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
