package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.time.LocalDate;

public class Storage {
    private final String pathName = "data/data.txt";
    private final File file = new File(pathName);

    /**
     * Constructor for Storage class. Attempts to access data file,
     * creates a new data file if it is not found.
     *
     * @throws DukeException if unable to create or load data file
     */
    public Storage() throws DukeException {
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("Error: Unable to load data.");
        }
    }

    /**
     * Loads data from data file into taskList.
     *
     * @param taskList TaskList where loaded data will be stored in.
     * @throws DukeException if unable to open data.txt file
     */
    public void loadData(TaskList taskList) throws DukeException {
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNext()) {
                Task task = readTask(scanner.nextLine());
                taskList.addTask(task);
            }
        } catch (FileNotFoundException | DukeException e) {
            taskList.deleteAllTasks();
            saveData(taskList);
            throw new DukeException(e.getMessage() + " New data file created.");
        }
    }

    /**
     * Reads task information from taskList and stores it into data/data.txt file.
     *
     * @param taskList the TaskList to be stored.
     * @throws DukeException if unable to save data.
     */
    public void saveData(TaskList taskList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(pathName);
            fw.write(taskList.getStorageString());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Error: Unable to save data.");
        }
    }

    /**
     * Creates a Task containing the information specified in string.
     *
     * @param storageLine String corresponding to a line that was read from storage file to be parsed into a Task.
     * @return Task containing the information stored in string.
     * @throws DukeException if information in data.txt is in the incorrect format.
     */
    private Task readTask(String storageLine) throws DukeException {
        try {
            String[] words = splitWords(storageLine);
            Task task = parseTask(words);
            markCompleted(words, task);
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Error: Unable to parse data file.");
        }
    }

    /**
     * Splits the given string using on the | character as a separator
     *
     * @param storageLine line that was read from storage file to be parsed into a Task
     * @return Array of Strings after storageLine was split
     */
    private static String[] splitWords(String storageLine) {
        String[] words = storageLine.split(" \\| ");
        return words;
    }

    /**
     * Returns a Task corresponding to the values stored in words.
     *
     * @param words String array containing the information of the Task
     * @return Task corresponding to the information in words
     * @throws DukeException             if words contains invalid values
     * @throws IndexOutOfBoundsException if words is missing some Task information
     */
    private static Task parseTask(String[] words) throws DukeException, IndexOutOfBoundsException {
        switch (words[0]) {
        case "T":
            return new ToDo(words[2]);
        case "E":
            return new Event(words[2], words[3]);
        case "D":
            LocalDate byDate = LocalDate.ofEpochDay(Long.parseLong(words[3]));
            return new Deadline(words[2], byDate);
        default:
            throw new DukeException("Error: Unable to parse data file.");
        }
    }

    /**
     * Sets the given Task as completed based on the values stored in words.
     *
     * @param words String array containing the information of the Task
     * @param task  Task to be marked as read if indicated in words
     * @throws IndexOutOfBoundsException if words is missing some Task information
     */
    private static void markCompleted(String[] words, Task task) throws IndexOutOfBoundsException {
        if (words[1].equals("1")) {
            task.setCompleted();
        }
    }
}
