package duke;

import duke.exception.LoadTaskException;
import duke.task.TaskManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class for handling all file operations
 *
 * <code>SAVE_FOLDER</code> corresponds to the directory to save the save file.
 * <code>FILE_NAME</code> corresponds to the name of te save file.
 */
public class IoManager {
    private static final String SAVE_FOLDER = "data";
    private static final String DIRECTORY = System.getProperty("user.dir") + '/' + SAVE_FOLDER;
    private static final String FILE_NAME = DIRECTORY + "/duke.txt";

    public static final String SAVE_FILE_CREATED_MESSAGE = "Save file created: " + FILE_NAME;
    public static final String LOAD_CREATED_SAVE_FILE_MESSAGE = "Loading previously created save file.";
    public static final String INVALID_DIRECTORY_PATH_MESSAGE = String.format("Directory path '%s' is invalid.", DIRECTORY);
    public static final String FILE_NAME_ALREADY_USED_MESSAGE = String.format("Please remove file '%s' in project directory!)", SAVE_FOLDER);
    public static final String NO_PERMISSION_MESSAGE = "Program does not have permission to create directory/file.";
    public static final String IO_EXCEPTION_MESSAGE = "IO exception occurred during Directory/File creation.";
    public static final String SAVE_FILE_CORRUPTED_MESSAGE = String.format("Save file '%s' has been corrupted!", FILE_NAME);
    public static final String OVERWRITE_IO_EXCEPTION_MESSAGE = "Unable to Write to save file, IO exception encountered.";

    /**
     * Initialises the save file.
     * if a new save file needs to be created, create one and inform the user,
     * else it attempts to load the save file.
     */
    public static boolean initialiseSaveFile() {
        try {
            Path path = Paths.get(DIRECTORY);
            Files.createDirectories(path);
            File saveFile = new File(FILE_NAME);
            if (saveFile.createNewFile()) {
                System.out.println(SAVE_FILE_CREATED_MESSAGE);
            } else {
                System.out.println(LOAD_CREATED_SAVE_FILE_MESSAGE);
                TaskManager.loadTasks();
            }
            return true;
        } catch (InvalidPathException ipe) {
            Message.printWithSpacers(INVALID_DIRECTORY_PATH_MESSAGE);
        } catch (FileAlreadyExistsException faee) {
            Message.printWithSpacers(FILE_NAME_ALREADY_USED_MESSAGE);
        } catch (SecurityException se) {
            Message.printWithSpacers(NO_PERMISSION_MESSAGE);
        } catch (IOException ioe) {
            Message.printWithSpacers(IO_EXCEPTION_MESSAGE);
        } catch (IllegalArgumentException | LoadTaskException e) {
            Message.printWithSpacers(SAVE_FILE_CORRUPTED_MESSAGE);
        }
        return false;
    }

    /**
     * Creates a String ArrayList and read the lines from the scanner into the ArrayList.
     *
     * @return All lines read from Scanner in an ArrayList.
     * @throws FileNotFoundException If Scanner was unable to find save file (should not happen because we just created it)
     */
    public static ArrayList<String> loadFile() throws FileNotFoundException {
        ArrayList<String> loadedTasks = new ArrayList<>(TaskManager.MAX_TASKS);
        File file = new File(FILE_NAME);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            loadedTasks.add(scanner.nextLine());
        }
        scanner.close();
        return loadedTasks;
    }

    /**
     * Overwrites the contents of the save file (does not append, overwrites everything!)
     *
     * @param toWrite the whole String that would be used to overwrite the current contents in the save file
     */
    public static void overwriteFile(String toWrite) {
        try {
            FileWriter writer = new FileWriter(FILE_NAME);
            writer.write(toWrite);
            writer.close();
        } catch (IOException ioe) {
            Message.printWithSpacers(OVERWRITE_IO_EXCEPTION_MESSAGE);
        }
    }
}
