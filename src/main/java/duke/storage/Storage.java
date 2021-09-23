package duke.storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static duke.message.Messages.LOAD_DELIMITER;

/**
 * Loads and saves list of tasks data from a local text file
 */
public class Storage {
    private static final String DEFAULT_STORAGE_FILEPATH = "data/duke.txt";
    private static final String TODO_CODE = "T";
    private static final String DEADLINE_CODE = "D";
    private static final String EVENT_CODE = "E";
    private final Path path;

    public Storage() {
        this.path = Paths.get(DEFAULT_STORAGE_FILEPATH);
    }

    /**
     * Loads the data from a default storage filepath, creates one if it does not exist
     * @return the list of tasks stored in filepath
     * @throws FileNotFoundException when the data file does not exist
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        final int TASK_INDEX = 0;
        final int DONE_INDEX = 1;
        final int DESCRIPTION_INDEX = 2;
        final int DATE_INDEX = 3;
        final int TIME_INDEX_1 = 4;
        final int TIME_INDEX_2 = 5;

        ArrayList<Task> loadList = new ArrayList<>();
        String line;
        File dir = new File("data");

        if (!dir.exists()) {
            dir.mkdir();
        }

        File f = new File("data/duke.txt");
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            line = s.nextLine();
            String[] lineData = line.split(LOAD_DELIMITER);

            switch (lineData[TASK_INDEX]) {
            case TODO_CODE:
                loadList.add(new Todo(lineData[DESCRIPTION_INDEX]));
                break;
            case DEADLINE_CODE:

                loadList.add(new Deadline(lineData[DESCRIPTION_INDEX],
                        lineData[DATE_INDEX],
                        lineData[TIME_INDEX_1]));
                break;
            case EVENT_CODE:
                loadList.add(new Event(lineData[DESCRIPTION_INDEX],
                        lineData[DATE_INDEX],
                        lineData[TIME_INDEX_1],
                        lineData[TIME_INDEX_2]));
                break;
            }

            if (lineData[DONE_INDEX].equals("1")) {
                loadList.get(loadList.size() - 1).markAsDone();
            }
        }

        return loadList;
    }

    /**
     * Saves the list of tasks
     * @param tasks the list of tasks
     * @throws IOException when there is a problem writing to the file
     */
    public void save(ArrayList<Task> tasks) throws IOException {
        FileWriter beginWrite = new FileWriter("data/duke.txt");

        beginWrite.write("");
        beginWrite.close();

        FileWriter fw = new FileWriter("data/duke.txt", true);

        for (Task task : tasks) {
            fw.write(task.toSave());
            fw.write(System.lineSeparator());
        }

        fw.close();
    }
}
