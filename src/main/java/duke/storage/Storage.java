package duke.storage;

import static duke.parser.Parser.parseFormattedTaskString;
import static duke.ui.ErrorMessage.FILE_IO_ERROR_MESSAGE;

import duke.exception.DukeException;
import duke.task.Task;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {

    private final String taskTxtFilePath;

    public Storage(String filePath) {
        this.taskTxtFilePath = filePath;
    }

    /**
     * Load all task data from ./data/task.txt to task manager
     */
    public ArrayList<Task> loadData() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            File taskFile = new File(taskTxtFilePath);
            if (taskFile.exists()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(taskFile));
                String formattedTaskString;
                while ((formattedTaskString = bufferedReader.readLine()) != null) {
                    Task task = parseFormattedTaskString(formattedTaskString);
                    tasks.add(task);
                }
            }

        } catch (IOException e) {
            throw new DukeException(FILE_IO_ERROR_MESSAGE);
        }
        return tasks;
    }

    /**
     * Save all task data to ./data/tasks.txt
     *
     * @param taskList Current list of task in the program
     */
    public void saveData(ArrayList<Task> taskList) throws IOException{
        try {
            File taskFileDirectory = new File("data");
            File taskFile = new File("data" + File.separator + "tasks.txt");
            // Create directory and file
            if (!taskFileDirectory.exists()) {
                taskFileDirectory.mkdir();
            }
            if (!taskFile.exists()) {
                taskFile.createNewFile();
            }
            FileWriter myWriter = new FileWriter("data" + File.separator + "tasks.txt");
            for (Task task : taskList) {
                myWriter.write(task.toSave() + "\n");
            }
            myWriter.close();

        } catch (IOException e) {
            throw new IOException(FILE_IO_ERROR_MESSAGE);
        }

    }
}
