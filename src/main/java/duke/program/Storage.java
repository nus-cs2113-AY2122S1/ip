package duke.program;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String SEPARATOR_DOT = ".";
    private static final String SEPARATOR_DOT_WITH_ESCAPE = "\\.";

    private static final String TASK_TYPE_ICON_TODO = "T";
    private static final String TASK_TYPE_ICON_DEADLINE = "D";
    private static final String TASK_TYPE_ICON_EVENT = "E";
    private static final String ICON_DONE = "X";

    private String filePath;
    private String folderPath;

    public Storage(String filePath, String folderPath) {
        this.filePath = filePath;
        this.folderPath = folderPath;
    }

    public ArrayList<Task> loadFile(LizUi ui) throws IOException {
        File f = new File(filePath);
        File folder = new File(folderPath);
        ArrayList<Task> tasks = new ArrayList<>();

        //the mkdir and createNewFile methods will always be called regardless
        //if they already exist or not. This is because in the case that the folder/file
        //already exists, the methods have implicit checks that guard against duplicate creations.
        folder.mkdir();
        f.createNewFile();
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            String[] taskArgs = s.nextLine().split(SEPARATOR_DOT_WITH_ESCAPE);
            String taskType = taskArgs[0];
            boolean taskStatus = taskArgs[1].equals(ICON_DONE);
            String taskDescription = taskArgs[2];

            if (taskType.equals(TASK_TYPE_ICON_TODO)) {
                tasks.add(new ToDo(taskDescription, taskStatus));
            } else if (taskType.equals(TASK_TYPE_ICON_DEADLINE)) {
                String taskByTime = taskArgs[3];
                tasks.add(new Deadline(taskDescription, taskByTime, taskStatus));
            } else if (taskType.equals(TASK_TYPE_ICON_EVENT)) {
                String taskAtTime = taskArgs[3];
                tasks.add(new Event(taskDescription, taskAtTime, taskStatus));
            } else {
                ui.printGenericErrorMessage();
            }
        }

        return tasks;
    }

    public void saveFile(ArrayList<Task> tasks, LizUi ui) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        for (int i = 0; i < tasks.size(); i++) {
            String taskType = tasks.get(i).getType();
            String taskStatus = tasks.get(i).getStatusIcon();
            String taskDescription = tasks.get(i).getDescription();

            if (taskType.equals(TASK_TYPE_ICON_TODO)) {
                fw.write(taskType + SEPARATOR_DOT + taskStatus + SEPARATOR_DOT + taskDescription);
            } else if (taskType.equals(TASK_TYPE_ICON_DEADLINE)) {
                String byTime = tasks.get(i).getByDateTime();
                fw.write(taskType + SEPARATOR_DOT + taskStatus + SEPARATOR_DOT + taskDescription + SEPARATOR_DOT + byTime);
            } else if (taskType.equals((TASK_TYPE_ICON_EVENT))) {
                String atTime = tasks.get(i).getStartAndEndTime();
                fw.write(taskType + SEPARATOR_DOT + taskStatus + SEPARATOR_DOT + taskDescription + SEPARATOR_DOT + atTime);
            } else {
                ui.printGenericErrorMessage();
            }

            fw.write(System.lineSeparator());
        }

        fw.close();
    }
}
