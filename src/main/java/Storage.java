import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Storage {

    public static final String FILE_PATH = getFilePath();

    public static String getFilePath() {
        String workingDirectory = System.getProperty("user.dir");
        Path rawFilePath = Paths.get(workingDirectory + File.separator + "duke.txt");
        String filePath = String.valueOf(rawFilePath);
        return filePath;
    }

    public static void readFile(ArrayList<Task> tasks) throws FileNotFoundException {
        File file = new File(FILE_PATH);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String fileLine = scanner.nextLine();
            String[] parsedFileLine = Parser.parseFileLine(fileLine);
            Task newTask = TaskList.createTask(parsedFileLine);
            tasks.add(newTask);
            String[] splitFileLine = fileLine.split(" ");
            String isDone = splitFileLine[2];
            if (isDone.equals("1")) {
                tasks.get(tasks.size() - 1).setDone();
            }
        }
    }

    public static void createNewFile() {
        File file = new File(FILE_PATH);
        try {
            file.createNewFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static ArrayList<Task> loadFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            readFile(tasks);
        } catch (FileNotFoundException e) {
            createNewFile();
        }
        return tasks;
    }

    public static String convertToFileLine (String[] parsedUserInput) {
        String fileLine = "";
        String fileLineCategory = "?";
        String fileLineDescription = parsedUserInput[1];
        String category = parsedUserInput[0];
        switch (category) {
        case "todo":
            fileLineCategory = "T";
            fileLine = fileLineCategory + " | 0 | " + fileLineDescription;
            break;
        case "deadline":
            fileLineCategory = "D";
            fileLine = fileLineCategory + " | 0 | " + fileLineDescription;
            String fileLineDeadlineBy = parsedUserInput[2];
            fileLine = fileLine + " | " + fileLineDeadlineBy;
            break;
        case "event":
            fileLineCategory = "E";
            fileLine = fileLineCategory + " | 0 | " + fileLineDescription;
            String fileLineEventAt = parsedUserInput[2];
            fileLine = fileLine + " | " + fileLineEventAt;
            break;
        default:
            break;
        }
        return fileLine;
    }

    public static void saveTaskInFile(String[] parsedUserInput) {
        String convertedUserInput = convertToFileLine(parsedUserInput);
        try {
            String filePath = Storage.getFilePath();
            FileWriter fileWriter = new FileWriter(filePath, true);
            fileWriter.write(convertedUserInput);
            fileWriter.write("\r\n");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getUpdatedDoneFile(int taskNumber) throws FileNotFoundException {
        File file = new File(FILE_PATH);
        Scanner scanner = new Scanner(file);
        String updatedDoneFile = "";
        int lineNumber = 0;
        while (scanner.hasNext()) {
            if (lineNumber == taskNumber) {
                String oldFileLine = scanner.nextLine();
                String newFileLine = oldFileLine.substring(0, 4) + "1" + oldFileLine.substring(5);
                updatedDoneFile = updatedDoneFile + newFileLine + "\n";
            } else {
                updatedDoneFile = updatedDoneFile + scanner.nextLine() + "\n";
            }
            lineNumber++;
        }
        return updatedDoneFile;
    }

    public static void updateDoneTaskInFile(int taskNumber) {
        try{
            String updatedFile = getUpdatedDoneFile(taskNumber);
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            fileWriter.write(updatedFile);
            fileWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getUpdatedDeleteFile(int taskNumber) throws FileNotFoundException {
        File file = new File(FILE_PATH);
        Scanner scanner = new Scanner(file);
        String updatedDeleteFile = "";
        int lineNumber = 0;
        while (scanner.hasNext()) {
            if (lineNumber == taskNumber) {
                scanner.nextLine();
            } else {
                updatedDeleteFile = updatedDeleteFile + scanner.nextLine() + "\n";
            }
            lineNumber++;
        }
        return updatedDeleteFile;
    }

    public static void updateDeleteTaskInFile(int taskNumber) {
        try{
            String updatedFile = getUpdatedDeleteFile(taskNumber);
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            fileWriter.write(updatedFile);
            fileWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
