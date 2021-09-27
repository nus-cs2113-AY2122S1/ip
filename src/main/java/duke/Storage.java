package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * To load tasks from a text file and to save tasks within the same text file.
 */
public class Storage {

    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * To append a new task to the text file.
     *
     * @param input The array of strings that represent the task that the user wishes to add
     * @throws IOException if there is any issue with the input or output
     */
    public void saveNewTask(String[] input) throws IOException {
        FileWriter fw = new FileWriter(this.filePath, true);
        String fullTaskAsString = "";
        for (String individualString : input) {
            fullTaskAsString += individualString + " ";
        }
        fullTaskAsString += "| 0";
        fw.write(fullTaskAsString + "\n");
        fw.close();
    }

    /**
     * To save all tasks by overwriting the text file.
     *
     * @param commands The ArrayList of all the user's tasks
     * @throws IOException if there is any issue with the input or output
     */
    public void saveAllTasks(ArrayList<Task> commands) throws IOException {
        String filePath = new File(this.filePath).getAbsolutePath();
        FileWriter fw = new FileWriter(filePath, false);
        String taskInFile;
        String done;
        for (Task individualTask : commands) {
            if (individualTask instanceof Deadline) {
                taskInFile = "deadline " + individualTask.description + " /by " + ((Deadline) individualTask).by;
            } else if (individualTask instanceof Event) {
                taskInFile = "event " + individualTask.description + " /at " + ((Event) individualTask).at;
            } else {
                taskInFile = "todo " + individualTask.description;
            }
            if (individualTask.isDone) {
                done = "1";
            } else {
                done = "0";
            }
            String fullTaskAsString = taskInFile + " | " + done + "\n";
            Files.write(Paths.get(filePath), fullTaskAsString.getBytes(), StandardOpenOption.APPEND);
            fw.close();
        }
    }

    /**
     * To load all tasks from the text file.
     *
     * @return ArrayList containing all tasks from the text file
     * @throws IOException if there is any issue with the input or output
     */
    public ArrayList<String> loadTasks() throws IOException {
        String newFilePath = new File(this.filePath).getAbsolutePath();
        ArrayList<String> loadedTasks = new ArrayList<>();
        File f = new File(newFilePath);
        Scanner s = new Scanner(f);
        String textFromFile;
        while (s.hasNext()) {
            textFromFile = s.nextLine();
            loadedTasks.add(textFromFile);
        }
        System.out.println(Ui.STARTING_MESSAGE);
        return loadedTasks;
    }
}
