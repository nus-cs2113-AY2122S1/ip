package duke;

import duke.exception.DukeException;
import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private static final String FILE_PATH = "duke.txt";

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

    public static void saveToFile(List<Task> taskList) throws DukeException {
        try {
            File saveFile = new File(FILE_PATH);
            if (!saveFile.exists()) {
                saveFile.createNewFile();

            }
            storeTaskData(saveFile, taskList);
        } catch (IOException e) {
            throw new DukeException("Something went WRONG and I can't save!!! Time to give up and die...");
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
                taskList.add(new Event(description, TaskType.EVENT, isDone, taskDetails[3].trim()));
                break;
            case "D":
                LocalDateTime dateTime = Parser.extractDateTime(taskDetails[3].trim());
                taskList.add(new Deadline(description, TaskType.DEADLINE, isDone, dateTime));
                break;
            default:
                myScanner.close();
            }
        }
        myScanner.close();
    }

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
