package duke;

import duke.exceptions.FileFormatException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import duke.tasks.TaskList;
import duke.tasks.TaskListResponse;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {

    private final String filePath;
    private final File f;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.f = new File(filePath);
    }

    public void loadTasks(TaskList taskList) throws FileNotFoundException, FileFormatException {
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String task = s.nextLine();
            if (!task.matches("T\\s\\|\\s[01]\\s\\|.+")
                    && !task.matches("[ED]\\\\s\\\\|\\\\s[01]\\\\s\\\\|.+\\\\|.+")) {
                throw new FileFormatException();
            }
            String[] taskSplit= task.split(" \\| ");
            String taskType = taskSplit[0];
            boolean isDone = taskSplit[1].equals("1");
            String description = taskSplit[2];
            String preposition = taskSplit.length >= 4 ? taskSplit[3] : "";
            if (taskType.equals("E")){
                taskList.tasks.add(new Event(description, isDone, preposition));
            } else if (taskType.equals("D")){
                taskList.tasks.add(new Deadline(description, isDone, preposition));
            } else {
                taskList.tasks.add(new Todo(description, isDone));
            }
        }
    }

    public void createFile() throws IOException {
        Path pathToFile = Paths.get("data/duke.txt");
        Files.createDirectories(pathToFile.getParent());
        f.createNewFile();
    }

    public TaskListResponse initialiseTasks() {
        TaskList taskList = new TaskList();
        String response = "";
        try {
            loadTasks(taskList);
        } catch (FileNotFoundException e0){
            response = response + "The file data/duke.txt is not found.";
            try {
                createFile();
            } catch (IOException e1) {
                response = response + "There has been an error creating a new file. ";
            }
            response = response + "A new file data/duke.txt has been created.";
        } catch (FileFormatException e2) {
            response = response + "There is a formatting error with the save file.";
            taskList.clearList();
        }
        return new TaskListResponse(taskList, response);
    }

    private void writeToFile(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter("data/duke.txt");
        for (Task list : taskList.tasks) {
            fw.write(list.saveTask() + "\n");
        }
        fw.close();
    }

    public String write(TaskList taskList) {
        try {
            writeToFile(taskList);
        } catch (IOException e) {
            return "There has been an error writing to file.";
        }
        return "";
    }
}
