package Duke;

import Duke.Commands.Command;
import Duke.Task.Deadline;
import Duke.Task.Task;
import Duke.Task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private static final String CURRENT_DIRECTORY = System.getProperty("user.dir");
    private static final java.nio.file.Path FILE_PATH = java.nio.file.Paths.get(CURRENT_DIRECTORY);
    private static final ArrayList<Task> FILE_TASKS_ARRAY_LIST = new ArrayList<>();

    public static ArrayList<Task> initialiseFile() {
        try {
            //get the file, else create file if it does not exist.
            File taskFile = getTaskFile();
            Scanner fileTaskLists = new Scanner(taskFile);

            //read and extract the data in the file to store it in the task array list.
            readAndExtractFile(fileTaskLists);
        } catch (IOException | DukeException e) {
            e.printStackTrace();
        }
        clearOutput();
        return FILE_TASKS_ARRAY_LIST;
    }

    private static void clearOutput() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static File getTaskFile() throws IOException {
        File taskFile = new File(FILE_PATH + "/taskLists.txt");
        if (taskFile.createNewFile()) {
            System.out.println("A new file has been created at " + FILE_PATH);
        } else {
            System.out.println(taskFile + " accessed.");
        }
        return taskFile;
    }

    private static void readAndExtractFile(Scanner fileTaskLists) throws DukeException {
        while (fileTaskLists.hasNextLine()) {
            String data = fileTaskLists.nextLine();
            String[] splittedData = data.split("\\|");
            String taskType = splittedData[0];
            boolean isMarkedDone = Integer.parseInt(splittedData[1]) == 1;
            String taskDescription = splittedData[2];
            String taskCommand;
            Command command;

            switch (taskType) {
            case "D":
                taskCommand = "deadline " + taskDescription;
                break;
            case "E":
                taskCommand = "event " + taskDescription;
                break;
            default:
                taskCommand = "todo " + taskDescription;
                break;
            }
            command = Parser.parseCommand(taskCommand);
            command.executeFromFile(FILE_TASKS_ARRAY_LIST);
            if (isMarkedDone) {
                taskCommand = "done " + FILE_TASKS_ARRAY_LIST.size();
                command = Parser.parseCommand(taskCommand);
                command.executeFromFile(FILE_TASKS_ARRAY_LIST);
            }
        }
    }

    public static void updateFile(ArrayList<Task> tasksList) throws IOException {
        FileWriter taskWriter = new FileWriter(FILE_PATH + "/taskLists.txt", false);
        for (Task task : tasksList) {
            String taskAbbreviation = getTaskAbbreviation(task);
            String taskStatus = getTaskStatus(task);
            String dataWritten = taskAbbreviation + "|" + taskStatus + "|" + task.getDescription() + System.lineSeparator();
            taskWriter.write(dataWritten);
        }
        taskWriter.close();
    }

    private static String getTaskStatus(Task task) {
        if (task.getStatusIcon().equals("[X] ")) {
            return "1";
        }
        return "0";
    }

    private static String getTaskAbbreviation(Task task) {
        if (task instanceof Todo) {
            return "T";
        } else if (task instanceof Deadline) {
            return "D";
        }
        return "E";
    }
}
