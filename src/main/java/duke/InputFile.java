package duke;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.validation.InvalidFileException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *  The InputFile class implements a save functionality to the Duke program.
 *  A text file is read/written when the program open/closes.
 */
public class InputFile extends TaskManager{
    private static final String FILE_PATH = "input.txt";
    private static final String SEPARATOR = "#";
    public static final int COMMAND_INDEX = 0;
    public static final int IS_DONE_INDEX = 1;
    public static final int DESCRIPTION_INDEX = 2;
    public static final int DATE_INDEX = 3;

    /**
     * Checks if there is a save file and reads it before deleting.
     * Called at the start of the Duke program.
     */
    public static void readSave() {
        if (InputFile.hasInput()) {
            try {
                InputFile.readInput();
            } catch (FileNotFoundException | InvalidFileException e) {
                UI.printExceptionMessage(e);
            }
            InputFile.deleteInput();
        }
    }

    /**
     * Creates and writes a save file.
     * Called at the end of the Duke program.
     */
    public static void writeSave() {
        try {
            InputFile.createInput();
            InputFile.writeToInput();
        } catch (IOException e) {
            UI.printExceptionMessage(e);
        }
    }

    /**
     * Checks if there is a save file <code>input</code>.
     * @return boolean This returns whether there exists a save file.
     */
    public static boolean hasInput() {
        File input = new File(FILE_PATH);
        return input.exists();
    }

    /**
     * Creates a text file for tasks to be saved in.
     * @throws IOException
     */
    public static void createInput() throws IOException {
        File input = new File(FILE_PATH);
        input.createNewFile();
    }

    /**
     * Deletes the read save file.
     */
    public static void deleteInput() {
        File input = new File(FILE_PATH);
        input.delete();
    }

    /**
     * Writes the save file according to current existing tasks in the Duke program.
     * Tasks are written as TaskType|DoneStatus|Description(|Date).
     * @throws IOException
     */
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

    /**
     * Reads save file to input existing tasks in the save file.
     * @throws FileNotFoundException A save file does not exist.
     * @throws InvalidFileException The save file being read has invalid characters.
     */
    public static void readInput() throws FileNotFoundException, InvalidFileException {
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
                throw new InvalidFileException();
            }
            tasks.add(current);
            taskCount = taskCount + 1;
            if (inputWords[IS_DONE_INDEX].equals("X")) {
                current.setDone();
            }
        }
    }
}
