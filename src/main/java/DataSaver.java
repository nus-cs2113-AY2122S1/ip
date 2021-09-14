import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.nio.file.Paths;
import java.io.File;

public class DataSaver {
    protected static final String SPACER = "\\|";

    public static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    public static void checkFileExist(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public static void writeFileContents(String filePath) throws IOException {
        String text;
        File file = new File(filePath);

        checkFileExist(filePath);
        FileWriter fw = new FileWriter(filePath, false);
        fw.write("");

        for (int i = 0; i < TaskHandler.taskCount; i++) {
            Task task = TaskHandler.tasks[i];
            text = task.getType() + " | " + task.getStatus() + " | " +
                    task.getDescription() + " | " + task.getDate() + System.lineSeparator();
            writeToFile(filePath, text);
        }
    }


    public static void loadFileContents(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner s = new Scanner(file);

        while (s.hasNext()) {
            String line = s.nextLine();
            String[] words = line.split(SPACER);
            switch(words[0].trim()) {
            case "T":
                TaskHandler.addTodoFromFile(words);
                break;
            case "D":
                TaskHandler.addDeadlineFromFile(words);
                break;
            case "E":
                TaskHandler.addEventFromFile(words);
                break;
            default:
                System.out.println("nope");

            }
        }
    }
}
