package duke.storage;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import static duke.ui.Ui.NL;

/**
 * Loads and stores data into a data file inside a specific data directory
 */
public class Storage {
    private final File dataDirectory;
    private final File dataFile;

    /**
     * @param dataDirectory Directory where the data file is to be inside
     * @param dataFile      File which contains the stored data
     */
    public Storage(File dataDirectory, File dataFile) {
        this.dataDirectory = dataDirectory;
        this.dataFile = dataFile;
    }

    /**
     * loads all tasks found in the data file into an ArrayList<Task>
     *
     * @return ArrayList<Task> containing all the tasks loaded from the data file
     * @throws FileNotFoundException when no data file exists
     * @throws SecurityException     when data directory not found and cannot be created using mkdirs
     */
    public ArrayList<Task> load() throws FileNotFoundException,
            SecurityException {
        if (!dataDirectory.isDirectory() && !dataDirectory.mkdirs()) {
            throw new SecurityException();
        }
        Scanner loadingScanner = new Scanner(dataFile);
        ArrayList<Task> loadedData = new ArrayList<>();
        while (loadingScanner.hasNext()) {
            String nextLine = loadingScanner.nextLine();
            Task loadedTask = extractTask(nextLine);
            if (loadedTask != null) {
                loadedData.add(loadedTask);
            }
        }
        return loadedData;
    }

    /**
     * takes in a task and adds it to the data file
     *
     * @param task          Task to be added to the data file
     * @param numberOfTasks number of tasks in the data file after adding the task
     * @throws IOException when I/O error occurs
     */
    public void writeToData(Task task, int numberOfTasks) throws IOException {
        FileWriter fw = new FileWriter(dataFile, true);
        fw.write(((numberOfTasks > 1) ? NL : "") + task.toData());
        fw.close();
    }

    /**
     * Overwrites all the data in the data file with the tasks in the tasks ArrayList
     *
     * @param tasks ArrayList with all the tasks to be written into the data file
     * @throws IOException when I/O error occurs
     */
    public void refreshData(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(dataFile, false);
        for (int i = 0; i < tasks.size(); i++) {
            fw.write(tasks.get(i).toData() + ((i >= tasks.size() - 1) ? "" : NL));
        }
        fw.close();
    }

    /**
     * takes in input from the data file and extracts the data into Task objects
     *
     * @param input input from data file
     * @return Task object corresponding ot the data file input, null if Task is not recognised
     */
    private Task extractTask(String input) {
        String[] taskDetails = input.split("\\|");
        boolean isDone;
        switch (taskDetails[0].trim()) {
        case "T":
            isDone = taskDetails[1].trim().equalsIgnoreCase("1");
            return new Todo(taskDetails[2].trim(), isDone);
        case "D":
            isDone = taskDetails[1].trim().equalsIgnoreCase("1");
            try {
                LocalDateTime byDT = LocalDateTime.parse(taskDetails[3].trim(),
                        DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
                return new Deadline(taskDetails[1].trim(), byDT, isDone);
            } catch (DateTimeParseException dtpe) {
                return new Deadline(taskDetails[2].trim(), taskDetails[3].trim(), isDone);
            }
        case "E":
            isDone = taskDetails[1].trim().equalsIgnoreCase("1");
            try {
                LocalDateTime atDT = LocalDateTime.parse(taskDetails[3].trim(),
                        DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
                return new Event(taskDetails[1].trim(), atDT, isDone);
            } catch (DateTimeParseException dtpe) {
                return new Event(taskDetails[2].trim(), taskDetails[3].trim(), isDone);
            }
        default:
            return null;
        }
    }
}
