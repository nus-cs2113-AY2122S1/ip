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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.StandardOpenOption.CREATE;


/**
 * Key Notes:
 * Call setWriterAndReader to set the csvReader & csvWriter.
 * Then use respective reader/writer to perform task.
 * If you close csvWriter, you have to set the writer again
 * and write from top of file again.
 * Use Flush to push to current writing in buffer to file.
 */
public class Storage {
    protected static BufferedReader csvReader;
    protected static BufferedWriter csvWriter;
    private static final String homePath = System.getProperty("user.dir");
    private static final String[] TITLE = {"Type", "Status", "Description", "Date"};
    private static final String FILE_NAME = "taskData.csv";
    private static Path DATA_PATH;
    private static int totalLinesLoaded = 0;

    /**
     * Saves the current list by deleting the old taskData.csv file and
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
            return;
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
     * Returns the properties of a task in String, CSV format.
     * If the function fails to convert the string into CSV,
     * it will return NULL instead.
     * Its corresponding task type to integers are,
     * 0 - todo, 1 - deadline, 2 - event, -1 - unknown.
     *
     * @param curTask The Task to be converted.
     * @return The task's properties in CSV format, null otherwise.
     */
    private static String getTaskInCsv(Task curTask) {

        String taskAsCsv = null;
        try {
            String type = curTask.getTaskType();
            int taskTypeInt = getTaskTypeInInt(type);
            if (taskTypeInt == -1) {
                //If Invalid task int.
                throw new TaskToStringException();
            }
            String taskDescription = curTask.getTaskDescription();
            String unformattedDate = curTask.getDateTimeForStorage();
            Boolean taskStatus = curTask.getStatus();
            String statusAsString = (taskStatus) ? "1" : "0";
            taskAsCsv = taskTypeInt + "," + statusAsString + "," + taskDescription + "," + unformattedDate;
        } catch (TaskToStringException x) {
            System.out.println("Error in getting the task type!");
            return null;
        } catch (Exception e) {
            System.out.println("Error :" + e);
            return null;
        }
        return taskAsCsv;
    }

    /**
     * Returns the integer corresponding to its task type, else -1.
     * This function takes in a task-type in String
     * and returns its corresponding integer value.
     * Its corresponding task type to integers are,
     * 0 - todo, 1 - deadline, 2 - event, -1 - unknown.
     *
     * @param taskType The Task type in String.
     * @return The value corresponding to its task type in integer. -1 if unknown.
     */
    public static int getTaskTypeInInt(String taskType) {
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
     * Returns a list of task containing all the tasks that
     * was originally in the file.
     * View the getStoredData function to understand more about
     * the data retrieval.
     *
     * @return A list of Task that was saved in the data file.
     */
    public static List<Task> initializeFileAndGetTasks() {
        initializeFile();
        List<String[]> storedTasks = getStoredDataInString();
        List<Task> listOfStoredTasks = new ArrayList<>();
        int iterator = 1;
        while (iterator < totalLinesLoaded) {
            Task newTask = getNewTask(storedTasks.get(iterator));
            if (newTask != null) {
                listOfStoredTasks.add(newTask);
            }
            iterator += 1;
        }
        return listOfStoredTasks;
    }

    /**
     * Returns the created task according to the parameters
     * passed in. Null otherwise.
     * This program takes in a row worth of data from the data
     * file but in an array format instead of CSV. It then
     * parses all the data to get the necessary information
     * to create a Task.
     * Its corresponding task type to integers are,
     * 0 - todo, 1 - deadline, 2 - event, -1 - unknown.
     *
     * @param curTaskString The array of the task's data from the dataFile.
     * @return The Task created according to the data in the array. Null otherwise.
     */
    private static Task getNewTask(String[] curTaskString) {
        int taskType = Integer.parseInt(curTaskString[0]);
        int statusInt = Integer.parseInt(curTaskString[1]);
        Boolean isDone = statusInt >= 1;
        Boolean isNotTodoTask = taskType != 0;
        String description = curTaskString[2];
        String dateInString = curTaskString[3];
        LocalDate date = Parser.getInDateFormat(dateInString);
        if (date == null && isNotTodoTask) {
            System.out.println("Unable to Parse date.");
            return null;
        }
        return createTask(taskType, isDone, description, date);
    }

    /**
     * Returns a Task based on the parameters passed in. Null otherwise.
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
    private static Task createTask(int taskType, boolean isDone, String name, LocalDate date) {
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
     * Returns a List of strings arrays containing the data
     * in the file by reading the dataFile & converts the data
     * from CSV into a list of string array and returns it.
     * Each List element is a row and
     * each array element is a row split along the commas.
     * In the event that a row is un-readable due to IO exception,
     * it will inform the user.
     * Note: Setup the csvReader before using this function.
     *
     * @return The data file's information in a List of arrays.
     */
    private static List<String[]> getStoredDataInString() {
        String curLine;
        List<String[]> allTasks = new ArrayList<>();
        try {
            //Reading the file line by line.
            while ((curLine = csvReader.readLine()) != null) {
                //Split line by comma.
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
     * Setups the the file, file reader, file writer based on the homePath.
     */
    private static void initializeFile() {
        try {
            setWriterAndReaderAndDataPath(homePath);
            writeHeader();
            csvWriter.close();
        } catch (IOException e) {
            System.out.println("Error in accessing/writing file.");
            System.exit(-1);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Writes the header values for the data file.
     * Note to setup the csvWriter before calling this.
     *
     * @throws IOException Thrown in the event of an error while writing.
     */
    private static void writeHeader() throws IOException {
        String header = String.join(",", TITLE);
        csvWriter.append(header);
        csvWriter.append(System.lineSeparator());
        csvWriter.flush();
    }

    /**
     * Sets up the file, file Writer/Reader and DATA_PATH value.
     * If the file is not found, it will
     * create a new file for the user at the specified
     * directory.
     *
     * @param homePath user home directory in String format.
     */
    private static void setWriterAndReaderAndDataPath(String homePath) {
        boolean isFileExist = false;

        try {
            DATA_PATH = Paths.get(homePath, FILE_NAME);
            isFileExist = Files.exists(DATA_PATH);
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

        if (isFileExist) {
            System.out.println("Found data file!");
        } else {
            System.out.println("A new data file has been created at: " + DATA_PATH.toString());
        }
    }
}
