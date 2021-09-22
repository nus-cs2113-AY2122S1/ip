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
    public static final int COMMAND_INDEX = 0;
    public static final int IS_DONE_INDEX = 1;
    public static final int DESCRIPTION_INDEX = 2;
    public static final int DATE_INDEX = 3;

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
        String description;
        String date = " ";
        Task current = null;
        while (s.hasNext()) {
            input = s.nextLine();
            inputWords = input.split(SEPARATOR);
            command = inputWords[COMMAND_INDEX];
            description = inputWords[DESCRIPTION_INDEX];
            switch (command) {
            case "T":
                current = new ToDo(description);
                break;
            case "D":
                date = inputWords[DATE_INDEX];
                current = new Deadline(description, date);
                break;
            case "E":
                date = inputWords[DATE_INDEX];
                current = new Event(description, date);
                break;
            default:
                break;
            }
            tasks.add(current);
            taskCount = taskCount + 1;
            if (inputWords[IS_DONE_INDEX].equals("X")) {
                current.setDone();
            }
        }
    }
}
