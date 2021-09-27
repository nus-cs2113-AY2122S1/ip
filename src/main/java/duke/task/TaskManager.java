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

public class TaskManager {
    public static final int MAX_TASKS = 100;

    private static ArrayList<Task> tasks = new ArrayList<>(MAX_TASKS);

    //A class to be passed into a function to allow lambda code to be executed
    private interface Lambda {
        void execute();
    }

    //TODO Add comment
    private static class runTasks {
        private final int id;

        private runTasks(int id) {
            this.id = id;
        }

        private void run(Lambda lambda) {
            try {
                if (id > tasks.size() - 1) {
                    String message = "That number does not correspond to any task!";
                    throw new ArrayIndexOutOfBoundsException(message);
                }
                lambda.execute();
                saveTasks();
            } catch (ArrayIndexOutOfBoundsException aiobe) {
                Message.printWithSpacers(aiobe.getMessage());
            }
        }

    }

    public static void taskDone(int id) {
        new runTasks(id)
                .run(() -> tasks.get(id).markAsDone());
    }

    public static void deleteTask(int id) {
        new runTasks(id)
                .run(() -> printTaskDeleted(tasks.remove(id)));
    }

    private static Type getCommandFromUserInput(String userInput) {
        String commandString = Parser.getFirstArgument(userInput);
        return Type.valueOf(commandString.toUpperCase());
    }

    private static String getCommandArguments(String userInput) throws NoDescriptionException {
        String arguments = Parser.removeFirstArgument(userInput);
        if (arguments.length() == 0) {
            throw new NoDescriptionException(userInput);
        }
        //Removes command from userInput.
        return arguments;
    }

    public static void newTask(String userInput) {
        try {
            Type command = getCommandFromUserInput(userInput);
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

    private static Type getTaskTypeFromLoadedTask(String[] loadedTaskSplit) throws LoadTaskException {
        Type taskType = Type.getType(loadedTaskSplit[0]);
        boolean isInvalidTask = taskType == null;
        boolean hasWrongNumberOfArguments  = taskType.NUM_ARGS != loadedTaskSplit.length - 1;
        if (isInvalidTask || hasWrongNumberOfArguments) {
            throw new LoadTaskException();
        }
        return taskType;
    }

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

    public static void saveTasks() {
        String message = "";
        for (Task task : tasks) {
            message += task.getFormattedString() + '\n';
        }
        IoManager.overwriteFile(message);
    }

    private static String getTaskModifiedString(Task task) {
        return String.format("%s\nNow you have %d tasks in the list.", task, tasks.size());
    }

    private static void printTaskCreated(Task task) {
        Message.printWithSpacers("Got it, I've added this task:\n" + getTaskModifiedString(task));
    }

    private static void printTaskDeleted(Task task) {
        Message.printWithSpacers("Noted. I've removed this task:\n" + getTaskModifiedString(task));
    }

    public static void printTasks() {
        printTasks("", tasks);
    }

    public static void printTasks(String message, ArrayList<Task> tasksToPrint) {
        try {
            if (tasksToPrint.size() == 0) {
                throw new ListEmptyException();
            }
            int count = 1;
            for (Task task : tasksToPrint) {
                message += String.format("%d.%s\n", count++, task);
            }
            Message.printWithSpacers(message);
        } catch (ListEmptyException lee) {
            Message.printWithSpacers(lee.getMessage());
        }
    }

    public static void findTasks(String description) {
        ArrayList<Task> tasksToPrint = new ArrayList<>(MAX_TASKS);
        for (Task task : tasks) {
            if (task.getDescription().contains(description)) {
                tasksToPrint.add(task);
            }
        }
        printTasks("Here are the matching tasks in your list:\n", tasksToPrint);
    }

}
