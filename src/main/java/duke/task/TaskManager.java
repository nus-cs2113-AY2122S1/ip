package duke.task;

import duke.Duke;
import duke.task.exception.InvalidParameterException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskManager {

    /* Types of tasks */
    public static final char CHAR_TYPE_TODO = 'T';
    public static final char CHAR_TYPE_DEADLINE = 'D';
    public static final char CHAR_TYPE_EVENT = 'E';

    /* Types of string split regex  */
    public static final String EVENT_STRING_SPLIT_REGEX = "/at ";
    public static final String DEADLINE_STRING_SPLIT_REGEX = "/by ";
    public static final String DATA_TEXT_SEPERATOR = ",";

    /* Error messages */
    private static final String DEADLINE_ERROR_MESSAGE = "Sorry please input a valid DEADLINE TASK with date and time "
            + "by using <description> /by <date and time>";
    private static final String EVENT_ERROR_MESSAGE = "Sorry please input a EVENT TASK with date and time by using "
            + "<description> /at <date time>";
    private static final String EMPTY_DESCRIPTION_ERROR_MESSAGE = "Please do not input an empty description.\n"
            + "Input a TODO/DEADLINE/EVENT TASK with a valid description by using\n"
            + "TODO <description>\n"
            + "DEADLINE <description> /by <date and time>\n"
            + "EVENT <description> /at <date and time>";
    private static final String TASK_DOES_NOT_EXIST_MESSAGE = "Sorry, task selected does not exist! Please double "
            + "check if task number exist with the list command.";
    private static final String DELETE_TASK_MESSAGE = "Why would you delete the following task? But anyways I have "
            + "removed the following task.\n"
            + "%s\n"
            + "Now you have left %d tasks in the list.";
    private static final String COMPLETE_TASK_MESSAGE = "Good lad, you have finally completed the task you needed "
            + "to do.\n"
            + "%s";
    private static final String ADD_TASK_MESSAGE = "Gaben have seen and will add the following task for you:\n"
            + "%s\n"
            + "You now have %d task in the list";
    private static final String FILE_IO_ERROR_MESSAGE = "Sorry, there was some issues regarding the data files.";


    /* List of tasks */
    private final ArrayList<Task> tasksList;

    /* Constructor for task manager */
    public TaskManager() {
        tasksList = new ArrayList<Task>();
        Duke.printMessage("Gaben's Task Manager is here to assist you!");
    }

    /**
     * Creates a new task based on type given
     *
     * @param userParameter Description of task and its parameter given by user
     * @param type          type of task
     * @return return task
     * @throws InvalidParameterException If deadline/event task do not have the corresponding regex such as /by and /at
     *                                   or when no description is given after the regex
     */
    public Task createTask(String userParameter, char type) throws InvalidParameterException {
        Task task = null;
        String[] userArguments = null;
        boolean regexNotFound = false;
        boolean emptyDescription = false;
        switch (type) {
        case CHAR_TYPE_TODO:
            task = new Todo(userParameter);
            break;
        case CHAR_TYPE_EVENT:
            userArguments = userParameter.split(EVENT_STRING_SPLIT_REGEX, 2);
            regexNotFound = userArguments.length != 2;
            emptyDescription = userArguments[0].isBlank();
            if (emptyDescription || regexNotFound) {
                throw new InvalidParameterException(EVENT_ERROR_MESSAGE);
            }
            task = new Event(userArguments[0], userArguments[1]);
            break;
        case CHAR_TYPE_DEADLINE:
            userArguments = userParameter.split(DEADLINE_STRING_SPLIT_REGEX, 2);
            regexNotFound = userArguments.length != 2;
            emptyDescription = userArguments[0].isBlank();
            if (emptyDescription || regexNotFound) {
                throw new InvalidParameterException(DEADLINE_ERROR_MESSAGE);
            }
            task = new Deadline(userArguments[0], userArguments[1]);
            break;
        }
        return task;
    }


    /**
     * Adds a newly created task with given task description and type to tasks list
     *
     * @param userInputArray Command and parameters given by user
     * @param type           Type of task given by user input
     */
    public void addTask(String[] userInputArray, TaskType type) {

        try {
            boolean emptyDescription = userInputArray[1].isBlank();
            if (emptyDescription) {
                Duke.printMessage(EMPTY_DESCRIPTION_ERROR_MESSAGE);
                return;
            }

            // Create tasks
            Task task = null;
            switch (type) {
            case TODO:
                task = createTask(userInputArray[1], CHAR_TYPE_TODO);
                break;
            case EVENT:
                task = createTask(userInputArray[1], CHAR_TYPE_EVENT);
                break;
            case DEADLINE:
                task = createTask(userInputArray[1], CHAR_TYPE_DEADLINE);
                break;
            }

            // As long as task is valid, add to task list and inform user
            if (task != null) {
                tasksList.add(task);
                Duke.printMessage(String.format(ADD_TASK_MESSAGE, task, tasksList.size()));
            }

        } catch (IndexOutOfBoundsException e) {
            Duke.printMessage(EMPTY_DESCRIPTION_ERROR_MESSAGE);
        } catch (InvalidParameterException e) {
            Duke.printMessage(e.getMessage());
        }

    }

    /**
     * Prints the entire list of tasks currently in tasks list. Will let user know if there is no task in tasks list.
     */
    public void listTask() {
        int tasksListSize = tasksList.size();
        String message = "";
        if (tasksListSize == 0) {
            message = "Oh! You have no tasks left!";
        }
        else{
            message = "Total of " + tasksListSize + " task(s)\n";
            int counter = 1;
            for (Task task : tasksList) {
                message += String.format("%d.%s\n", counter, task.toString());
                counter++;
            }
        }
        Duke.printMessage(message);
    }

    /**
     * Check if task exist and returns index
     *
     * @param taskNumber Task Number given by user
     * @return Corresponding task index fpr task number
     * @throws InvalidParameterException If task index does not exist in the current task list
     */
    public int getIndexOfTask(int taskNumber) throws InvalidParameterException {
        boolean isNotWithinSizeLimit = taskNumber < 1 || taskNumber > tasksList.size();
        if (isNotWithinSizeLimit) {
            throw new InvalidParameterException(TASK_DOES_NOT_EXIST_MESSAGE);
        }
        return taskNumber - 1;
    }

    /**
     * Set the task to be completed by marking it done.
     *
     * @param taskNumber task number given by user
     */
    public void completeTask(int taskNumber) {
        try {
            int taskNumberIndex = getIndexOfTask(taskNumber);
            Task task = tasksList.get(taskNumberIndex);
            task.markAsDone();
            Duke.printMessage(String.format(COMPLETE_TASK_MESSAGE, task));
        } catch (InvalidParameterException e) {
            Duke.printMessage(e.getMessage());
        }
    }

    /**
     * Delete task as request by user.
     *
     * @param taskNumber task number given by user
     */
    public void deleteTask(int taskNumber) {
        try {
            int taskNumberIndex = getIndexOfTask(taskNumber);
            Task task = tasksList.get(taskNumberIndex);
            tasksList.remove(taskNumberIndex);
            Duke.printMessage(String.format(DELETE_TASK_MESSAGE, task, tasksList.size()));
        } catch (InvalidParameterException e) {
            Duke.printMessage(e.getMessage());
        }
    }

    /**
     * Save all task data to ./data/task.txt
     */
    public void saveData() {
        try {
            File taskFileDirectory = new File("data");
            File taskFile = new File("data" + File.separator + "task.txt");
            // Create directory and file
            if (!taskFileDirectory.exists()) {
                taskFileDirectory.mkdir();
            }
            if (!taskFile.exists()) {
                taskFile.createNewFile();
            }
            FileWriter myWriter = new FileWriter("data" + File.separator + "task.txt");
            for (Task task : tasksList) {
                myWriter.write(task.toSave() + "\n");
            }
            myWriter.close();

        } catch (IOException e) {
            Duke.printMessage(FILE_IO_ERROR_MESSAGE);
        }

    }

    /**
     * Load all task data from ./data/task.txt to task manager
     */
    public void loadData() {
        try {
            File taskFile = new File("data" + File.separator + "task.txt");
            if (taskFile.exists()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(taskFile));
                String line;
                Task task;
                while ((line = bufferedReader.readLine()) != null) {
                    String[] userInputArray = line.split(DATA_TEXT_SEPERATOR);
                    boolean isDone = Integer.parseInt(userInputArray[1]) == 1;
                    switch (userInputArray[0].charAt(0)) {
                    case CHAR_TYPE_TODO:
                        task = new Todo(userInputArray[2]);
                        task.setIsDone(isDone);
                        tasksList.add(task);
                        break;
                    case CHAR_TYPE_DEADLINE:
                        task = new Deadline(userInputArray[2], userInputArray[3]);
                        task.setIsDone(isDone);
                        tasksList.add(task);
                        break;
                    case CHAR_TYPE_EVENT:
                        task = new Event(userInputArray[2], userInputArray[3]);
                        task.setIsDone(isDone);
                        tasksList.add(task);
                        break;
                    }
                }
            }

        } catch (IOException e) {
            Duke.printMessage(FILE_IO_ERROR_MESSAGE);
        }

    }

}
