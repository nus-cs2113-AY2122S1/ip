package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDos;

public class TaskStorage {
    static final String DATA_FILE_PATH = "./data/tasks.txt";
    static final String SPACE_SEPARATOR = " ";

    private File dataFile;

    public TaskStorage() throws IOException {
        File dataFile = new File(DATA_FILE_PATH);
        File dataDir = new File(dataFile.getParent());

        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }

        if (!dataFile.exists()) {
            dataFile.createNewFile();
        }

        this.dataFile = dataFile;
    }

    public ArrayList<Task> tasksFromFile() {
        ArrayList<Task> extractedTasks = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(this.dataFile);

            while(scanner.hasNextLine()) {
                Task task = readTask(scanner.nextLine());
                extractedTasks.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("\tI cannot open the file!");
        } catch (DukeException e) {
            System.out.println("\tWrong task format");
        }

        return extractedTasks;
    }



    private Task readTask(String string) throws DukeException {
        String[] detail = string.split(SPACE_SEPARATOR);

        switch (detail[0]) {
        case "event":
            return new Event(detail[1], detail[2], Boolean.parseBoolean(detail[3]));

        case "deadline":
            return new Deadline(detail[1],detail[2], Boolean.parseBoolean(detail[3]));

        case "todo":
            return new ToDos(detail[1], Boolean.parseBoolean(detail[2]));

        default:
            throw new DukeException("Not the right format. Not sure I can read that.");
        }
    }
}
