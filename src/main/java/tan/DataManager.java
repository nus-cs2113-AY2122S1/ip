package tan;

import tan.exceptions.TaskToStringException;
import tan.tasktype.Deadline;
import tan.tasktype.Event;
import tan.tasktype.Task;
import tan.tasktype.ToDo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.StandardOpenOption.CREATE;


/**
 * HOW TO USE??
 * <p>
 * Call setWriterAndReader to set the csvReader & csvWriter.
 * Then use respective reader/writer to perform task.
 * Note, if you close csvWriter, you have to write from top
 * aga in. Use Flush to push to current writing in buffer to file.
 * <p>
 *
 * homePath - The directory that the data file is saved at in String.
 *
 */
public class DataManager {
    protected static BufferedReader csvReader;
    protected static BufferedWriter csvWriter;
    private static final String homePath = System.getProperty("user.dir");
    private static final String[] TITLE = {"Type", "Status", "Description", "Date"};
    private static final String FILE_NAME = "taskData.csv";
    private static Path DATA_PATH;
    private static int totalLinesLoaded = 0;


    /**
     * This function deletes the old taskData.csv file and
     * recreates a new one with the new List of task. The file's
     * location is specified by the DATA_PATH variable.
     * As such, ensure that the DATA_PATH is set
     * before running this function.
     *
     * @param curList The list that contains all the Task.
     * @throws IOException Thrown when there is an error in writing to the file.
     */
    public static void saveCurrentList(List<Task> curList) throws IOException {
        File oldFile = new File(DATA_PATH.toString());
        boolean isDeleted = oldFile.delete();
        if (!isDeleted) {
            System.out.println("Unable to delete file. Please resolve this issue 1st");
        } else {
            csvWriter = Files.newBufferedWriter(DATA_PATH, CREATE);
        }
        writeHeader();
        for (Task curTask : curList) {
            String curTaskInCsv = getTaskInCsv(curTask);
            if (curTaskInCsv == null) {
                System.out.println("Error in converting task to string. Duke will not save this task.");
                continue;
            }
            csvWriter.append(curTaskInCsv);
            csvWriter.append(System.lineSeparator());
            csvWriter.flush();
        }
    }

