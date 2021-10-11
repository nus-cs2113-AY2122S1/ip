package data;

import task.Task;
import task.Deadline;
import task.Event;
import task.ToDo;

import static common.Error.FILE_NOT_EXIST;
import static common.Error.WRITE_IOEXCEPTION;
import static common.Message.SUCCESS_FILE_FOUND;
import static common.Message.SUCCESS_DATA_READ;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Storage object contains all operations related
 * to read and write operations on the data for the application
 */
public class Storage {
    protected String dataPath;
    protected static final String PATH_DELIMITER = "/";
    protected static final String TASK_DELIMITER = " | ";
    protected static final String TASK_DELIMITER_REGEX = " \\| ";
    protected static final String DONE_INDICATOR = "X";

    public Storage() {
        this.dataPath = "data/data.txt";
    }

    /**
     * This method validates that the directories specified
     * to contain the data.txt and the text file itself exists
     * If not, it creates directories and new files to ensure
     * that the complete path is valid
     * @return File A valid file object that points to data.txt
     */
    private File validatePath() {
        String[] directories = this.dataPath.split(PATH_DELIMITER);
        /*
        Check for whether ./data directory exists
        if not, create ./data directory
        */
        File directory = new File(directories[0]);
        if (!directory.exists()) {
            directory.mkdir();
        }
        /*
        Check for whether ./data/data.txt exists
        if so, process list of tasks contained within
        if not, create new, empty data.txt file
        */
        File data = new File(dataPath);
        if (!data.exists()) {
            try {
                data.createNewFile();
            } catch (IOException e) {
                System.out.println(WRITE_IOEXCEPTION);
            }
            String absolutePath = data.getAbsolutePath();
            System.out.println(String.format(FILE_NOT_EXIST, absolutePath));
        } else {
            System.out.println(String.format(SUCCESS_FILE_FOUND, data.getAbsolutePath()));
        }
        return data;
    }

    /**
     * Reads and the content of data.txt specified, parses each entry
     * categorising it as either a ToDo, Deadline or Event,
     * storing them in an arraylist
     * @return ArrayList<Task> This stores all tasks found within data.txt in an arraylist
     */
    public ArrayList<Task> read() {
        File data = validatePath();
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner s = new Scanner(data);
            // Reads data.txt line by line
            while (s.hasNext()) {
                // Split line by delimited
                String[] line = s.nextLine().split(TASK_DELIMITER_REGEX);
                // Check status of each task
                Boolean status = false;
                if (line[1].equals("1")) {
                    status = true;
                }
                // Classify tasks into todo, deadline, event, then create corresponding objects
                // Lastly, add to tasklist
                switch(line[0]) {
                case "T":
                    ToDo newToDo = new ToDo(line[2], status);
                    tasks.add(newToDo);
                    break;
                case "D":
                    Deadline newDeadline = new Deadline(line[2], line[3], status);
                    tasks.add(newDeadline);
                    break;
                case "E":
                    Event newEvent = new Event(line[2], line[3], line[4], status);
                    tasks.add(newEvent);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("(!) File not found");
        }
        System.out.println(String.format(SUCCESS_DATA_READ, tasks.size()));
        return tasks;
    }

    /**
     * Given an arraylist of tasks, this method converts them into a text
     * representation of the objects, and overwrites data.txt with the contents
     * derived from the tasks.
     * @param tasks This contains the list of updated tasks as specified by the user
     */
    public void write(ArrayList<Task> tasks) {
        try {
            FileWriter fw = new FileWriter(dataPath);
            for (Task currentTask : tasks) {
                fw.write(getTaskData(currentTask) + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(WRITE_IOEXCEPTION);
        }
    }

    /**
     * This method converts tasks into a string format to be stored within data.txt
     * @param current An individual task
     * @return String The textual representation of the task
     */
    private static String getTaskData(Task current) {
        if (current instanceof ToDo) {
            return "T" + TASK_DELIMITER +
                    convertStatus(current.getStatus()) + TASK_DELIMITER +
                    current.getDescription();
        } else if (current instanceof Deadline) {
            return "D" + TASK_DELIMITER +
                    convertStatus(current.getStatus()) + TASK_DELIMITER +
                    current.getDescription() + TASK_DELIMITER +
                    ((Deadline) current).getTime();
        } else if (current instanceof Event) {
            return "E" + TASK_DELIMITER +
                    convertStatus(current.getStatus()) + TASK_DELIMITER +
                    current.getDescription() + TASK_DELIMITER +
                    ((Event) current).getStart() + TASK_DELIMITER +
                    ((Event) current).getEnd() + TASK_DELIMITER;
        } else {
            return "";
        }
    }

    private static int convertStatus(String status) {
        if (status.equals(DONE_INDICATOR)) {
            return 1;
        } else {
            return 0;
        }
    }
}
