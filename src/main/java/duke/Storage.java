package duke;

import duke.Exceptions.DukeException;
import duke.Exceptions.StorageFileCreationFailedException;
import duke.Exceptions.InvalidTaskTypeException;
import duke.Exceptions.DataStoreNotFoundException;
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

    public TasksList load() throws DukeException{
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
            String raw_task_entry = fileScanner.nextLine();
            String[] task_data_points = raw_task_entry.split("\\|");
            String taskType = task_data_points[0];
            switch (taskType) {
            case "T":
                task = new ToDo(task_data_points[2]);
                if (task_data_points[1].equals("1")) { task.markAsDone(); }
                taskList.addTask(task);
                break;
            case "D":
                task = new Deadline(task_data_points[2], task_data_points[3]);
                if (task_data_points[1].equals("1")) { task.markAsDone(); }
                taskList.addTask(task);
                break;
            case "E":
                task = new Event(task_data_points[2], task_data_points[3]);
                if (task_data_points[1].equals("1")) { task.markAsDone(); }
                taskList.addTask(task);
                break;
            default:
                throw new InvalidTaskTypeException(taskType);
            }
        }
        return taskList;
    }

    public void save(TasksList taskList) throws DukeException{
        FileWriter file_writer;
        StringBuilder output_string;

        try {
            file_writer = new FileWriter(FILEPATH);
            output_string = new StringBuilder();
            for (int i=0; i< taskList.getSize(); i++) {
                Task task = taskList.getTask(i);
                output_string.append(task.formatForDataStore());
            }
            file_writer.write(output_string.toString());
            file_writer.close();
        } catch (IOException e) {
            throw new DataStoreNotFoundException(FILEPATH);
        }
    }
}
