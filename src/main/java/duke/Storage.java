package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class handles reading, writing and loading of the file used to store the data of
 * the task list in. If the file does not exist, a directory and a file will be created
 * to store the contents of the task list in
 */
public class Storage {
    private static final int TASK_DONE_INDEX = 1;
    private static final int TASK_DESCRIPTION_INDEX = 2;
    public static final int TASK_TIME_INDEX = 3;
    private File file;

    /**
     * Represents the setting up of the storage in the form of a file object that will be used during the
     * duration which the application is running. If the file path does not contain a file, a file will
     * be created instead.
     *
     * @param filePath relative file path that contains the file storing data of the task list
     * @throws Exception exception is thrown if the file path does not exist or if a problem occur when
     *                   creating a directory or file
     */
    public Storage(String filePath) throws Exception{
        this.file = new File(filePath);
        if (!this.file.exists()) {
            File dir = new File("data");
            if (dir.mkdir()) {
                System.out.println("Creating a directory called /data");
            }
            this.file = new File("data/duke.txt");
            if (file.createNewFile()) {
                System.out.println("A file called duke.txt is created at /data/duke.txt");
            }
        }
    }

    /**
     * Converts the data stored in the file object into an ArrayList storing all the task in the task
     * list.
     *
     * @return the existing task list that was stored in the file
     * @throws FileNotFoundException exception thrown if file cannot be found for reading
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner s = new Scanner(file);
        while (s.hasNext()){
            String[] taskInfo = s.nextLine().split(",");

            if (taskInfo[0].equals("duke.tasks.Task")) {
                Task todo = new Task(Boolean.parseBoolean(taskInfo[TASK_DONE_INDEX]), taskInfo[TASK_DESCRIPTION_INDEX]);
                tasks.add(todo);
            } else if (taskInfo[0].equals("duke.tasks.Event")) {
                Task event = new Event(Boolean.parseBoolean(taskInfo[TASK_DONE_INDEX]),taskInfo[TASK_DESCRIPTION_INDEX], taskInfo[TASK_TIME_INDEX]);
                tasks.add(event);
            } else {
                Task deadline = new Deadline(Boolean.parseBoolean(taskInfo[TASK_DONE_INDEX]), taskInfo[TASK_DESCRIPTION_INDEX], taskInfo[TASK_TIME_INDEX]);
                tasks.add(deadline);
            }
        }
        return tasks;
    }

    /**
     * writes the contents of the task list into the file to store store the data permanently
     *
     * @param tasks task list that stores all the task that we want to write to the file
     * @throws IOException thrown when file cannot be written to the file object in Storage
     */
    public void store(TaskList tasks) throws IOException {
        FileWriter writer = new FileWriter(this.file);
        ArrayList<Task> tasksToSave = tasks.saveTasks();
        for(Task t : tasksToSave) {
            writer.write(t.saveFormat() + "\n");
        }
        writer.close();
    }
}
