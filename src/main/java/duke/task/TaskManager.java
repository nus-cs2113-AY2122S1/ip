package duke.task;

import duke.Message;
import duke.IoManager;
import duke.task.Task.Types;
import duke.exception.ListEmptyException;
import duke.exception.NoDescriptionException;
import duke.exception.TooManyTasksException;
import duke.exception.WrongNumberOfArgumentsException;

import java.util.Arrays;

public class TaskManager {
    public static final int MAX_TASKS = 100;

    private static int taskCount = 0;
    private static Task[] tasks = new Task[MAX_TASKS];

    public static void taskDone(int id) {
        try {
            if (id > taskCount - 1) {
                String message = "That number does not correspond to any task!";
                throw new ArrayIndexOutOfBoundsException(message);
            }
            Task currentTask = tasks[id];
            currentTask.markAsDone();
        } catch (ArrayIndexOutOfBoundsException aiobe) {
            Message.printWithSpacers(aiobe.getMessage());
        }
        saveTasks();
    }

    public static void newTask(String userInput) {
        try {
            String[] userInputSplit = Message.splitWhitespace(userInput);
            if (userInputSplit.length == 1) {
                throw new NoDescriptionException(userInput);
            }
            if (taskCount >= MAX_TASKS) {
                throw new TooManyTasksException();
            }
            String command = userInputSplit[0];
            //Remove command from userInput.
            userInput = userInput.replaceAll('^' + command + Message.WHITESPACE_REGEX, "");
            switch (Types.valueOf(command.toUpperCase())) {
            case DEADLINE:
                tasks[taskCount] = new Deadline(userInput);
                break;
            case EVENT:
                tasks[taskCount] = new Event(userInput);
                break;
            case TODO:
                tasks[taskCount] = new Todo(userInput);
                break;
            }
            printInputReceived(tasks[taskCount++]);
        } catch (NoDescriptionException nde) {
            Message.printWithSpacers(nde.getMessage());
        } catch (WrongNumberOfArgumentsException wnoae) {
            Message.printWithSpacers(wnoae.getMessage());
        } catch (TooManyTasksException tmte) {
            Message.printWithSpacers(tmte.getMessage());
        }
        saveTasks();
    }
    public static void loadTasks() throws IllegalArgumentException {
        for(String loadedTask : IoManager.loadFile()) {
            String[] line = loadedTask.split("\\|");
            Types taskType = Types.getType(line[0].charAt(0));
            if (taskType == null) {
                throw new IllegalArgumentException("Save file has been corrupted");
            }
            switch (taskType) {
            case DEADLINE:
                tasks[taskCount++] = new Deadline(Integer.parseInt(line[1]) == 1, line[2], line[3]);
                break;
            case EVENT:
                tasks[taskCount++] = new Event(Integer.parseInt(line[1]) == 1, line[2], line[3]);
                break;
            case TODO:
                tasks[taskCount++] = new Todo(Integer.parseInt(line[1]) == 1, line[2]);
                break;
            }
        }
    }

    public static void saveTasks(){
        String message = "";
        for (Task task : Arrays.copyOf(tasks, taskCount)){
            message += task.getFormattedString() + '\n';
        }
        IoManager.overwriteFile(message);
    }

    public static void printInputReceived(Task task) {
        Message.printWithSpacers(String.format("Got it, I've added this task:\n%s\n" +
                "Now you have %d tasks in the list.", task, taskCount));
    }

    public static void printTasks() {
        try {
            if (taskCount == 0) {
                throw new ListEmptyException();
            }
            int count = 1;
            String message = "";
            for (Task task : Arrays.copyOf(tasks, taskCount)) {
                message += String.format("%d.%s\n", count++, task);
            }
            Message.printWithSpacers(message);
        } catch (ListEmptyException lee) {
            Message.printWithSpacers(lee.getMessage());
        }
    }
}
