import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class DukeStorage {
    private static final String TASK_IS_EVENT = "E";
    private static final String TASK_IS_TODO = "T";
    private static final String TASK_IS_DEADLINE = "D";
    private static final String TASK_COMPLETED_INT = "1";
    private static final String TASK_NOT_COMPLETED_INT = "0";
    private static final String DATA_DIRECTORY = "../data";
    private static final String TEXT_FILE_DIRECTORY = DATA_DIRECTORY + "/duke.txt";
    private static final String TASK_SPLITTER = " [|] ";
    private static final int INDEX_TASK_TYPE = 0;
    private static final int INDEX_TASK_COMPLETION = 1;
    private static final int INDEX_TASK_DESCRIPTION = 2;
    private static final int INDEX_TASK_DATE = 3;

    private ArrayList<Task> tasks = new ArrayList<Task>();

    public DukeStorage() {
        updateDukeStorageOnStartUp();
    }

    private FileWriter createNewDukeTextFile() {
        FileWriter dukeTextFile = null;
        try {
            dukeTextFile = new FileWriter(TEXT_FILE_DIRECTORY);
        } catch (IOException e) {
            System.out.println("Unable to create duke.txt file in data directory");
        }
        return dukeTextFile;
    }

    private void updateDukeStorageOnStartUp() {
        try {
            File dukeTextFile = new File(TEXT_FILE_DIRECTORY);
            Scanner dukeTextFileReader = new Scanner(dukeTextFile);
            while (dukeTextFileReader.hasNextLine()) {
                String currentTaskString = dukeTextFileReader.nextLine();
                Task currentTask = getTaskFromDatabase(currentTaskString);
                this.tasks.add(currentTask);
            }
            dukeTextFileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Duke text file not found. Creating a new duke text file.\n");
            createNewDataDirectory();
        } catch (InvalidTaskTypeException e) {
            System.out.println("Incorrect task type saved. Duke text file is corrupted.");
            System.out.println("We will create a new empty duke text file.\n");
            FileWriter newDukeTextFile = createNewDukeTextFile();
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Incorrect format of task saved. Duke text file is corrupted.");
            System.out.println("We will create a new empty duke text file.\n");
            FileWriter newDukeTextFile = createNewDukeTextFile();
        }
    }

    /**
     * @return We return the list of all tasks from duke text file
     * */
    public ArrayList<Task> loadTaskList() {
        return this.tasks;
    }

    private Task getTaskFromDatabase(String taskString)
            throws InvalidTaskTypeException, ArrayIndexOutOfBoundsException {
        String[] currentTaskSplit = taskString.split(TASK_SPLITTER);
        String taskIndicator = currentTaskSplit[INDEX_TASK_TYPE];
        String taskIsCompleted = currentTaskSplit[INDEX_TASK_COMPLETION];
        String taskDescription = currentTaskSplit[INDEX_TASK_DESCRIPTION];
        Task taskToReturn;
        switch (taskIndicator) {
            case TASK_IS_TODO:
                Todo todoReturn = new Todo(taskDescription);
                if (taskIsCompleted.equals(TASK_COMPLETED_INT)) {
                    todoReturn.setTaskAsDone();
                } else {
                    todoReturn.setTaskAsUndone();
                }
                taskToReturn = todoReturn;
                break;
            case TASK_IS_DEADLINE:
                String deadlineDate = currentTaskSplit[INDEX_TASK_DATE];
                Deadline deadlineReturn = new Deadline(taskDescription, deadlineDate);
                if (taskIsCompleted.equals(TASK_COMPLETED_INT)) {
                    deadlineReturn.setTaskAsDone();
                } else {
                    deadlineReturn.setTaskAsUndone();
                }
                taskToReturn = deadlineReturn;
                break;
            case TASK_IS_EVENT:
                String eventDate = currentTaskSplit[INDEX_TASK_DATE];
                Event eventReturn = new Event(taskDescription, eventDate);
                if (taskIsCompleted.equals(TASK_COMPLETED_INT)) {
                    eventReturn.setTaskAsDone();
                } else {
                    eventReturn.setTaskAsUndone();
                }
                taskToReturn = eventReturn;
                break;
            default:
                throw new InvalidTaskTypeException();
        }
        return taskToReturn;
    }

    private void createNewDataDirectory() {
        // We create the file object for the data directory
        File dataDirectory = new File(DATA_DIRECTORY);

        // We create the data directory
        boolean dukeTextFileCreated = dataDirectory.mkdirs();
    }

    private String getTaskCompletionInInt(Task task) {
        String taskCompletionInteger;
        if (task.isCompleted()) {
            taskCompletionInteger = TASK_COMPLETED_INT;
        } else {
            taskCompletionInteger = TASK_NOT_COMPLETED_INT;
        }
        return taskCompletionInteger;
    }

    private String cleanTaskToSave(Task task) {
        String taskStringToSave = "";
        if (task instanceof Todo) {
            Todo taskTodo = (Todo) task;
            taskStringToSave = TASK_IS_TODO + " | " +
                    getTaskCompletionInInt(taskTodo) + " | " +
                    taskTodo.getTaskDescription();
        } else if (task instanceof Event) {
            Event taskEvent = (Event) task;
            taskStringToSave = TASK_IS_EVENT + " | " +
                    getTaskCompletionInInt(task) + " | " +
                    taskEvent.getTaskDescription() + " | " +
                    taskEvent.getEventDate();
        } else {
            Deadline taskDeadline = (Deadline) task;
            taskStringToSave = TASK_IS_DEADLINE + " | " +
                    getTaskCompletionInInt(task) + " | " +
                    taskDeadline.getTaskDescription() + " | " +
                    taskDeadline.getDeadlineDate();
        }
        return taskStringToSave;
    }

    /**
     * @param taskList The tasklist to update duke text file
     * We update the duke text file in ../data/duke.txt everytime
     * the tasklist is updated.
     * */
    public void updateStorage(TaskList taskList) {
        try {
            FileWriter dukeTextFile = createNewDukeTextFile();;
            for (int i = 0; i < taskList.getNumberOfTask(); i++) {
                Task currentTask = taskList.getTaskFromIndex(i);
                String taskString = cleanTaskToSave(currentTask);
                dukeTextFile.write(taskString);
                dukeTextFile.write("\n");
            }
            dukeTextFile.close();
        } catch (IOException e) {
            System.out.println("Error saving into the duke text file. " +
                    "Data directory not found. \n" +
                    "Creating a new data directory.");
            createNewDataDirectory();
            FileWriter newDukeTextFile = createNewDukeTextFile();
            System.out.println("Done creating new data directory.");
            updateStorage(taskList);
        }
    }
}
