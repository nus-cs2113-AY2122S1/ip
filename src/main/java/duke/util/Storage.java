package duke.util;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Storage {
    private static final String MESSAGE_FILEWRITER_ERROR = "FileWriter Error.";
    private static final String MESSAGE_FORMAT_FILE_NOT_FOUND = "File not found - %s";

    private String filename;

    /**
     * Constructor for Storage class.
     *
     * @param filename The file to read/write.
     */
    public Storage(String filename) {
        this.filename = filename;

        Util.createFile(filename);
    }

    /**
     * Parses the line and returns a task object.
     *
     * @param line The line to parse.
     * @return A Task object if line is successfully parsed, else null.
     */
    private Task getTaskFromLine(String line) {
        String[] lineSplit = line.split(Pattern.quote(Task.COLUMN_SEPARATOR));
        if (lineSplit.length < 3) {
            return null;
        }

        String taskTypeColumn = lineSplit[0].trim();
        String isDoneColumn = lineSplit[1].trim();
        String descriptionColumn = lineSplit[2];

        if (taskTypeColumn.length() != 1 || isDoneColumn.length() != 1) {
            return null;
        }

        Task task = null;
        char taskType = taskTypeColumn.charAt(0);
        boolean isDone = isDoneColumn.equals("1");
        switch (taskType) {
        case Task.TYPE_TODO:
            task = new Todo(descriptionColumn);
            break;

        case Task.TYPE_DEADLINE:
            String byColumn = lineSplit[3];
            if (lineSplit.length == 4) {
                LocalDateTime byDateTime = Util.getDateTimeFromString(byColumn, Task.DATETIME_FORMAT_INPUT);
                if (byDateTime != null) {
                    task = new Deadline(descriptionColumn, byDateTime);
                }
            }
            break;

        case Task.TYPE_EVENT:
            String atColumn = lineSplit[3];
            if (lineSplit.length == 4) {
                LocalDateTime atDateTime = Util.getDateTimeFromString(atColumn, Task.DATETIME_FORMAT_INPUT);
                task = new Event(descriptionColumn, atDateTime);
            }
            break;
        }

        if (task != null && isDone) {
            task.setAsDone();
        }

        return task;
    }

    /**
     * Loads tasks from the file.
     *
     * @return A list of tasks.
     * @throws DukeException if file is not found.
     */
    public ArrayList<Task> load() throws DukeException {
        File file = new File(filename);
        if (!file.exists()) {
            return null;
        }

        ArrayList<Task> tasks = new ArrayList<>();

        try {
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                if (line.isEmpty()) {
                    continue;
                }

                Task task = getTaskFromLine(line);
                if (task == null) {
                    continue;
                }

                tasks.add(task);
            }

            fileReader.close();
        } catch (FileNotFoundException e) {
            throw new DukeException(String.format(MESSAGE_FORMAT_FILE_NOT_FOUND, filename));
        }

        return tasks;
    }

    /**
     * Writes tasks into the file
     *
     * @param tasks The tasks to write.
     * @throws DukeException If fail to write.
     */
    public void write(TaskList tasks) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(filename);

            for (int i = 0; i < tasks.getSize(); i += 1) {
                Task task = tasks.getTaskAt(i);
                fileWriter.write(String.format("%s\n", task.toFileString()));
            }

            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException(MESSAGE_FILEWRITER_ERROR);
        }
    }
}
