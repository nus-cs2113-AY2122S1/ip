package duke.task;

import duke.Message;
import duke.IoManager;
import duke.Parser;
import duke.task.Task.Type;
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

    private static String parseUserInput(String userInput) throws NoDescriptionException {
        String[] userInputSplit = Parser.splitWhitespace(userInput);
        if (userInputSplit.length == 1) {
            throw new NoDescriptionException(userInput);
        }
        //Remove command from userInput.
        return userInput.replaceAll('^' + userInputSplit[0] + Parser.WHITESPACE_REGEX, "");
    }

    private static Type getCmdFromUserInput(String userInput) {
        String commandString = Parser.getFirstArg(userInput);
        return Type.valueOf(commandString.toUpperCase());
    }

    public static void newTask(String userInput) {
        try {
            Type command = getCmdFromUserInput(userInput);
            userInput = parseUserInput(userInput);
            if (command == Type.TODO) {
                tasks.add(new Todo(userInput));
            } else {
                tasks.add(TimedTask.newTimedTask(command, userInput));
            }
            printTaskDone(tasks.get(tasks.size() - 1));
            saveTasks();
        } catch (NoDescriptionException nde) {
            Message.printWithSpacers(nde.getMessage());
        } catch (WrongNumberOfArgumentsException wnoae) {
            Message.printWithSpacers(wnoae.getMessage());
        }
    }

    private static Type getTaskFromLoadedTask(String[] loadedTaskSplit) throws IllegalArgumentException, WrongNumberOfArgumentsException{
        Type taskType = loadedTaskSplit[0].length() == 1 ? Type.getType(loadedTaskSplit[0].charAt(0)) : null;
        if (taskType == null) {
            throw new IllegalArgumentException();
        }
        if (taskType.NUM_ARGS != loadedTaskSplit.length - 1) {
            throw new WrongNumberOfArgumentsException();
        }
        return taskType;
    }

    public static void loadTasks() throws IllegalArgumentException, FileNotFoundException, WrongNumberOfArgumentsException {
        for (String loadedTaskSplit[] : IoManager.loadFile()) {
            Type taskType = getTaskFromLoadedTask(loadedTaskSplit);
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

    private static void printTaskDone(Task task) {
        Message.printWithSpacers("Got it, I've added this task:\n" + getTaskModifiedString(task));
    }

    private static void printTaskDeleted(Task task) {
        Message.printWithSpacers("Noted. I've removed this task:\n" + getTaskModifiedString(task));
    }

    public static void printTasks() {
        try {
            if (tasks.size() == 0) {
                throw new ListEmptyException();
            }
            int count = 1;
            String message = "";
            for (Task task : tasks) {
                message += String.format("%d.%s\n", count++, task);
            }
            Message.printWithSpacers(message);
        } catch (ListEmptyException lee) {
            Message.printWithSpacers(lee.getMessage());
        }
    }
}
