package duke;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Manages the data storage for tasks. Creates, adds to, edits or deletes
 * from the data in the external file.
 */
public class Storage {
    /** Path to the external file */
    private Path dataPath;

    public Storage() {
        openFile();
    }

    /**
     * Attempts to open the external file that is to hold data. Creates such
     * a file if it does not exist.
     */
    public void openFile() {
        try {
            String home = System.getProperty("user.dir");
            Path dataDirPath = Paths.get(home, "/data/");
            Files.createDirectories(dataDirPath);
            Path dataPath = Paths.get(home, "/data/duke.txt");
            if (!Files.exists(dataPath)) {
                Files.createFile(dataPath);
            }
            this.dataPath = dataPath;
        } catch (IOException e) {
            System.err.println("Open failure: " + e.getMessage());
        }
    }

    /**
     * Loads the stored data into an ArrayList of Tasks.
     *
     * @return an ArrayList of Tasks if there is data;
     *         an empty ArrayList otherwise
     */
    public ArrayList<Task> load() {
        List<String> lines = this.getAllLines();
        ArrayList<Task> tasks = new ArrayList<>();
        if (lines.isEmpty()) {
            return tasks;
        }
        for (String line : lines) {
            Task task = convertFileLineToTask(line);
            tasks.add(task);
        }
        return tasks;
    }

    /**
     * Converts a line of text in the file into a Task.
     * @param line the line of text in the file to be converted; should be in
     *             the format `task type | done | description | detail`
     * @return the converted Task
     */
    public Task convertFileLineToTask(String line) {
        String[] taskComponents = line.split("\\|");
        for (String component : taskComponents) {
            component.strip();
        }
        String taskType = taskComponents[0];
        String done = taskComponents[1];
        String description = taskComponents[2];
        String detail = taskComponents[3];

        Task task;
        boolean isDone = done.equals("X");

        switch (taskType) {
            case "D":
                task = new Deadline(description, detail, isDone);
                break;
            case "E":
                task = new Event(description, detail, isDone);
                break;
            default:
                task = new Todo(description, isDone);
        }
        return task;
    }

    /**
     * Reads all the data in the file and returns it as a List<String>.
     * @return a List<String> of all the data in the file
     */
    private List<String> getAllLines() {
        List<String> lines = List.of();
        try {
            lines = Files.readAllLines(dataPath);
        } catch (IOException e) {
            System.err.println("Read failure: " + e.getMessage());
        }
        return lines;
    }

    /**
     * Adds the details of a Task to the external file.
     * @param task the Task whose details are to be added
     */
    public void addTaskToFileData(Task task) {
        appendLineToFileData(task.taskString());
    }

    /**
     * Appends the given line to the external file.
     * @param line the line to be appended
     */
    public void appendLineToFileData(String line) {
        try {
            List<String> lines = getAllLines();
            lines.add(line);
            Files.write(dataPath, lines);
        } catch (IOException e) {
            System.err.println("Write failure: " + e.getMessage());
        }
    }

    /**
     * Returns the line at the given index.
     * @param index the index of the line to be returned
     * @return the line at the given index
     */
    public String getLine(int index) {
        List<String> lines = getAllLines();
        String output = "";
        try {
            output = lines.get(index);
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Index out of bounds failure: " + e.getMessage());
        }

        return output;
    }

    /**
     * Sets a Task as done.
     * @param id the (id - 1) of the task, corresponding to the line to edit
     */
    public void setDone(int id) {
        String newFileDataLine = getLine(id);
        char[] newFileDataLineChars = newFileDataLine.toCharArray();
        newFileDataLineChars[3] = 'X';
        replaceFileData(id, String.valueOf(newFileDataLineChars));
    }

    /**
     * Replaces a line in the external file with the given line.
     * @param index the index of the line to be replaced
     * @param line the line to replace
     */
    public void replaceFileData(int index, String line) {
        try {
            List<String> lines = getAllLines();
            lines.set(index, line);
            Files.write(dataPath, lines);
        } catch (IOException e) {
            System.err.println("Write failure: " + e.getMessage());
        }
    }

    /**
     * Deletes a line at the given index from the external file.
     * @param index the index of the line to be deleted
     */
    public void deleteLineFromFileData(int index) {
        try {
            List<String> lines = getAllLines();
            lines.remove(index);
            Files.write(dataPath, lines);
        } catch (IOException e) {
            System.err.println("Write failure: " + e.getMessage());
        }
    }

    /**
     * Deletes all the data in the file.
     */
    public void clearFileData() {
        try {
            Files.write(dataPath, Collections.EMPTY_LIST);
        } catch (IOException e) {
            System.err.println("Write failure: " + e.getMessage());
        }
    }
}
