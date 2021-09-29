package duke;

import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private static final java.nio.file.Path userDirectory = java.nio.file.Paths.get(System.getProperty("user.dir"));
    private static final String userFile = userDirectory + "/task.txt";

    /**
     * Initializes the text file that contains the task list. Creates a text file if none is found.
     */
    public static void loadFile() {
        try {
            File saveFile = new File(userFile);
            saveFile.createNewFile();
            Scanner savedTasks = new Scanner(saveFile);
            extractDateFromFile(savedTasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parses the data from the text file and stores them into a task list by calling different methods depending on
     * then commands in the text file.
     *
     * @param savedTasks Task data stored in the text file.
     */
    private static void extractDateFromFile(Scanner savedTasks) {
        boolean isPrint = false;
        int numOfTasks = 1;
        while (savedTasks.hasNext()) {
            String[] currentLine = savedTasks.nextLine().split("\\|");
            String taskType = currentLine[0];
            String taskStatus = currentLine[1];
            String taskCommand;
            String taskDetails = currentLine[2];
            switch (taskType) {
            case "D ":
                taskCommand = "deadline" + taskDetails;
                TaskList.addDeadline(taskCommand, isPrint);
                break;
            case "E ":
                taskCommand = "event" + taskDetails;
                TaskList.addEvent(taskCommand, isPrint);
                break;
            default:
                taskCommand = "todo" + taskDetails;
                TaskList.addTodo(taskCommand, isPrint);
                break;
            }
            if (taskStatus.equals(" 1 ")) {
                TaskList.completeTask(numOfTasks, isPrint);
            }
            numOfTasks++;
        }
    }

    /**
     * Saves the current task list to the text file.
     *
     * @param userList Current task list to be saved to text file.
     * @param numTask Total number of tasks in the task list.
     */
    public static void saveFile(ArrayList<Task> userList, int numTask) {
        try {
            FileWriter updateFile = new FileWriter(userFile);
            for (int i = 0; i < numTask; i++) {
                String stringToSave = userList.get(i).getTaskType() + " | " + TaskList.getDoneStatus(userList, i) +
                        " | " + userList.get(i).getDescription() + "\n";
                updateFile.write(stringToSave);
            }
            updateFile.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}

