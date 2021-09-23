import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {
    public static final Path dataPath = Paths.get("DukeData/data.txt");
    private static final Path dukeDirPath = Paths.get("DukeData");
    private static final File dukeDir = new File(dukeDirPath.toString());
    private static final File dataFile = new File(dataPath.toString());

    public static void initializeStorage() throws IOException {
        if (dukeDir.mkdir()) {
            Ui.printlnTab("Created " + dukeDirPath + " directory!");
        }
        if (dataFile.createNewFile()) {
            Ui.printlnTab("Created " + dataPath + " storage file!");
        }

        try {
            readFile();
        } catch (FileNotFoundException e) {
            Ui.printlnTab(dataPath + " not found!");
            Ui.printDivider();
        }
    }

    private static void readFile() throws FileNotFoundException {
        Ui.printlnTab("Reading " + dataPath + " file...");
        Ui.printlnTab("");

        boolean hasError = false;
        Scanner s = new Scanner(dataFile);
        int lineNumber = 1;
        while (s.hasNext()) {
            try {
                parseData(s.nextLine());

            } catch (Exception e) {
                Ui.printlnTab("☹ OOPS!!! Line " + lineNumber + " is invalid! Skipping to next line...");
                hasError = true;
            }

            lineNumber++;
        }
        Ui.printDivider();
        if (hasError) {
            Ui.printlnTab("1. Enter 'bye' to exit program to correct data file " + dataPath);
            Ui.printlnTab("2. add, do or delete tasks to OVERWRITE all invalid data!");
            Ui.printDivider();
        }
    }

    private static void parseData(String line) throws InvalidIntegerException {
        if (line.isBlank()) {
            return;
        }
        String[] taskDetails = line.split(" \\| ");

        String taskLetter = taskDetails[0];

        String isDoneString = taskDetails[1];
        int isDoneInt = Integer.parseInt(isDoneString);
        if (isDoneInt != Integer.parseInt("1") && isDoneInt != Integer.parseInt("0")) {
            throw new InvalidIntegerException();
        }
        boolean isDone = (isDoneInt == 1);

        String description = taskDetails[2];
        String date;

        switch (taskLetter) {
        case "T":
            Parser.taskList.addTodo(description, isDone);
            break;

        case "D":
            date = taskDetails[3];
            String[] deadlineDetails = {description, date};

            Parser.taskList.addDeadline(deadlineDetails, isDone);
            break;

        case "E":
            date = taskDetails[3];
            String[] eventDetails = {description, date};
            Parser.taskList.addEvent(eventDetails, isDone);
            break;
        default:
            throw new IllegalStateException();
        }
    }

    //rewrite entire data.txt file with latest version of tasks list
    public static void writeToFile() throws IOException {
        FileWriter fw = new FileWriter(dataFile);
        fw.write(Parser.taskList.getTasksDataStorageString());
        fw.close();
    }

}
