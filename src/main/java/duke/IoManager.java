package duke;

import duke.task.TaskManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class IoManager {
    private static final String SAVE_FOLDER = "data";
    private static final String DIRECTORY = System.getProperty("user.dir") + '/' + SAVE_FOLDER;
    private static final String FILE_NAME = DIRECTORY + '/' + "duke.txt";

    public static boolean init(){
        try {
            Path path = Paths.get(DIRECTORY);
            Files.createDirectories(path);
            File myObj = new File(FILE_NAME);
            if (myObj.createNewFile()) {
                System.out.println("Save file created: " + FILE_NAME);
            } else {
                System.out.println("Loading previously created save file.");
                TaskManager.loadTasks();
            }
        } catch (FileAlreadyExistsException faee) {
            String message = String.format("Please remove file '%s' in project directory!)", SAVE_FOLDER);
            Message.printWithSpacers(message);
            return false;
        } catch (IOException ioe) {
            Message.printWithSpacers("IO exception occured during Directory/File creation");
            return false;
        } catch (SecurityException se) {
            Message.printWithSpacers("Program does not have permission to create directory");
            return false;
        }
        return true;
    }

    public static ArrayList<String> loadFile(){
        ArrayList<String> loadedTasks = new ArrayList<>(TaskManager.MAX_TASKS);
        try {
            File file = new File(FILE_NAME);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                loadedTasks.add(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            //TODO Handle Error
        }
        return loadedTasks;
    }

    public static void overwriteFile(String toWrite){
        try{
            FileWriter myWriter = new FileWriter(FILE_NAME);
            myWriter.write(toWrite);
            myWriter.close();
        } catch (IOException ioe){
            //TODO Handle Error
        }
    }
}
