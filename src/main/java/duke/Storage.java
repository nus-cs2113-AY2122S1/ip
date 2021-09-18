package duke;

import duke.exceptions.DukeException;
import duke.exceptions.StorageFileCreationFailedException;
import duke.exceptions.InvalidTaskTypeException;
import duke.exceptions.DataStoreNotFoundException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Storage {
    private final String FILEPATH;

    public Storage(String filePath) {
        this.FILEPATH = filePath;
    }

    public void createStorageFile() throws StorageFileCreationFailedException {
        File file = new File(FILEPATH);
        try {
            if (file.getParentFile().mkdirs()) {
                System.out.println("Directories created");
            }
            if (file.createNewFile()) {
                System.out.println("File " + FILEPATH + " created");
            }
        } catch (IOException e) {
            throw new StorageFileCreationFailedException(FILEPATH);
        }
    }

    public TasksList load() throws DukeException {
        String DONE_INDICATOR = "1";
        int TASK_TYPE_INDEX = 0;
        int DONE_STATUS_INDEX = 1;
        int TASK_INDEX = 2;
        int TASK_DETAILS_INDEX = 3;

        Scanner fileScanner;
        TasksList taskList = new TasksList();

        File file = new File(FILEPATH);
        try {
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new DataStoreNotFoundException(FILEPATH);
        }
        while (fileScanner.hasNext()) {
            Task task;
            String rawTaskEntry = fileScanner.nextLine();
            String[] taskDataPoints = rawTaskEntry.split("\\|");
            String taskType = taskDataPoints[TASK_TYPE_INDEX];
            switch (taskType) {
            case "T":
                task = new ToDo(taskDataPoints[TASK_INDEX]);
                if (taskDataPoints[DONE_STATUS_INDEX].equals(DONE_INDICATOR)) {
                    task.markAsDone();
                }
                taskList.addTask(task);
                break;
            case "D":
                task = new Deadline(taskDataPoints[TASK_INDEX], taskDataPoints[TASK_DETAILS_INDEX]);
                if (taskDataPoints[DONE_STATUS_INDEX].equals(DONE_INDICATOR)) {
                    task.markAsDone();
                }
                taskList.addTask(task);
                break;
            case "E":
                task = new Event(taskDataPoints[TASK_INDEX], taskDataPoints[TASK_DETAILS_INDEX]);
                if (taskDataPoints[DONE_STATUS_INDEX].equals(DONE_INDICATOR)) {
                    task.markAsDone();
                }
                taskList.addTask(task);
                break;
            default:
                throw new InvalidTaskTypeException(taskType);
            }
        }
        return taskList;
    }

    public void save(TasksList taskList) throws DukeException {
        FileWriter fileWriter;
        StringBuilder outputString;

        try {
            fileWriter = new FileWriter(FILEPATH);
            outputString = new StringBuilder();
            for (int i=0; i< taskList.getSize(); i++) {
                Task task = taskList.getTask(i);
                outputString.append(task.formatForDataStore());
            }
            fileWriter.write(outputString.toString());
            fileWriter.close();
        } catch (IOException e) {
            throw new DataStoreNotFoundException(FILEPATH);
        }
    }
}
