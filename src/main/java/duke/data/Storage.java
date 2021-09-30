package duke.data;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {

    private static final String PATH_NAME = "data/output.txt";
    private static final String FILE_CREATION_ERROR = "Error in creating file";
    private static final String SEPARATOR = " | ";

    /**
     * Creates file inside its directory.
     */
    public static void createFile(){
        try {
            Path path = Paths.get(PATH_NAME);
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            System.out.println(FILE_CREATION_ERROR);
        }
    }

    /**
     * Reads content from file and adds into TaskList.
     *
     * @param filePath Path where file is read from.
     * @throws FileNotFoundException If file not found.
     */
    public static void readFromFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] details = line.split("\\|");
            String taskType = details[0].trim();
            boolean isDone = details[1].trim().equals("1");
            String description = details[2].trim();
            String timing;
            switch (taskType) {
            case "T":
                TaskManager.loadToDoFromFile(description, isDone);
                break;
            case "D":
                timing = details[3].trim();
                TaskManager.loadDeadlineFromFile(description, timing, isDone);
                break;
            case "E":
                timing = details[3].trim();
                TaskManager.loadEventFromFile(description, timing, isDone);
                break;
            }
        }
    }

    /**
     * Appends to end of file.
     *
     * @param filePath Path to append file to.
     * @param textToAppend Text to be appended.
     * @throws IOException If an I/O exception has occurred.
     */
    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Writes to file.
     *
     * @param filePath Path to write file to.
     * @throws IOException If an I/O exception has occurred.
     */
    public static void writeToFile(String filePath) throws IOException {
        String textToAppend;
        FileWriter fw = new FileWriter(filePath, false);
        fw.write(""); //clear the file
        fw.close();
        for (Task task: TaskManager.taskList) {
            String taskType = task.getIcon();
            String status = task.getStatus();
            String description = task.getDescription();
            String timing = task.getTime();

            textToAppend = taskType + SEPARATOR + status + SEPARATOR + description;
            if (task instanceof Event || task instanceof Deadline) {
                textToAppend += SEPARATOR + timing;
            }
            textToAppend += "\n";
            appendToFile(filePath, textToAppend);
        }
    }

    /**
     * Saves data into file.
     */
    public static void saveData() {
        try {
            Path path = Paths.get(PATH_NAME);
            Files.createDirectories(path.getParent());
            writeToFile(PATH_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads data from file.
     */
    public static void loadData() {
        try {
            readFromFile(PATH_NAME);
        } catch (FileNotFoundException e) {
            createFile();
        }
    }
}
