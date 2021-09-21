import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.ArrayList;

public class Storage {

    public final static String MESSAGE_INVALID_FILE = "File cannot be found.";
    public final static String MESSAGE_INVALID_WRITE = "File cannot be written";
    public final String path;

    public Storage (String filePath) {
        this.path = filePath;
    }
    public TaskList load() throws DukeException {
        final TaskList taskLists = new TaskList();
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }
        try {
            Scanner fileReader = new Scanner(file);
            while(fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                if (line.isEmpty()) {
                    continue;
                }
                Task task = decodeTask(line);
                taskLists.add(task);
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            throw new DukeException(MESSAGE_INVALID_FILE);
        }
        return taskLists;
    }

    public void write(TaskList tasksList) throws DukeException{
        try {
            File file = new File(path);
            FileWriter fileWriter = new FileWriter(file);

            for (int i = 0; i < tasksList.size(); i += 1) {
                Task task = tasksList.get(i);
                fileWriter.write(String.format("%s\n", encodeTask(task.toString())));
            }
            fileWriter.close();
        } catch (IOException e) {
          throw new DukeException(MESSAGE_INVALID_WRITE);
        }
    }

    public String encodeTask (String taskToWrite) {
        String task = null;
        if(taskToWrite.startsWith("[T]")) {
            task = "todo" + taskToWrite.substring(7);
            return task;
        } else if (taskToWrite.startsWith("[E]")) {
            task = "event" + taskToWrite.substring(7, taskToWrite.indexOf("(")) + " /at "
            + taskToWrite.substring(taskToWrite.indexOf(":") + 1, taskToWrite.indexOf(")"));
        } else if (taskToWrite.startsWith("[D]")) {
            task = "deadline" + taskToWrite.substring(7, taskToWrite.indexOf("(")) + " /by "
            + taskToWrite.substring(taskToWrite.indexOf(":") + 1, taskToWrite.indexOf(")"));
        }
        return task;
    }

    public Task decodeTask (String taskToRead) {
        Task task = null;
        if(taskToRead.startsWith("todo")) {
            return task = new Todo(taskToRead);
        } else if(taskToRead.startsWith("event")) {
            return task = new Event(taskToRead);
        } else if (taskToRead.startsWith("deadline")) {
            return task = new Deadline(taskToRead);
        } else {
            return task = null;
        }
    }

    public static class DukeException extends Exception {
        /**
         * Constructor for DukeException class.
         *
         * @param message The exeception message.
         */
        public DukeException(String message) {
            super(message);
        }
    }

}
