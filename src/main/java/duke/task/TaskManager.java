package duke.task;

import duke.Message;
import duke.IoManager;
import duke.Parser;
import duke.exception.LoadTaskException;
import duke.exception.ListEmptyException;
import duke.exception.NoDescriptionException;
import duke.exception.WrongNumberOfArgumentsException;

import java.io.FileNotFoundException;
import java.util.ArrayList;


/**
 * Manages tasks that are created in an ArrayList of <code>tasks</code>
 * and provides functionality such as create, read and delete.
 */
public class TaskManager {

    public static final int MAX_TASKS = 100;
    private static final String ARRAY_OUT_OF_BOUNDS_EXCEPTION_MESSAGE = "That number does not correspond to any task!";
    public static final String TASK_MODIFIED_MESSAGE = "%s\nNow you have %d tasks in the list.";
    public static final String TASK_CREATED_MESSAGE = "Got it, I've added this task:\n";
    public static final String TASK_DELETED_MESSAGE = "Noted. I've removed this task:\n";
    public static final String PRINT_INDIVIDUAL_TASK_REGEX = "%d.%s\n";
    public static final String FOUND_TASKS_MESSAGE = "Here are the matching tasks in your list:\n";

    private static ArrayList<Task> tasks = new ArrayList<>(MAX_TASKS);

    /**
     * A class to be passed into a function to allow lambda code to be executed
     */
    private interface Lambda {
        void execute();
    }

    /**
     * A function that is used by both <code>taskDone()</code> and <code>deleteTask()</code>.
     * <code>lambda.execute()</code> is replaced by the lambda function that is passed
     * into the <code>run()</code> function.
     * @param id index of the task that is to be modified.
     */
    //This allows both functions to 'share code' preventing errors
    // where one function code was modified but the other was forgotten.
    private static class runTasks {
        private final int id;

        private runTasks(int id) {
            this.id = id;
        }

        private void run(Lambda lambda) {
            try {
                if (id > tasks.size() - 1) {
                    throw new ArrayIndexOutOfBoundsException(ARRAY_OUT_OF_BOUNDS_EXCEPTION_MESSAGE);
                }
                //replaced function
                lambda.execute();
                saveTasks();
            } catch (ArrayIndexOutOfBoundsException aiobe) {
                Message.printWithSpacers(aiobe.getMessage());
            }
        }

    }

    /**
     * Marks the task indicated by <code>id</code> as done.
     * @param id index of the task that is to be marked as done.
     */
    public static void taskDone(int id) {
        new runTasks(id)
                .run(() -> tasks.get(id).markAsDone());
    }

    /**
     * Deletes the task indicated by <code>id</code>.
     * @param id index of the task that is to be deleted.
     */
    public static void deleteTask(int id) {
        new runTasks(id)
                .run(() -> printTaskDeleted(tasks.remove(id)));
    }

    /**
     * Gets the task type based off of the first string in user input.
     * @param userInput user input of user to console
     * @return Task type entered by user
     */
    private static Type getTaskTypeFromUserInput(String userInput) {
        String commandString = Parser.getFirstArgument(userInput);
        return Type.valueOf(commandString.toUpperCase());
    }

    /**
     * Gets the arguments (strings after the first argument) entered by user.
     * @param userInput user input of user to console
     * @return Arguments after first command and trailing whitespace
     */
    private static String getCommandArguments(String userInput) throws NoDescriptionException {
        String arguments = Parser.removeFirstArgument(userInput);
        if (arguments.length() == 0) {
            throw new NoDescriptionException(userInput);
        }
        //Removes command from userInput.
        return arguments;
    }

    /**
     * User input handler for creation of new tasks.
     * Gets the user input, passes it to the appropriate functions for parsing
     * and calls the appropriate function to insert new task, <code>Todo()</code>
     * constructor for todo and <code>newTimedTask()</code> for creating a timed task
     * (deadline or event).
     * Prints the new task created and finally saves the tasks.
     * @param userInput user input of user to console
     */
    public static void newTask(String userInput) {
        try {
            Type command = getTaskTypeFromUserInput(userInput);
            userInput = getCommandArguments(userInput);
            Task createdTask;
            if (command == Type.TODO) {
                createdTask = new Todo(userInput);
            } else {
                createdTask = TimedTask.newTimedTask(command, userInput);
            }
            tasks.add(createdTask);
            printTaskCreated(createdTask);
            saveTasks();
        } catch (NoDescriptionException nde) {
            Message.printWithSpacers(nde.getMessage());
        } catch (WrongNumberOfArgumentsException wnoae) {
            Message.printWithSpacers(wnoae.getMessage());
        }
    }

