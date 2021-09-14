package duke;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class InputFile extends TaskManager{
    private static final String FILE_PATH = "input.txt";
    private static final String SEPARATOR = "#";

    public static boolean hasInput() {
        File input = new File(FILE_PATH);
        return input.exists();
    }

    public static void createInput() throws IOException {
        File input = new File(FILE_PATH);
        input.createNewFile();
    }

    public static void deleteInput() {
        File input = new File(FILE_PATH);
        input.delete();
    }

    public static void writeToInput() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        String type;
        for (Task current : tasks) {
            type = current.getIcon();
            switch (type) {
                case "T":
                    fw.write(current.getIcon()
                            + SEPARATOR
                            + current.getStatusIcon()
                            + SEPARATOR
                            + current.getDescription()
                            + "\n");
                    break;
                case "D":
                case "E":
                    fw.write(current.getIcon()
                            + SEPARATOR
                            + current.getStatusIcon()
                            + SEPARATOR
                            + current.getDescription()
                            + SEPARATOR
                            + current.getDate()
                            + "\n");
                    break;
                default:
                    break;
            }
        }
        fw.close();
    }

    public static void readInput() throws FileNotFoundException {
        File f = new File(FILE_PATH); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        String input;
        String[] inputWords;
        String command;
        boolean doneStatus = false;
        String description;
        String date = " ";
        Task current = null;
        while (s.hasNext()) {
            input = s.nextLine();
            inputWords = input.split(SEPARATOR);
            command = inputWords[0];
            if (inputWords[1].equals("X")) {
                doneStatus = true;
            }
            description = inputWords[2];
            if (inputWords.length == 4) {
                date = inputWords[3];
            }
            switch (command) {
            case "T":
                current = new ToDo(description);
                break;
            case "D":
                current = new Deadline(description, date);
                break;
            case "E":
                current = new Event(description, date);
                break;
            default:
                break;
            }
            tasks.add(current);
            taskCount = taskCount + 1;
            if (doneStatus) {
                current.setDone();
            }
        }
    }
}
