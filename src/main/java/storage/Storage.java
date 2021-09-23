package storage;

import task.TaskList;
import task.Task;
import task.Todo;
import task.Event;
import task.Deadline;
import ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {
    private static final String FILE_PATH = "data/austin.txt";
    private static final String SEPARATOR = " # ";

    /**
     * Opens the text file for reading if it exists. If the file does not exist in
     * the directory, a new file is created. A new directory is created if the specified
     * directory is not available.
     * @return The file which is used to store tasks
     * @throws IOException If there is an error due to file management
     */
    private File loadFile() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            File dir = new File("data");
            dir.mkdir();
            File newFile = new File("data/austin.txt");
            newFile.createNewFile();
            return newFile;
        }
        return file;
    }

    /**
     * Calls a private method to load tasks from text file to the array list.
     * @return The arraylist of Task objects stored in the file
     * @throws IOException If there is a problem in opening or
     * creating the file
     */
    public ArrayList<Task> load() throws IOException,
            DateTimeParseException {
        return addTasksFromFile();
    }

    /**
     * Adds tasks stored in the file into the array.
     * @return The arraylist of Task objects stored in the file
     * @throws IOException If there is a problem in opening or
     * creating the file
     */
    private ArrayList<Task> addTasksFromFile() throws IOException,
            DateTimeParseException {
        File file = loadFile();
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] details = line.split(SEPARATOR);
            switch (details[0]) {
            case "T":
                tasks.add(new Todo(details[2]));
                break;
            case "D":
                tasks.add(new Deadline(details[2], LocalDateTime.parse(details[3])));
                break;
            case "E":
                tasks.add(new Event(details[2], LocalDateTime.parse(details[3])));
                break;
            default:
                break;
            }
            if (details[1].equals("1")) {
                tasks.get(tasks.size() - 1).setDone(true);
            }
        }
        return tasks;
    }

    /**
     * Updates the text file when called.
     * @throws IOException If there is an error while accessing the text file
     * @param taskList List of Task objects
     */
    public void updateFile(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH, false);
        for (int i = 0; i < taskList.tasksCount(); i++) {
            fw.write(taskList.getTaskItemInFileFormat(i));
        }
        fw.close();
    }

    /**
     * Clears the contents of the text file.
     * @throws IOException If there is an error while accessing the text file
     */
    public void clearFileContents() throws IOException {
        FileChannel.open(Paths.get(FILE_PATH), StandardOpenOption.WRITE)
                .truncate(0).close();
    }
}
