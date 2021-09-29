import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.nio.file.Paths;
import java.io.File;

/**
 * This class deals with loading from and saving tasks in the file
 */
public class Storage {
    protected static final String SPACER = "\\s\\|\\s";

    /**
     * Writes text to the file.
     *
     * @param filePath Path of the file
     * @param textToAdd Text to be written in the file
     * @throws IOException If the file exists but cannot be opened, or if it does not exist
     */
    public static void writeToFile(String filePath, String textToAdd) throws IOException{
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Checks if the file of the specified path exists.
     * Creates a new file if it does not exist.
     *
     * @param filePath Path of the file
     * @throws IOException If the file already exists or it is unable to create a new empty file
     */
    public static void checkFileExist(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            Files.createFile(file.toPath());
        }
    }

    /**
     * Checks if the directory of the specified path exists.
     * Creates a new directory if it does not exist.
     * @param filePath Path of the file
     * @throws IOException If the directory already exists or it is unable to create a new empty file
     */
    public static void checkDirectoryExist(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            Files.createDirectory(file.toPath());
        }
    }

    /**
     * Writes all Tasks' details to the file.
     *
     * @param filePath Path of the file
     * @throws IOException If the file exists but cannot be opened, or if it does not exist
     */
    public static void writeFileContents(String filePath) throws IOException{
        String text;
        File file = new File(filePath);

        FileWriter fw = new FileWriter(filePath, false);
        fw.write("");

        for (Task task : TaskList.tasks) {
            text = task.getType() + " | " + task.getStatus() + " | " +
                    task.getDescription() + " | " + task.getDate() + System.lineSeparator();
            writeToFile(filePath, text);
        }
    }

    /**
     * Loads file contents and adds saved Tasks to Task list.
     *
     * @param filePath Path of the file
     * @throws IOException If the file to be written on is not found
     */
    public static void loadFileContents(String filePath) throws IOException {
        File file = new File(filePath);
        Scanner s = new Scanner(file);

        while (s.hasNext()) {
            String line = s.nextLine();
            switch(Parser.parseCommandFromFile(line)) {
            case "T":
                addTodoFromFile(line);
                break;
            case "D":
                addDeadlineFromFile(line);
                break;
            case "E":
                addEventFromFile(line);
                break;
            }
        }
    }

    /**
     * Loads file contents and adds saved tasks to Task list.
     * Before doing so, checks if directory and file to load data from exists.
     * If there is no directory or file, creates one.
     */
    public static void loadData() {
        try {
            checkDirectoryExist("data");
            checkFileExist("data/duke.txt");
            loadFileContents("data/duke.txt");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Writes (saves) tasks from Task list to the file
     * Before doing so, checks if directory and file to save data exists.
     * If there is no directory or file, creates one.
     */
    public static void saveData() {
        try {
            checkDirectoryExist("data");
            checkFileExist("data/duke.txt");
            writeFileContents("data/duke.txt");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds Todo task to the list by reading from the file.
     * @param line A String (one line) from the file which contains the saved Todo's details
     */
    public static void addTodoFromFile(String line) {
        TaskList.addTodo(Parser.parseDescriptionFromFile(line));
        if (Parser.parseIsDoneFromFile(line).equals(TaskHandler.DONE_INDICATOR)) {
            TaskList.getTask(TaskList.tasks.size() - 1).markAsDoneWithoutMessage();
        }
    }

    /**
     * Adds Event task to the list by reading from the file.
     * @param line A String (one line) from the file which contains the saved Event's details
     */
    public static void addEventFromFile(String line) {
        TaskList.addEvent(Parser.parseDescriptionFromFile(line), Parser.parseDateFromFile(line));
        if (Parser.parseIsDoneFromFile(line).equals(TaskHandler.DONE_INDICATOR)) {
            TaskList.getTask(TaskList.tasks.size() - 1).markAsDoneWithoutMessage();
        }
    }

    /**
     * Adds Deadline task to the list by reading from the file.
     * @param line A String (one line) from the file which contains the saved Deadline's details
     */
    public static void addDeadlineFromFile(String line) {
        TaskList.addDeadline(Parser.parseDescriptionFromFile(line), Parser.parseDateFromFile(line));
        if (Parser.parseIsDoneFromFile(line).equals(TaskHandler.DONE_INDICATOR)) {
            TaskList.getTask(TaskList.tasks.size() - 1).markAsDoneWithoutMessage();
        }
    }
}
