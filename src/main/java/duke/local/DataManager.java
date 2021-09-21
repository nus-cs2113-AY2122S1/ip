package duke.local;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import duke.task.TaskManager;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;

public class DataManager {

    private final String filePath;
    private final File dataFile;

    private final String FILE_SEPARATOR_REGEX = "\\s\\|\\s";
    private final String TODO_FILE_FORMAT_REGEX = "T\\s\\|\\s[01]\\s\\|.+";
    private final String DEADLINE_FILE_FORMAT_REGEX = "D\\s\\|\\s[01]\\s\\|.+\\s\\|.+";
    private final String EVENT_FILE_FORMAT_REGEX = "E\\s\\|\\s[01]\\s\\|.+\\s\\|.+";

    private final String TODO_TYPE = "T";
    private final String DEADLINE_TYPE = "D";
    private final String EVENT_TYPE = "E";

    private final String FILE_NOT_FOUND_MSG = "File does not exist!";
    private final String FILE_CREATION_ERROR_MSG = "File cannot be created!";
    private final String FILE_WRITE_ERROR_MSG = "File cannot be written to!";

    public DataManager(String filePath) {
        this.filePath = filePath;
        this.dataFile = new File(filePath);
    }

    public void printFileContents() throws FileNotFoundException {
        Scanner in = new Scanner(dataFile);

        while (in.hasNext()) {
            System.out.println(in.nextLine());
        }
    }

    public void createFile() throws IOException {
        Files.createDirectories(Path.of(dataFile.getParent()));
        dataFile.createNewFile();
    }

    public TaskManager loadDataFromFile() {

        TaskManager taskMgr = new TaskManager();

        try {

            Scanner in = new Scanner(dataFile);

            while (in.hasNext()) {

                String task = in.nextLine();
                String[] taskArgs = task.split(FILE_SEPARATOR_REGEX, 4);

                String taskType = taskArgs[0];
                boolean isDone = false;

                if (taskArgs[1].equals("1")) {
                    isDone = true;
                }

                String taskDescription = taskArgs[2];
                String byAtDescription = "";

                if (taskArgs.length == 4) {
                    byAtDescription = taskArgs[3];
                }

                switch (taskType) {
                case TODO_TYPE:
                    Task newToDo = new ToDo(taskDescription.trim(), isDone);
                    taskMgr.tasks.add(newToDo);
                    break;
                case DEADLINE_TYPE:
                    Task newDeadline = new Deadline(taskDescription.trim(), isDone, byAtDescription.trim());
                    taskMgr.tasks.add(newDeadline);
                    break;
                case EVENT_TYPE:
                    Task newEvent = new Event(taskDescription.trim(), isDone, byAtDescription.trim());
                    taskMgr.tasks.add(newEvent);
                    break;
                }
            }
        } catch (FileNotFoundException e1) {

            System.out.println(FILE_NOT_FOUND_MSG);
            try {
                createFile();
            } catch (IOException e2) {
                System.out.println(FILE_CREATION_ERROR_MSG);
            }

        }
        return taskMgr;
    }

    public void writeToFile(TaskManager taskMgr) {

        try {
            FileWriter fw = new FileWriter(filePath);

            for (int i = 0; i < taskMgr.tasks.size(); i++) {
                fw.write(taskMgr.tasks.get(i).getTaskFileFormat() + "\n");
            }

            fw.close();
        } catch (IOException e) {
            System.out.println(FILE_WRITE_ERROR_MSG);
        }

    }

}
