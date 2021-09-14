package duke.task;

import duke.FileManager;
import duke.exception.DirectoryCreationException;
import duke.exception.IllegalParameterException;
import java.util.ArrayList;
import java.util.Objects;

public class TaskManager {

    private final ArrayList<Task> taskList;
    private final FileManager fileManager;

    private static final int SAVE_TYPE_LOC = 0;
    private static final int SAVE_DONE_LOC = 1;
    private static final int SAVE_DESCRIPTION_LOC = 2;
    private static final int SAVE_DEADLINE_LOC = 3;

    /* Constructor for Taskmanger */
    public TaskManager() throws DirectoryCreationException {
        taskList = new ArrayList<Task>();
        fileManager = new FileManager();
    }

    /**
     * Adds a new task of type Todo to the task manager.
     *
     * @param task     task to be added to the task manager
     * @param isSilent if true, does not print add response to terminal
     */
    public void addTask(Task task, boolean isSilent) {

        if (task != null) {
            taskList.add(task);

            if (!isSilent) {
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + task.toFormattedString());

                if (taskList.size() == 1) {
                    System.out.printf("There is now %d task in the list%n", taskList.size());
                } else {
                    System.out.printf("There are now %d tasks in the list%n", taskList.size());
                }
            }

        }

        fileManager.saveTasklistToFile(taskList);

    }

    /**
     * Overloads addTask to provide a default value for isSilent
     *
     * @param task task to be added to the task manager
     */
    public void addTask(Task task) {
        addTask(task, false);
    }

    /**
     * Loads tasklist from savefile
     */
    public void loadTasklistFromFile() {
        ArrayList<String> fileLines = fileManager.readTasklistFromFile();

        for (String line : fileLines) {
            Task task;
            String[] taskData = line.split("\\|");

            char taskType = taskData[SAVE_TYPE_LOC].trim().charAt(0);
            String taskDescription = taskData[SAVE_DESCRIPTION_LOC].trim();
            boolean isDone = taskData[SAVE_DONE_LOC].trim().equals("1");
            String taskDeadline = null;

            if (taskData.length >= 4) {
                taskDeadline = taskData[SAVE_DEADLINE_LOC].trim();
            }

            switch (taskType) {
            case TaskType.TODO:
                task = new Todo(taskDescription);
                break;
            case TaskType.DEADLINE:
                task = new Deadline(taskDescription, taskDeadline);
                break;
            case TaskType.EVENT:
                task = new Event(taskDescription, taskDeadline);
                break;
            default:
                System.out.println("An invalid task type has been detected");
                return;
            }

            if (isDone) {
                task.setDone();
            }

            addTask(task, true);
        }

    }

    /**
     * Processes user input and adds a new task of type Todo to the task manager.
     *
     * @param taskDescription Name of the task to add.
     * @param taskType        Type of task to add, defined in TaskType.
     * @throws IllegalParameterException Exception is thrown if an illegal parameter was passed
     */
    public void processAndAddTask(String taskDescription, char taskType) throws IllegalParameterException {

        Task task;
        String[] userInput;

        switch (taskType) {
        case TaskType.TODO:
            task = new Todo(processString(taskDescription));
            break;
        case TaskType.DEADLINE:
            userInput = taskDescription.split("/by", 2);
            task = new Deadline(processString(userInput[0]), processString(userInput[1]));
            break;
        case TaskType.EVENT:
            userInput = taskDescription.split("/at", 2);
            task = new Event(processString(userInput[0]), processString(userInput[1]));
            break;
        default:
            System.out.println("An invalid task type has been detected");
            return;
        }

        addTask(task);

    }

    /**
     * Deletes the task at selected index, removing it from the list.
     *
     * @param index index of the task to be deleted.
     */
    public void deleteTask(int index){
        index--;
        Task task = taskList.get(index);
        taskList.remove(index);
        System.out.println("I have erased this task from existence:");
        System.out.println("  " + task.toFormattedString());

        fileManager.saveTasklistToFile(taskList);
    }

    /* Prints a list of all tasks in task manager */
    public void listTasks() {
        if (taskList.size() <= 0) {
            System.out.println("The list, just like your head, is empty.");
        } else {
            System.out.println(
                    "Here is the list of the things your feeble human mind is incapable of keeping track of:");

            int index = 1;

            for (Task task : taskList) {
                System.out.printf("%d.%s%n", index, task.toFormattedString());
                index++;
            }
        }
    }

    /**
     * Marks the task at selected index as done.
     *
     * @param index index of the task to be marked as done.
     */
    public void markDone(int index) {
        Task task = taskList.get(--index);

        task.setDone();
        System.out.println("The task has been marked as done. No need to thank me.");
        System.out.println(task.toFormattedString());

        fileManager.saveTasklistToFile(taskList);
    }

    /**
     * Performs some processing on user inputs
     *
     * @param toProcess String to be processed
     * @return Processed strong
     * @throws IllegalParameterException exception is thrown if parameter is empty
     */
    private String processString(String toProcess) throws IllegalParameterException {
        String processed = toProcess.trim();
        processed = processed.replace("|", "");

        if (processed.isEmpty()) {
            throw new IllegalParameterException("Empty parameter");
        }

        return processed;
    }
}

