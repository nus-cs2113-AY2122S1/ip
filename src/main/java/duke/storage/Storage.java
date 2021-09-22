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

public class Storage {
    private static final String DEFAULT_STORAGE_FILEPATH = "data/duke.txt";
    private final Path path;

    public Storage() {
        this.path = Paths.get(DEFAULT_STORAGE_FILEPATH);
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        final String LOAD_DELIMITER = "--";
        final int TASK_INDEX = 0;
        final int DONE_INDEX = 1;
        final int DESCRIPTION_INDEX = 2;
        final int BY_AT_INDEX = 3;
        final String TODO_CODE = "T";
        final String DEADLINE_CODE = "D";
        final String EVENT_CODE = "E";

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

            if (lineData[TASK_INDEX].equals(TODO_CODE)) {
                loadList.add(new Todo(lineData[DESCRIPTION_INDEX]));
            } else if (lineData[TASK_INDEX].equals(DEADLINE_CODE)) {
                loadList.add(new Deadline(lineData[DESCRIPTION_INDEX], lineData[BY_AT_INDEX]));
            } else if (lineData[TASK_INDEX].equals(EVENT_CODE)) {
                loadList.add(new Event(lineData[DESCRIPTION_INDEX], lineData[BY_AT_INDEX]));
            }

            if (lineData[DONE_INDEX].equals("1")) {
                loadList.get(loadList.size() - 1).markAsDone();
            }
        }

        return loadList;
    }

    public void save(ArrayList<Task> tasks) throws IOException {
        final String DELIMITER = "--";
        FileWriter beginWrite = new FileWriter("data/duke.txt");
        int userInputsCount = tasks.size();

        beginWrite.write("");
        beginWrite.close();

        FileWriter fw = new FileWriter("data/duke.txt", true);

        for (int i = 0; i < userInputsCount; i++) {
            if (tasks.get(i).getCode().equals("T")) {
                String lineToAppend = tasks.get(i).getCode() + DELIMITER + tasks.get(i).getDoneValue() +
                        DELIMITER + tasks.get(i).getDescription();
                fw.write(lineToAppend);
                fw.write(System.lineSeparator());
            } else if (tasks.get(i).getCode().equals("D")) {
                String lineToAppend = tasks.get(i).getCode() + DELIMITER + tasks.get(i).getDoneValue() +
                        DELIMITER + tasks.get(i).getDescription() + DELIMITER + tasks.get(i).getBy();
                fw.write(lineToAppend);
                fw.write(System.lineSeparator());
            } else if (tasks.get(i).getCode().equals("E")) {
                String lineToAppend = tasks.get(i).getCode() + DELIMITER + tasks.get(i).getDoneValue() +
                        DELIMITER + tasks.get(i).getDescription() + DELIMITER + tasks.get(i).getAt();
                fw.write(lineToAppend);
                fw.write(System.lineSeparator());
            }
        }
        fw.close();
    }
}
