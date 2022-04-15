package Duke;

import Duke.Task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * This class loads and saves any tasks in the list
 */
public class Storage {

    public String fileLocation = Paths.get(System.getProperty("user.dir"), "data/duke.txt").normalize().toString();

    /**
     * Create a directory or file if it doesn't exist
     */
    public static void saveData() {
        try {
            Path filePath = Paths.get("data/duke.txt");
            Files.createDirectories(filePath.getParent());
            saveFile("data/duke.txt");
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    /**
     * Checks if the file exists
     * If it does, load the file by adding saved tasks to the list.
     */
    public static void loadData() {
        try {
            checkExist("data/duke.txt");
            loadFile("data/duke.txt");
        } catch (IOException e) {
            System.out.println("No save data found");
        }
    }

    /**
     * Checks to see if there exists a previous save file.
     * Create a new file if it doesn't exist.
     *
     * @param fileLocation The path of the file
     * @throws IOException If the file exists or the system is unable to create a new file
     */
    public static void checkExist(String fileLocation) throws IOException {
        File f = new File(fileLocation);
        if (!f.exists()) {
            f.createNewFile();
        }
    }

    /**
     * Loads the contents of the file by adding them to the list of tasks
     *
     * @param fileLocation Path of the file
     * @throws FileNotFoundException If the file cannot be found
     */
    public static void loadFile(String fileLocation) throws FileNotFoundException {
        File f = new File(fileLocation);
        Scanner s = new Scanner(f);
        int counter = 1;

        while (s.hasNext()) {
            String curr = String.valueOf(counter);
            String next = s.nextLine();
            String[] lines = next.split("|");
            String taskIcon = lines[0];
            String description = next.substring(4);
            switch(taskIcon.trim()) {
            case "T":
                TaskList.addToDoWithoutPrint(description);
                if (lines[2].equals("X")) {
                    TaskList.thisDoneWithoutPrint(curr);
                }
                counter++;
                break;
            case "D":
                TaskList.addDeadlineWithoutPrint(description);
                if (lines[2].equals("X")) {
                    TaskList.thisDoneWithoutPrint(curr);
                }
                counter++;
                break;
            case "E":
                TaskList.addEventWithoutPrint(description);
                if (lines[2].equals("X")) {
                    TaskList.thisDoneWithoutPrint(curr);
                }
                counter++;
                break;
            default:
                System.out.println("Huh?");
                break;
            }
        }
    }

    /**
     * Writes all current Tasks to the save file
     *
     * @param fileLocation The path of the file
     * @throws IOException Throws an error if the file doesn't exist
     */
    public static void saveFile(String fileLocation) throws IOException {
        String text;
        File f = new File(fileLocation);
        checkExist(fileLocation);
        FileWriter fw = new FileWriter(fileLocation, false);
        fw.write("");

        for (int i = 0; i < TaskList.input.List.size(); i++) {
            text = TaskList.input.List.get(i).getTaskIcon() + "|" + TaskList.input.List.get(i).getStatusIcon() + "|" + TaskList.input.List.get(i).getOriginalDescription() + System.lineSeparator();
            writeToFile(fileLocation, text);
        }
    }

    /**
     * Writes to the text file
     *
     * @param fileLocation The path of the file
     * @param whatToWrite The text to be added
     * @throws IOException Throws an error if the file doesn't exist
     */
    public static void writeToFile(String fileLocation, String whatToWrite) throws IOException {
        FileWriter fw = new FileWriter(fileLocation, true);
        fw.write(whatToWrite);
        fw.close();
    }
}
