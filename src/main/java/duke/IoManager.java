package duke;

import duke.exception.WrongNumberOfArgumentsException;
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

public class IoManager {
    private static final String SAVE_FOLDER = "data";
    private static final String DIRECTORY = System.getProperty("user.dir") + '/' + SAVE_FOLDER;
    private static final String FILE_NAME = DIRECTORY + "/duke.txt";

    private class ErrorMessage{

    }

    public static boolean init() {
        try {
            Path path = Paths.get(DIRECTORY);
            Files.createDirectories(path);
            File saveFile = new File(FILE_NAME);
            if (saveFile.createNewFile()) {
                System.out.println("Save file created: " + FILE_NAME);
            } else {
                System.out.println("Loading previously created save file.");
                TaskManager.loadTasks();
            }
            return true;
        } catch (InvalidPathException ipe) {
            Message.printWithSpacers(String.format("Directory path '%s' is invalid.", DIRECTORY));
        } catch (FileAlreadyExistsException faee) {
            Message.printWithSpacers(String.format("Please remove file '%s' in project directory!)", SAVE_FOLDER));
        } catch (SecurityException se) {
            Message.printWithSpacers("Program does not have permission to create directory/file.");
        } catch (FileNotFoundException fnfe) {
            Message.printWithSpacers(String.format("Program could not find save file in directory %s", DIRECTORY));
        } catch (IOException ioe) {
            Message.printWithSpacers("IO exception occurred during Directory/File creation.");
        } catch (IllegalArgumentException | WrongNumberOfArgumentsException e) {
            Message.printWithSpacers(String.format("Save file '%s' has been corrupted!", FILE_NAME));
        }
        return false;
    }

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

    public static void overwriteFile(String toWrite) {
        try {
            FileWriter writer = new FileWriter(FILE_NAME);
            writer.write(toWrite);
            writer.close();
        } catch (IOException ioe) {
            Message.printWithSpacers("Unable to Write to save file, IO exception encountered.");
        }
    }
}
