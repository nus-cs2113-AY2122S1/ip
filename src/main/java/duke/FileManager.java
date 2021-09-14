package duke;

import duke.exception.DirectoryCreationException;
import duke.task.Task;
import duke.task.TaskHelper;
import duke.task.TaskType;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager {

    private static String USER_DIR;
    private static String FILE_PATH;
    private static final String FILE_NAME = "duke.txt";

    private final File saveFile;

    /**
     * Constructor for FileManager, checks and generates save file
     *
     * @throws DirectoryCreationException Throws exception if file directory cannot be created
     */
    public FileManager() throws DirectoryCreationException {
        USER_DIR = System.getProperty("user.dir");
        FILE_PATH = USER_DIR + "/data/" + FILE_NAME;

        saveFile = new File(FILE_PATH);
        checkFileExists(saveFile);
    }

    /**
     * Saves task list to a file
     *
     * @param tasklist
     */
    public void saveTasklistToFile(ArrayList<Task> tasklist) {
        try {
            FileWriter fw = new FileWriter(saveFile);
            for (Task task : tasklist) {
                fw.write(formatTask(task));
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }

    /**
     * reads save file into an array
     *
     * @return ArrayList containing each line from the save file
     */
    public ArrayList<String> readTasklistFromFile() {
        ArrayList<String> fileLines = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(saveFile);

            while (scanner.hasNextLine()) {
                fileLines.add(scanner.nextLine());
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println(e + "Save file not found!");
        }

        return fileLines;
    }

    /**
     * Checks if the given file and its directory exists, creating then if it doesn't.
     *
     * @param file file to check if it exists
     * @throws DirectoryCreationException Throws exception if file directory cannot be created
     */
    private void checkFileExists(File file) throws DirectoryCreationException {
        if (!file.exists()) {
            if (!file.getParentFile().mkdirs()) {
                throw new DirectoryCreationException(file.toString());
            }
        }
    }

    /**
     * @param task task to be formatted to be saved
     * @return
     */
    private String formatTask(Task task) {
        final String SPACER = " | ";
        String saveString = "";

        saveString += task.getType();
        saveString += SPACER;
        saveString += task.isDone() ? '1' : '0';
        saveString += SPACER;
        saveString += task.getDescription();

        boolean isDeadline = task.getType() == TaskType.DEADLINE;
        boolean isEvent = task.getType() == TaskType.EVENT;

        if (isDeadline || isEvent) {
            saveString += SPACER;
            saveString += ((TaskHelper) task).getDeadline();
        }

        saveString += System.lineSeparator();

        return saveString;
    }

}
