package FileManager;

import tasks.TaskList;
import tasks.Task;
import tasks.Todo;
import tasks.Event;
import tasks.Deadline;


import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * <h1>Load saved user status</h1>
 * <p>This FildReader provides methods to reload the tasks that a user saved previously.</p>
 *
 */
public class FileReader {
    private Path filePath;

    public FileReader(String userName) {
        String fileName = userName + ".txt";
        filePath = Paths.get(System.getProperty("user.dir"), "UserStatus", fileName);
    }


    /**
     * Load all tasks that a user has saved before.
     *
     * @return {@link TaskList} returns the reloaded task with a {@link TaskList} object.
     */
    public TaskList restore() {
        try {
            Scanner fileReader = new Scanner(filePath);
            TaskList tasks = new TaskList();
            while (fileReader.hasNext()) {
                tasks.addTask(convertToTask(fileReader.nextLine()));
            }
            fileReader.close();
            return tasks;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * checks whether the corresponding file that saves the users' tasks exist or not
     *
     * @return boolean returns true if the file exists, otherwise false.
     */
    public boolean fileExists() {
        return filePath.toFile().exists();
    }

    private Task convertToTask(String taskLine) {
        String[] split = taskLine.split("\\|");
        switch(split[0].strip()) {
        case "T" :
            return new Todo(split[2].strip(), split[1].strip().equals("1"));
        case "D":
            return new Deadline(split[2].strip(), split[3].strip(), split[1].strip().equals("1"));
        case "E":
            return new Event(split[2].strip(), split[3].strip(), split[1].strip().equals("1"));
        default:
            return null;
        }
    }

}
