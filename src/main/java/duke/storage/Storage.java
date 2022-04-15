package duke.storage;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static duke.message.Messages.LOAD_DELIMITER;

/**
 * Loads and saves list of tasks data from a local text file
 */
public class Storage {
    private static final String DEFAULT_STORAGE_FILEPATH = "data/duke.txt";
    private static final String DIR = "data";
    private static final String TODO_CODE = "T";
    private static final String DEADLINE_CODE = "D";
    private static final String EVENT_CODE = "E";

    /**
     * Loads the data from a default storage filepath, creates one if it does not exist
     * @return the list of tasks stored in filepath
     * @throws DukeException when the data file does not exist
     */
    public ArrayList<Task> load() throws DukeException {
        final String LOAD_ERROR_1 = "failed to create directory 'duke/'";
        final String LOAD_ERROR_2 = "file not found";
        final String IS_DONE_TRUE = "1";
        final int TASK_INDEX = 0;
        final int DONE_INDEX = 1;
        final int DESCRIPTION_INDEX = 2;
        final int DATE_INDEX = 3;
        final int TIME_INDEX_1 = 4;
        final int TIME_INDEX_2 = 5;

        ArrayList<Task> loadList = new ArrayList<>();
        String line;
        File dir = new File(DIR);

        if (!dir.exists()) {
            boolean isCreated = dir.mkdir();

            if (!isCreated) {
                throw new DukeException(LOAD_ERROR_1);
            }
        }

        File f = new File(DEFAULT_STORAGE_FILEPATH);
        Scanner s;

        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            throw new DukeException(LOAD_ERROR_2);
        }

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

            if (lineData[DONE_INDEX].equals(IS_DONE_TRUE)) {
                loadList.get(loadList.size() - 1)
                        .markAsDone();
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
        FileWriter beginWrite = new FileWriter(DEFAULT_STORAGE_FILEPATH);

        // overwrites data in storage file
        beginWrite.write("");
        beginWrite.close();

        FileWriter fw = new FileWriter(DEFAULT_STORAGE_FILEPATH, true);

        for (Task task : tasks) {
            fw.write(task.toSave());
            fw.write(System.lineSeparator());
        }

        fw.close();
    }
}
