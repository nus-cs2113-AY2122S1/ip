package Duke;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int numTask = 0;

    public static final String TASK_ADDED_MESSAGE = "The task has been added: \n";
    public static final String NO_SUCH_TASK_MESSAGE = "There is no task by that number \n";
    public static final String TASK_COMPLETE_MESSAGE = "Congrats on finishing a task! Have a cookie!\n";
    public static final String NO_TASKS_MESSAGE = "No Tasks\n";
    public static final String TASK_DELETED_MESSAGE = "The following task has been deleted:\n";

    public TaskManager() {
    }

    /**
     * This section deals with file reading and writing
     */
    public static void processStoredTasks(String taskString) {
        String[] taskDetails = taskString.split("\\|", 4);
        boolean isDone = taskDetails[1].trim().equals("X");
        switch (taskDetails[0].trim()) {
        case "T":
            tasks.add(new Todo(taskDetails[2].trim(), isDone));
            numTask++;
            break;
        case "D":
            String[] dueDate = taskDetails[3].split("by:");
            tasks.add(new Deadline(taskDetails[2].trim(), isDone, dueDate[1].trim()));
            numTask++;
            break;
        case "E" :
            String[] startDate = taskDetails[3].split("at:");
            tasks.add(new Event(taskDetails[2].trim(), isDone, startDate[1].trim()));
            numTask++;
            break;
        default:
            break;
        }
    }

    public static void readFile() throws FileNotFoundException {
        File taskFile = new File("data/tasks.txt");
        Scanner scanner = new Scanner(taskFile);
        if (taskFile.exists() && !taskFile.isDirectory()) {
            while (scanner.hasNext()) {
                processStoredTasks(scanner.nextLine());
            }
        } else {
            return;
        }
    }

    public static void loadTasks() throws FileNotFoundException {
        File taskFile = new File("data");
        boolean isDir = Files.isDirectory(Path.of(taskFile.getAbsolutePath()));
        if (isDir) {
            readFile();
        } else {
            taskFile.mkdir();
        }
    }

    public static void saveTasks() throws IOException {
        FileWriter fw = new FileWriter("data/tasks.txt");
        for (Task t : tasks) {
            fw.write(t.toString() + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * This section contains the actions the user can make
     */
    public String addTodoTask(String description) {
        tasks.add(new Todo(description, false));
        numTask++;
        return TASK_ADDED_MESSAGE + tasks.get(numTask - 1) + "\n";
    }

    public String addDeadlineTask(String task) {
        String[] separator = task.split("/by");
        String description = separator[0].trim();
        String deadline = separator[1].trim();
        tasks.add(new Deadline(description, false,  deadline));
        numTask++;
        return TASK_ADDED_MESSAGE + tasks.get(numTask - 1) + "\n";
    }

    public String addEventTask(String task) {
        String[] separator = task.split("/at");
        String description = separator[0].trim();
        String timing = separator[1].trim();
        tasks.add(new Event(description, false, timing));
        numTask++;
        return TASK_ADDED_MESSAGE + tasks.get(numTask - 1) + "\n";
    }

    public String listTasks() {
        if (numTask == 0) {
            return NO_TASKS_MESSAGE;
        }
        String infoForUser = "Task List:\n";
        for (int i = 0; i < numTask; i++) {
            infoForUser += (i + 1) + ". " + tasks.get(i) + "\n";
        }

        return infoForUser;
    }

    public String markAsDone(int number) {
        if (number > numTask) {
            return NO_SUCH_TASK_MESSAGE;
        }
        tasks.get(number - 1).markAsDone();
        return TASK_COMPLETE_MESSAGE + tasks.get(number - 1) + "\n";
    }

    public String deleteTask(int number) {
        if (number > numTask) {
            return NO_SUCH_TASK_MESSAGE;
        }
        Task removedTask = tasks.get(number - 1);
        tasks.remove(number - 1);
        numTask--;
        return TASK_DELETED_MESSAGE + removedTask + "\n";
    }

    public String showCommandHelp() {
        return "The following is the list of commands: \n" +
                "list \nDisplays the current tasks. \n" +
                "todo <task> \nAdds <task> to the task list \n" +
                "deadline <task> /by <date>\nAdds <task> to task list with deadline <date> \n" +
                "event <task> /at <time>\nAdds <task> to task list with time <time> \n" +
                "done <task index>\nMarks task number <task index> as done. <task index> should be an integer\n" +
                "exit\nExits the program\n";
    }
}
