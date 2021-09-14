package Duke;

import Duke.Task.TaskManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class DataDetective {

    public String fileLocation = Paths.get(System.getProperty("user.dir"), "data/duke.txt").normalize().toString();

    public static void saveData() {
        try {
            Path filePath = Paths.get("data/duke.txt");
            Files.createDirectories(filePath.getParent());
            saveFile("data/duke.txt");
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public static void loadData() {
        try {
            checkExist("data/duke.txt");
            loadFile("data/duke.txt");
        } catch (IOException e) {
            System.out.println("No save data found");
        }
    }

    public static void checkExist(String fileLocation) throws IOException {
        File f = new File(fileLocation);
        if (!f.exists()) {
            f.createNewFile();
        }
    }

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
                TaskManager.addToDo(description);
                if (lines[2].equals("X")) {
                    TaskManager.thisDone(curr);
                }
                counter++;
                break;
            case "D":
                TaskManager.addDeadline(description);
                if (lines[2].equals("X")) {
                    TaskManager.thisDone(curr);
                }
                counter++;
                break;
            case "E":
                TaskManager.addEvent(description);
                if (lines[2].equals("X")) {
                    TaskManager.thisDone(curr);
                }
                counter++;
                break;
            default:
                System.out.println("Huh?");
                break;
            }
        }
    }

    public static void saveFile(String fileLocation) throws IOException {
        String text;
        File f = new File(fileLocation);
        checkExist(fileLocation);
        FileWriter fw = new FileWriter(fileLocation, false);
        fw.write("");

        for (int i = 0; i < TaskManager.input.List.size(); i++) {
            text = TaskManager.input.List.get(i).getTaskIcon() + "|" + TaskManager.input.List.get(i).getStatusIcon() + "|" + TaskManager.input.List.get(i).getOriginalDescription() + System.lineSeparator();
            writeToFile(fileLocation, text);
        }
    }

    public static void writeToFile(String fileLocation, String whatToWrite) throws IOException {
        FileWriter fw = new FileWriter(fileLocation, true);
        fw.write(whatToWrite);
        fw.close();
    }
}
