package duke.storage;

import static duke.ui.Strings.NL;

import duke.data.exception.DirectoryCreationException;
import duke.data.exception.InvalidTaskTypeException;
import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.task.Todo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private static final String USER_DIR = System.getProperty("user.dir");
    private static final String DEFAULT_FILE_PATH = USER_DIR + "/data/tasks.txt";

    private final File saveFile;

    public Storage(String filePath) throws DirectoryCreationException {
        saveFile = new File(USER_DIR + "/" + filePath);
        createFileIfNotExist();
    }

    private void createFileIfNotExist() throws DirectoryCreationException {
        if (!saveFile.exists()) {
            if (!saveFile.getParentFile().exists()) {
                if (!saveFile.getParentFile().mkdirs()) {
                    throw new DirectoryCreationException(saveFile.toString());
                }
            }
        }
    }

    public ArrayList<Task> load() throws FileNotFoundException, InvalidTaskTypeException {
        ArrayList<Task> taskList = new ArrayList<>();
        ArrayList<String> fileLines = readTasklistFromFile();

        for (String line : fileLines) {
            taskList.add(decodeTask(line));
        }

        return taskList;
    }

    public void save(ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(saveFile);

        for (Task task : taskList) {
            fw.write(encodeTask(task));
        }

        fw.close();
    }

    /**
     * reads save file into an array
     *
     * @return ArrayList containing each line from the save file
     */
    private ArrayList<String> readTasklistFromFile() throws FileNotFoundException {
        ArrayList<String> fileLines = new ArrayList<>();

        Scanner scanner = new Scanner(saveFile);

        while (scanner.hasNextLine()) {
            fileLines.add(scanner.nextLine());
        }

        scanner.close();

        return fileLines;
    }

    private static final String SPACER = " | ";

    private static final int SAVE_TYPE_LOC = 0;
    private static final int SAVE_DONE_LOC = 1;
    private static final int SAVE_DESCRIPTION_LOC = 2;
    private static final int SAVE_DEADLINE_LOC = 3;

    private Task decodeTask(String encodedTask) throws InvalidTaskTypeException {
        String[] taskData = encodedTask.split("\\|");

        char taskType = taskData[SAVE_TYPE_LOC].trim().charAt(0);
        String taskDescription = taskData[SAVE_DESCRIPTION_LOC].trim();
        boolean isDone = taskData[SAVE_DONE_LOC].trim().equals("1");

        Task task;

        switch (taskType) {
        case Todo.TASK_TYPE:
            task = new Todo(taskDescription);
            break;
        case Deadline.TASK_TYPE:
            task = new Deadline(taskDescription, taskData[SAVE_DEADLINE_LOC].trim());
            break;
        case Event.TASK_TYPE:
            task = new Event(taskDescription, taskData[SAVE_DEADLINE_LOC].trim());
            break;
        default:
            throw new InvalidTaskTypeException();
        }

        if (isDone) {
            task.setDone();
        }

        return task;
    }

    private String encodeTask(Task task) {
        final StringBuilder encodedTask = new StringBuilder();

        encodedTask.append(task.getType());
        encodedTask.append(SPACER);
        encodedTask.append(task.isDone() ? '1' : '0');
        encodedTask.append(SPACER);
        encodedTask.append(task.getDescription());

        if (task instanceof Event || task instanceof Deadline) {
            encodedTask.append(SPACER);
            encodedTask.append(task.getDeadline());
        }

        encodedTask.append(NL);

        return encodedTask.toString();
    }
}
