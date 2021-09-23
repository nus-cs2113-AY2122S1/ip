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
     * @param string String to be read from storage to be parsed into a Task.
     * @return Task containing the information stored in string.
     * @throws DukeException if information in data.txt is in the incorrect format.
     */
    private Task readTask(String string) throws DukeException {
        try {
            Task task = null;
            String[] words = string.split(" \\| ");
            switch (words[0]) {
            case "T":
                task = new ToDo(words[2]);
                break;
            case "E":
                task = new Event(words[2], words[3]);
                break;
            case "D":
                LocalDate byDate = LocalDate.ofEpochDay(Long.parseLong(words[3]));
                task = new Deadline(words[2], byDate);
                break;
            default:
                throw new DukeException("Error: Unable to parse data file.");
            }
            if (words[1].equals("1")) {
                task.setCompleted();
            }
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Error: Unable to parse data file.");
        }
    }
}
