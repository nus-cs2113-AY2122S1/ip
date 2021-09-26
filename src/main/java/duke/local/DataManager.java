package duke.local;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;

/**
 * Handles reading and writing of the user's tasklist to a local storage file (default: {@code duke.txt}).
 */
public class DataManager {

    /**
     * Represents the file path of the local storage file containing the user's tasklist entries.
     */
    private final String filePath;
    /**
     * Represents the file object of the local storage file pointed by {@code filePath}.
     */
    private final File dataFile;

    private final String FILE_SEPARATOR_REGEX = "\\s\\|\\s";

    private final String TODO_TYPE = "T";
    private final String DEADLINE_TYPE = "D";
    private final String EVENT_TYPE = "E";

    private final String FILE_NOT_FOUND_MSG = "[!] File does not exist! Trying to create duke.txt...";
    private final String FILE_CREATION_SUCCESS_MSG = "[!] duke.txt created successfully...";
    private final String FILE_CREATION_ERROR_MSG = "[!] File cannot be created!";
    private final String FILE_WRITE_ERROR_MSG = "[!] File cannot be written to!";

    /**
     * Constructor that initialises the String {@code filePath} and File {@code dataFile} variable.
     * @param filePath file path pointing to the location of the local storage file containing the user's tasklist.
     */
    public DataManager(String filePath) {
        this.filePath = filePath;
        this.dataFile = new File(filePath);
    }

    /**
     * Gets the user's tasklist entries from the local storage file as an {@code ArrayList<Task>} object.
     * If the local storage file {@code dataFile} cannot be found, it creates a new empty storage file.
     *
     * @return {@code ArrayList<Task>} object of user's tasks retrieved from the local storage file.
     */
    public ArrayList<Task> getLoadedTaskList() {

        ArrayList<Task> tasks = new ArrayList<>();

        try {

            tasks = loadDataIntoTaskList();

        } catch (FileNotFoundException e1) {

            System.out.println(FILE_NOT_FOUND_MSG);
            try {
                createFile();
                System.out.println(FILE_CREATION_SUCCESS_MSG);
            } catch (IOException e2) {
                System.out.println(FILE_CREATION_ERROR_MSG);
            }

        }
        return tasks;
    }

    /**
     * Creates a new local storage file, and it's parent directories.
     *
     * @throws IOException if the file path is unavailable.
     */
    public void createFile() throws IOException {
        Files.createDirectories(Path.of(dataFile.getParent()));
        dataFile.createNewFile();
    }

    /**
     * Loads and returns the user's tasklist entries from the {@code dataFile} into an {@code ArrayList<Task>} object.
     *
     * @return {@code ArrayList<Task>} object containing the tasklist of the user.
     * @throws FileNotFoundException if the {@code dataFile} does not exist.
     */
    public ArrayList<Task> loadDataIntoTaskList() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner in = new Scanner(dataFile);

        while (in.hasNext()) {
            String task = in.nextLine();
            String[] taskArgs = task.split(FILE_SEPARATOR_REGEX, 4);
            String taskType = taskArgs[0];
            boolean isDone = taskArgs[1].equals("1");
            String taskDescription = taskArgs[2];
            String byAtDescription = "";

            if (taskArgs.length == 4) {
                byAtDescription = taskArgs[3].trim();
            }

            switch (taskType) {
            case TODO_TYPE:
                Task newToDo = new ToDo(taskDescription.trim(), isDone);
                tasks.add(newToDo);
                break;
            case DEADLINE_TYPE:
                Task newDeadline = new Deadline(taskDescription.trim(), isDone, byAtDescription);
                tasks.add(newDeadline);
                break;
            case EVENT_TYPE:
                Task newEvent = new Event(taskDescription.trim(), isDone, byAtDescription);
                tasks.add(newEvent);
                break;
            }
        }
        return tasks;
    }

    /**
     * Writes tasklist {@code tasks} into the local storage file {@code dataFile}.
     * @param tasks tasklist to be written to the {@code dataFile}.
     */
    public void writeToFile(ArrayList<Task> tasks) {

        try {
            FileWriter fw = new FileWriter(filePath);

            for (int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.get(i).getTaskFileFormat() + "\n");
            }

            fw.close();
        } catch (IOException e) {
            System.out.println(FILE_WRITE_ERROR_MSG);
        }

    }

}