    /**
     * Gets the task type based off the loaded file from the save file.
     * @param loadedTaskSplit a single loaded task that has been split by the separator used in save file.
     * @return Task type in save file for respective task.
     * @throws LoadTaskException If task type invalid or there are the wrong number of arguments.
     */
    private static Type getTaskTypeFromLoadedTask(String[] loadedTaskSplit) throws LoadTaskException {
        Type taskType = Type.getType(loadedTaskSplit[0]);
        boolean isInvalidTask = taskType == null;
        boolean hasWrongNumberOfArguments  = taskType.NUMBER_OF_ARGUMENTS != loadedTaskSplit.length - 1;
        if (isInvalidTask || hasWrongNumberOfArguments) {
            throw new LoadTaskException();
        }
        return taskType;
    }

    /**
     * For all the tasks in the safe file, function split them and pass them into
     * <code>getTaskTypeFromLoadedTask()</code> to get task type.
     * With task type, it will add the respective task to <code>tasks</code> ArrayList.
     * @throws IllegalArgumentException If <code>parseInt()</code> fails.
     * @throws FileNotFoundException throws from <code>loadFile()</code> function.
     * @throws LoadTaskException throws from <code>getTaskTypeFromLoadedTask()</code> function.
     */
    public static void loadTasks() throws IllegalArgumentException, FileNotFoundException, LoadTaskException {
        for (String loadedTask : IoManager.loadFile()) {
            String[] loadedTaskSplit = Parser.splitPipe(loadedTask);
            Type taskType = getTaskTypeFromLoadedTask(loadedTaskSplit);
            boolean isDone = Integer.parseInt(loadedTaskSplit[1]) == 1;
            switch (taskType) {
            case DEADLINE:
                tasks.add(new Deadline(isDone, loadedTaskSplit[2], loadedTaskSplit[3]));
                break;
            case EVENT:
                tasks.add(new Event(isDone, loadedTaskSplit[2], loadedTaskSplit[3]));
                break;
            case TODO:
                tasks.add(new Todo(isDone, loadedTaskSplit[2]));
                break;
            }
        }
    }

    /**
     * Creates the string to b written to save file
     * to save all modified tasks.
     */
    public static void saveTasks() {
        String message = "";
        for (Task task : tasks) {
            message += task.getFormattedString() + '\n';
        }
        IoManager.overwriteFile(message);
    }

    private static String getTaskModifiedString(Task task) {
        return String.format(TASK_MODIFIED_MESSAGE, task, tasks.size());
    }

    private static void printTaskCreated(Task task) {
        Message.printWithSpacers(TASK_CREATED_MESSAGE + getTaskModifiedString(task));
    }

    private static void printTaskDeleted(Task task) {
        Message.printWithSpacers(TASK_DELETED_MESSAGE + getTaskModifiedString(task));
    }

    /**
     * prints all current tasks in <code>tasks</code> ArrayList
     */
    public static void printTasks() {
        printTasks("", tasks);
    }

    /**
     * prints all current tasks in <code>tasksToPrint</code> ArrayList
     * with <code>message</code> appended in front.
     * @param message message to be appended in front of tasks to be printed.
     * @param tasksToPrint ArrayList of tasks to be printed.
     */
    public static void printTasks(String message, ArrayList<Task> tasksToPrint) {
        try {
            if (tasksToPrint.size() == 0) {
                throw new ListEmptyException();
            }
            int count = 1;
            for (Task task : tasksToPrint) {
                message += String.format(PRINT_INDIVIDUAL_TASK_REGEX, count++, task);
            }
            Message.printWithSpacers(message);
        } catch (ListEmptyException lee) {
            Message.printWithSpacers(lee.getMessage());
        }
    }

    /**
     * prints tasks found to have the description
     * @param description description of tasks to be printed from <code>tasks</code> ArrayList.
     */
    public static void findTasks(String description) {
        ArrayList<Task> tasksToPrint = new ArrayList<>(MAX_TASKS);
        for (Task task : tasks) {
            if (task.getDescription().contains(description)) {
                tasksToPrint.add(task);
            }
        }
        printTasks(FOUND_TASKS_MESSAGE, tasksToPrint);
    }

}
