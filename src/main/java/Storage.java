import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.nio.file.Paths;
import java.io.File;

public class Storage {
    protected static final String SPACER = "\\s\\|\\s";

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

        for (Task task : TaskList.tasks) {
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

    public static void loadData() {
        try {
            File file = new File("data/duke.txt");
            if (!file.exists()) {
                Files.createFile(file.toPath());
            }
            loadFileContents("data/duke.txt");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void saveData() {
        try {
            Path dir = Paths.get("data/duke.txt");
            Files.createDirectories(dir.getParent());
            writeFileContents("data/duke.txt");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void addTodoFromFile(String line) {
        TaskList.addTodo(Parser.parseDescriptionFromFile(line));
        if (Parser.parseIsDoneFromFile(line).equals(TaskHandler.DONE_INDICATOR)) {
            TaskList.getTask(TaskList.tasks.size() - 1).markAsDoneWithoutMessage();
        }
    }

    public static void addEventFromFile(String line) {
        TaskList.addEvent(Parser.parseDescriptionFromFile(line), Parser.parseDateFromFile(line));
        if (Parser.parseIsDoneFromFile(line).equals(TaskHandler.DONE_INDICATOR)) {
            TaskList.getTask(TaskList.tasks.size() - 1).markAsDoneWithoutMessage();
        }
    }

    public static void addDeadlineFromFile(String line) {
        TaskList.addDeadline(Parser.parseDescriptionFromFile(line), Parser.parseDateFromFile(line));
        if (Parser.parseIsDoneFromFile(line).equals(TaskHandler.DONE_INDICATOR)) {
            TaskList.getTask(TaskList.tasks.size() - 1).markAsDoneWithoutMessage();
        }
    }
}
