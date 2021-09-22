package duke.data;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A storage class used to handle all functionalities related to saving
 * and retrieving data from the local storage file.
 * @author Mohamed Irfan
 */
public class Storage {
    private final File data;
    private final String[] dataReadWriteErrorMsg = {
            "Directory to file cannot be found.",
            "Task cannot be read from local file.",
            "Tasks cannot be saved to local file."
    };

    /**
     * Constructor method that creates a new tasks file if one does not exist.
     * @param pathToFile The path to the file including the file name and extension (eg. tasks.txt)
     * @throws DukeException
     */
    public Storage(String pathToFile) throws DukeException {
        try {
            File data = new File(pathToFile);
            if (data.createNewFile()) {
                System.out.println("* New storage file created *");
            } else {
                System.out.println("* Tasks successfully loaded *");
            }
            this.data = data;
        } catch (IOException err) {
            throw new DukeException(dataReadWriteErrorMsg[0]);
        }
    }

    /**
     * Method to load tasks from the local storage file. Once it reads the data
     * from the file, it does the necessary processing of the data to convert
     * it into Task objects for use by the application.
     * @return tasks - A list of task objects loaded and parsed from local storage file.
     * @throws DukeException
     */
    public ArrayList<Task> loadTasksFromFile() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner in = new Scanner(data);
            while (in.hasNextLine()) {
                String[] taskData = in.nextLine().split(" \\| ");
                String taskTime;
                String taskType = taskData[0];
                String taskDescription = taskData[2];
                boolean isDone = taskData[1].equals("1");
                switch (taskType) {
                case "T":
                    Task todo = new Todo(taskDescription, isDone);
                    tasks.add(todo);
                    break;
                case "E":
                    taskTime = taskData[3];
                    Task event = new Event(taskDescription, taskTime, isDone);
                    tasks.add(event);
                    break;
                case "D":
                    taskTime = taskData[3];
                    Task deadline = new Deadline(taskDescription, taskTime, isDone);
                    tasks.add(deadline);
                    break;
                default:
                    throw new DukeException(dataReadWriteErrorMsg[1]);
                }
            }
            return tasks;
        } catch (FileNotFoundException err) {
            throw new DukeException(dataReadWriteErrorMsg[0]);
        }
    }

    /**
     * Method to save tasks to the local storage file
     * @param tasks
     * @throws DukeException
     */
    public void saveTasksToFile(ArrayList<Task> tasks) throws DukeException {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(data));
            for (Task task : tasks) {
                writer.println(task.formatSaveToFile());
            }
            writer.close();
        } catch (IOException err) {
            throw new DukeException(dataReadWriteErrorMsg[2]);
        }
    }
}