    /**
     * This function takes in any Task and returns
     * its properties in CSV format. If the function fails
     * to convert the string into CSV, it will return NULL instead.
     *
     * @param curTask The Task to be converted.
     * @return The task's properties in CSV format, null otherwise.
     */
    private static String getTaskInCsv(Task curTask) {

        try {
            String type = curTask.getTaskType();
            int taskTypeInt = getTaskTypeInt(type);
            if (taskTypeInt == -1) {
                throw new TaskToStringException();
            }
            String name = curTask.getTaskName();
            String date = curTask.getDateTime();
            Boolean status = curTask.getStatus();
            String statusAsString = (status) ? "1" : "0";
            String taskAsCsv = taskTypeInt + "," + statusAsString + "," + name + "," + date;
            return taskAsCsv;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * This function takes in a task-type in String
     * and returns its corresponding integer value.
     * the task type accepted is not case sensitive.
     * 0 - todo, 1 - deadline, 2 - event, -1 - unknown.
     *
     * @param taskType The task type in String.
     * @return The value corresponding to its task type in integer. -1 if unknown.
     */
    public static int getTaskTypeInt(String taskType) {
        taskType = taskType.toLowerCase();
        switch (taskType) {
        case "todo":
            return 0;
        case "deadline":
            return 1;
        case "event":
            return 2;
        default:
            System.out.println("Unknown Task Type!");
            return -1;
        }
    }

    /**
     * This task retrieves all the tasks stored
     * in the file, converts them into their respective
     * task and puts them into a list and then returns it.
     * View the getStoredData function to understand more about
     * the data retrieval.
     *
     * @return A list of task that were stored in the file.
     */
    public static List<Task> setFileAndGetTasks() {
        initializeFile();
        List<String[]> storedTasks = getStoredDataInString();
        List<Task> listOfStoredTasks = new ArrayList<>();
        int iterator = 1;
        while (iterator < totalLinesLoaded) {
            Task newTask = getNewTask(storedTasks.get(iterator));
            if (newTask != null) {
                listOfStoredTasks.add(newTask);
                iterator += 1;
            }
        }
        return listOfStoredTasks;
    }

    /**
     * This program takes in a row of data from the data
     * file but in an array format instead of CSV. It then
     * parses all the data to get the necessary information
     * to create a Task by calling createTask().
     *
     * @param curTaskString The array of the task's data from the dataFile.
     * @return The Task created according to the data in the array. Null otherwise.
     */
    private static Task getNewTask(String[] curTaskString) {
        int taskType = Integer.parseInt(curTaskString[0]);
        int statusInt = Integer.parseInt(curTaskString[1]);
        Boolean isDone = statusInt >= 1;
        String description = curTaskString[2];
        String date = curTaskString[3];

        return createTask(taskType, isDone, description, date);
    }

    /**
     * This function creates & returns the actual task based on the
     * parameters passed in. If the task type integer is not recognized,
     * null is returned.
     *
     * @param taskType The integer representing its type of task.
     * @param isDone   The completion status of the task.
     * @param name     The Name of the task.
     * @param date     The date, if there is, of the task.
     * @return The task created. Null if task type is not recognized.
     */
    private static Task createTask(int taskType, boolean isDone, String name, String date) {
        Task newTask;
        switch (taskType) {
        case 0:
            //Task type todo.
            newTask = new ToDo(name, isDone);
            break;
        case 1:
            //Task type Deadline.
            newTask = new Deadline(name, isDone, date);
            break;
        case 2:
            //Task type event.
            newTask = new Event(name, isDone, date);
            break;
        default:
            //Error in task type.
            newTask = null;
            System.out.println("Error in task type.");
            break;
        }
        return newTask;
    }

    /**
     * Reads the dataFile converts the data
     * from CSV into a list of string array and returns it.
     * <p>
     * Each List element is a row and
     * each array element is a row split along the commas.
     * In the event that a row is un-readable due to IO exception,
     * it will inform the user.
     * <p>
     * Note: setup the csvReader before using this function.
     *
     * @return The data file's information in a List of arrays.
     */
    private static List<String[]> getStoredDataInString() {
        String curLine;
        List<String[]> allTasks = new ArrayList<>();
        try {
            while ((curLine = csvReader.readLine()) != null) {
                String[] output = curLine.split(",");
                allTasks.add(output);
                totalLinesLoaded += 1;
            }
        } catch (IOException e) {
            System.out.println("Error in reading file! Failed loading data.");
            System.exit(-1);
        }
        return allTasks;
    }

    /**
     * This function calls the necessary
     * functions to setup the file, file reader,
     * file writer based on the homePath.
     */
    private static void initializeFile() {
        try {
            setWriterAndReaderAndDataPath(homePath);
            writeHeader();
            csvWriter.close();
        } catch (IOException e) {
            System.out.println("Error in writing to file.");
            System.exit(-1);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * This function writes the Header, as per
     * TITLE values, for the data file.
     * Note to setup the csvWriter before calling this.
     *
     * @throws IOException Thrown in the event of an error while writing.
     */
    private static void writeHeader() throws IOException {
        csvWriter.append(String.join(",", TITLE));
        csvWriter.append(System.lineSeparator());
        csvWriter.flush();
    }

    /**
     * This function sets up the file, file Writer/Reader
     * and DATA_PATH. If the file is not found, it will
     * create a new file for the user at the specified
     * directory.
     *
     * @param homePath user home directory in String format.
     */
    private static void setWriterAndReaderAndDataPath(String homePath) {
        boolean isExists = false;

        try {
            DATA_PATH = Paths.get(homePath, FILE_NAME);
            isExists = Files.exists(DATA_PATH);
            //Sets writer if can find file, else creates file then sets.
            csvWriter = Files.newBufferedWriter(DATA_PATH, CREATE);
            csvReader = Files.newBufferedReader(DATA_PATH);
        } catch (InvalidPathException e) {
            System.out.println("Path to file not found. Please try again.");
            System.exit(-1);
        } catch (IOException e) {
            System.out.println("IO Exception error!");
            System.exit(-1);
        } catch (SecurityException e) {
            System.out.println("Security Error, unable to read data file.");
            System.exit(-1);
        } catch (Exception e) {
            System.out.println("Error :" + e);
            System.exit(-1);
        }

        if (isExists) {
            System.out.println("Found data file!");
        } else {
            System.out.println("A new data file has been created at: " + DATA_PATH.toString());
        }
    }
}
