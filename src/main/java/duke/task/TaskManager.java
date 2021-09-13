package duke.task;

import duke.Message;
import duke.IoManager;
import duke.task.Task.Types;
import duke.exception.ListEmptyException;
import duke.exception.NoDescriptionException;
import duke.exception.WrongNumberOfArgumentsException;

import java.util.ArrayList;

public class TaskManager {
    public static final int MAX_TASKS = 100;

    private static ArrayList<Task> tasks = new ArrayList<Task>(MAX_TASKS);

    private interface Lambda {
        void execute();
    }
    //TODO What do I even call this class?
    private static class Runnable {
        private final int id;

        private Runnable(int id) {
            this.id = id;
        }

        private void run(Lambda lambda) {
            try {
                if (id > tasks.size() - 1) {
                    String message = "That number does not correspond to any task!";
                    throw new ArrayIndexOutOfBoundsException(message);
                }
                lambda.execute();
            } catch (ArrayIndexOutOfBoundsException aiobe) {
                Message.printWithSpacers(aiobe.getMessage());
            }
        }
    }

    public static void taskDone(int id) {
        new Runnable(id).run(() -> tasks.get(id).markAsDone());
    }

    public static void deleteTask(int id) {
        new Runnable(id).run(() -> printTaskDeleted(tasks.remove(id)));
    }

    public static void newTask(String userInput) {
        try {
            String[] userInputSplit = Message.splitWhitespace(userInput);
            if (userInputSplit.length == 1) {
                throw new NoDescriptionException(userInput);
            }
            String command = userInputSplit[0];
            //Remove command from userInput.
            userInput = userInput.replaceAll('^' + command + Message.WHITESPACE_REGEX, "");
            switch (Types.valueOf(command.toUpperCase())) {
            case DEADLINE:
                tasks.add(new Deadline(userInput));
                break;
            case EVENT:
                tasks.add(new Event(userInput));
                break;
            case TODO:
                tasks.add(new Todo(userInput));
                ;
                break;
            }
            printTaskDone(tasks.get(tasks.size() - 1));
        } catch (NoDescriptionException nde) {
            Message.printWithSpacers(nde.getMessage());
        } catch (WrongNumberOfArgumentsException wnoae) {
            Message.printWithSpacers(wnoae.getMessage());
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
                tasks.add(new Deadline(Integer.parseInt(line[1]) == 1, line[2], line[3]));
                break;
            case EVENT:
                tasks.add(new Event(Integer.parseInt(line[1]) == 1, line[2], line[3]));
                break;
            case TODO:
                tasks.add(new Todo(Integer.parseInt(line[1]) == 1, line[2]));
                break;
            }
        }
    }

    public static void saveTasks(){
        String message = "";
        for (Task task : tasks){
            message += task.getFormattedString() + '\n';
        }
        IoManager.overwriteFile(message);
    }

    private static String getTaskModifiedString(Task task){
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
