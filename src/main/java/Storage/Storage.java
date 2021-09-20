package Storage;

import Tasks.TaskList;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private ArrayList<Task> loadedTaskList = new ArrayList<>();

    public Storage() {
    }

    /*
        This section contains the relevant code for the loading of the stored task file
     */

    /**
     * Checks for the existence of the {@code data} folder, and creates one if it does not exist
     *
     * @return loadedTaskList The Tasks read from the data file
     * @throws FileNotFoundException if the {@code data} folder cannot be found
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        File taskFile = new File("data");
        boolean isDir = Files.isDirectory(Path.of(taskFile.getAbsolutePath()));
        if (!isDir) {
            taskFile.mkdir();
            return loadedTaskList;
        }
        readFile();
        return loadedTaskList;
    }

    /**
     * Reads the contents of the {@code tasks} file
     *
     * @throws FileNotFoundException if the {@code tasks} file does not exist
     */
    public void readFile() throws FileNotFoundException {
        File taskFile = new File("data/tasks.txt");
        Scanner scanner = new Scanner(taskFile);
        if (taskFile.exists() && !taskFile.isDirectory()) {
            while (scanner.hasNext()) {
                processStoredTasks(scanner.nextLine());
            }
        } else {
            return;
        }
    }

    /**
     * Processes the data stored in the task file and stores it in loadedTaskList
     *
     * @param taskString
     */
    public void processStoredTasks(String taskString) {
        String[] taskDetails = taskString.split("\\|", 4);
        boolean isDone = taskDetails[1].trim().equals("X");
        switch (taskDetails[0].trim()) {
        case "T":
            loadedTaskList.add(new Todo(taskDetails[2].trim(), isDone));
            break;
        case "D":
            String[] dueDate = taskDetails[3].split("by:");
            loadedTaskList.add(new Deadline(taskDetails[2].trim(), isDone,
                    LocalDateTime.parse(convertToDateTimeFormat(dueDate[1].trim()))));
            break;
        case "E":
            String[] startDate = taskDetails[3].split("at:");
            loadedTaskList.add(new Event(taskDetails[2].trim(), isDone,
                    LocalDateTime.parse(convertToDateTimeFormat(startDate[1].trim()))));
            break;
        default:
            break;
        }
    }

    public String convertToDateTimeFormat(String dateTime) {
        String day = dateTime.substring(0, 2);
        String month = dateTime.substring(3, 5);
        String year = dateTime.substring(6, 10);
        String hour = dateTime.substring(11, 13);
        String minute = dateTime.substring(14, 16);
        String second = dateTime.substring(17, 19);
        return year + "-" + month + "-" + day + "T" + hour + ":" + minute + ":" + second;
    }
    /*
        This section contains the relevant codes for the writing of the task list to the stored file
     */

    /**
     * Saves the current tasks stored in task list to the text file.
     *
     * @param taskList the arraylist containing the current tasks
     * @throws IOException if error writing to the file
     */
    public static void save(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter("data/tasks.txt");
        for (Task t : taskList.getTaskList()) {
            fw.write(t.toString() + System.lineSeparator());
        }
        fw.close();
    }

}
