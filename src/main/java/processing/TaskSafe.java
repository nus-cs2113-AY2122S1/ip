package processing;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

/*------- Local imports --------*/
import exceptions.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.TaskType;

public class TaskSafe {

    /*------------ PUBLIC STATIC VARIABLES --------- */
    private static final String DATA_PATH = "/data/duke.txt";
    private static final String rootPath = new File(".").getAbsolutePath();
    private static final String fullPath = rootPath + DATA_PATH;

    /*-------------- SAVE ------------ */
    private static void appendToFile(Task task) throws DukeException{
        try {
            FileWriter fw = new FileWriter(fullPath,true);
            char isDone = task.isDone() ? '1' : '0';
            String taskToAdd =
                    task.getTaskChar() + "|" + isDone + "|" + task.getFullDescription() + '\n';
            fw.write(taskToAdd);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new DukeException("Failed to save following task: \n" + task);
        }
    }

    /**
     * Saves a task list to a file and overwrites what is in it
     * @param taskList list of tasks to save to file
     */
    public static void saveToFile(ArrayList<Task> taskList) {
        try {
            new FileWriter(fullPath,false).close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Task t : taskList) {
            try {
                appendToFile(t);
            } catch (DukeException e) {
                e.printStackTrace();
            }
        }
    }

    /*-------------- LOAD ------------ */
    public static void loadFromFile( TaskManager taskManager) {
        File file = new File(fullPath);
        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                parseTask(fileScanner.nextLine(),taskManager);
            }
        } catch (IOException | DukeException e) {
            System.out.println(e);
        }
        taskManager.listTasks();
        System.out.println("You now have (" + taskManager.getTaskSize() + ") tasks!" );

    }

    private static void parseTask(String task, TaskManager taskManager) throws DukeException {
        // Parse the string
        TaskType t = TaskType.TODO;
        boolean isDone;
        String descriptor = "";
        try {
            String[] split = task.split("\\|");
            switch (split[0]) {
            case "T":
                descriptor = "todo " + split[2];
                break;
            case "D":
                t = TaskType.DEADLINE;
                descriptor = split[2].replace(Deadline.DEADLINE_BY,TaskManager.DEADLINE_CLAUSE);
                descriptor = descriptor.trim().replaceAll(".$",""); //remove last ')'
                descriptor = "deadline " + descriptor;
                break;
            case "E":
                t = TaskType.EVENT;
                descriptor = split[2].replace(Event.EVENT_AT,TaskManager.EVENT_CLAUSE);
                descriptor = descriptor.trim().replaceAll(".$",""); //remove last )
                descriptor = "event " + descriptor;
                break;
            }
            isDone = !split[1].equals("0");
            CommandHandler command = new CommandHandler(descriptor);
            taskManager.addTask(command,t,isDone,false);
        } catch (PatternSyntaxException | DukeException e) {
            System.out.println(e);
            throw new DukeException("Corrupted task syntax from file, unable to parse");
        }
    }
}
