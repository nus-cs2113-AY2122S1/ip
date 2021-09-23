package duke.storage;

import static duke.parser.Parser.parseDateTime;
import static duke.ui.Strings.FORMAT_DATE_SAVE;
import static duke.ui.Strings.FORMAT_TIME_SAVE;
import static duke.ui.Strings.NL;

import duke.data.exception.DirectoryCreationException;
import duke.data.exception.InvalidTaskTypeException;
import duke.data.task.Deadline;
import duke.data.task.Event;
import duke.data.task.Task;
import duke.data.task.Todo;
import duke.data.util.DateTime;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Storage {

    private static final String USER_DIR = System.getProperty("user.dir");
    private static final String DEFAULT_FILE_PATH = USER_DIR + "/data/tasks.txt";

    private final File saveFile;

    public Storage(String filePath) throws DirectoryCreationException {
        saveFile = new File(USER_DIR + "/" + filePath);
        createFileIfNotExist();
    }

    /**
     * Checks and creates savefile directory if it doesn't exist
     *
     * @throws DirectoryCreationException throws exception if directory cannot be created
     */
    private void createFileIfNotExist() throws DirectoryCreationException {
        if (!saveFile.exists()) {
            if (!saveFile.getParentFile().exists()) {
                if (!saveFile.getParentFile().mkdirs()) {
                    throw new DirectoryCreationException(saveFile.toString());
                }
            }
        }
    }

    /**
     * Loads preexisting tasklist from savefile given
     *
     * @return ArrayList of tasks containing all tasks to be added to the tasklist
     * @throws FileNotFoundException    throws exception if file cannot be found
     * @throws InvalidTaskTypeException throws exception if file contains invalid task types
     */
    public ArrayList<Task> load() throws FileNotFoundException, InvalidTaskTypeException {
        ArrayList<Task> taskList = new ArrayList<>();
        ArrayList<String> fileLines = readTasklistFromFile();

        for (String line : fileLines) {
            taskList.add(decodeTask(line));
        }

        return taskList;
    }

    /**
     * Saves current tasklist to file
     *
     * @param taskList tasklist to be saved
     * @throws IOException throws exception if file cannot be opened
     */
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

    public static final Pattern COMMAND_DATE_TIME_FORMAT = Pattern.compile(
            "(?<date>\\d+[:/]\\d+[:/]\\d+)(?<time>.*)");

    /**
     * Parses and decodes task saved in saveFile into a Task object
     *
     * @param encodedTask String of encoded task in the saveFile
     * @return Task object based on decoded task object
     * @throws InvalidTaskTypeException throws exception if String had an invalid task type
     */
    private Task decodeTask(String encodedTask) throws InvalidTaskTypeException {
        String[] taskData = encodedTask.split("\\|");

        char taskType = taskData[SAVE_TYPE_LOC].trim().charAt(0);
        String taskDescription = taskData[SAVE_DESCRIPTION_LOC].trim();
        boolean isDone = taskData[SAVE_DONE_LOC].trim().equals("1");
        String deadline = null;

        LocalDate deadlineDate = null;
        LocalTime deadlineTime = null;

        if (taskType == Deadline.TASK_TYPE || taskType == Event.TASK_TYPE) {
            deadline = taskData[SAVE_DEADLINE_LOC].trim();

            DateTime dateTime = parseDateTime(deadline);

            deadlineDate = dateTime.getDate();
            deadlineTime = dateTime.getTime();
        }

        Task task;

        switch (taskType) {
        case Todo.TASK_TYPE:
            task = new Todo(taskDescription);
            break;
        case Deadline.TASK_TYPE:
            if (deadlineDate == null) {
                task = new Deadline(taskDescription, deadline);
            } else {
                task = new Deadline(taskDescription, deadlineDate, deadlineTime);
            }
            break;
        case Event.TASK_TYPE:
            if (deadlineDate == null) {
                task = new Event(taskDescription, deadline);
            } else {
                task = new Event(taskDescription, deadlineDate, deadlineTime);
            }
            break;
        default:
            throw new InvalidTaskTypeException();
        }

        if (isDone) {
            task.setDone();
        }

        return task;
    }

    /**
     * Encodes task to be saved as a String in the saveFile
     *
     * @param task Task to be encoded
     * @return String to be saved in the saveFile
     */
    private String encodeTask(Task task) {
        final StringBuilder encodedTask = new StringBuilder();

        encodedTask.append(task.getType());
        encodedTask.append(SPACER);
        encodedTask.append(task.isDone() ? '1' : '0');
        encodedTask.append(SPACER);
        encodedTask.append(task.getDescription());

        if (task instanceof Event || task instanceof Deadline) {
            encodedTask.append(SPACER);
            String deadline = task.getDeadline();
            if (deadline != null) {
                encodedTask.append(deadline);
            } else {
                LocalDate deadlineDate = task.getDeadlineDate();
                LocalTime deadlineTime = task.getDeadlineTime();

                String formattedDeadlineDate = deadlineDate.format(DateTimeFormatter.ofPattern(FORMAT_DATE_SAVE));
                encodedTask.append(formattedDeadlineDate);

                if (deadlineTime != null) {
                    String formattedDeadlineTime = deadlineTime.format(DateTimeFormatter.ofPattern(FORMAT_TIME_SAVE));
                    encodedTask.append(" ").append(formattedDeadlineTime);
                }
            }
        }

        encodedTask.append(NL);

        return encodedTask.toString();
    }
}
