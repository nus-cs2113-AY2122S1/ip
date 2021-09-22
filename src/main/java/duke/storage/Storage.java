package duke.storage;

import duke.exception.DukeException;
import duke.parser.Parser;
import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private static final String FILE_PATH = "data/dukeTask.txt";
    private static final String DIRECTORY = "data";

    /**
     * Stores tasks in the provided list of tasks to duke.txt save file by rewriting it.
     * Format: T | 1 | Description
     * Where the first field indicates the type of task, second field that a task is done if it is marked as 1 and
     * vice versa, and the third field contains the description of the task.
     *
     * @param taskList List of tasks to be written to the save file.
     * @throws IOException Throws an IOException
     */
    public static void storeTaskData(File saveFile, List<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(saveFile);
        for (Task task : taskList) {
            fw.write(String.format("%s\n", task.getFileStringFormat()));
        }
        fw.close();
    }

    /**
     * Saves a list of tasks to the specified file path
     *
     * @param taskList The list of tasks to be saved
     */
    public static void saveToFile(List<Task> taskList) {
        try {
            File saveFile = new File(FILE_PATH);
            if (!saveFile.exists()) {
                Files.createDirectory(Path.of(DIRECTORY));
                Files.createFile(Path.of(FILE_PATH));
            }
            storeTaskData(saveFile, taskList);
        } catch (IOException e) {
            System.out.println("Something went WRONG and I can't save!!! Time to give up and die...");
        }
    }

    /**
     * Parses save data from duke.txt and stores the parsed data into a created lists of tasks.
     *
     * @param taskList The list of tasks for parsed data to be stored in.
     * @throws FileNotFoundException    Throws FileNotFoundException if the save file is not created yet.
     * @throws IllegalArgumentException Throws IllegalArgumentException.
     */
    private static void parseSaveData(List<Task> taskList) throws FileNotFoundException, IllegalArgumentException, DukeException {
        File taskFile = new File(FILE_PATH);
        Scanner myScanner = new Scanner(taskFile);
        while (myScanner.hasNextLine()) {
            String data = myScanner.nextLine();

            // Index 0 contains taskType, Index 1 contains isDone, Index 2 contains description, Index 3 contains DateTime
            String[] taskDetails = data.split("\\|");
            boolean isDone = taskDetails[1].trim().equals("1");
            String description = taskDetails[2].trim();
            switch (taskDetails[0].trim()) {
            case "T":
                taskList.add(new Todo(description, TaskType.TODO, isDone));
                break;
            case "E":
                LocalDateTime eventDateTime = Parser.extractDateTime(taskDetails[3].trim());
                taskList.add(new Event(description, TaskType.EVENT, isDone, eventDateTime));
                break;
            case "D":
                LocalDateTime deadlineDateTime = Parser.extractDateTime(taskDetails[3].trim());
                taskList.add(new Deadline(description, TaskType.DEADLINE, isDone, deadlineDateTime));
                break;
            default:
                myScanner.close();
            }
        }
        myScanner.close();
    }

    /**
     * Loads previous lists of tasks from duke.txt and creates a new save file if the user is running the program
     * for the first time.
     *
     * @param taskList The list of tasks data from duke.txt is to be loaded into.
     */
    public static void loadSaveData(List<Task> taskList) {
        try {
            parseSaveData(taskList);
            System.out.println("File loaded successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Gosh. This save file cannot is bad. I can't read it!");
        } catch (FileNotFoundException e) {
            System.out.println("A new user??? Oh my... shall we begin?");
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
