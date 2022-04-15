package Duke;

import Duke.Commands.Command;
import Duke.Task.Deadline;
import Duke.Task.Task;
import Duke.Task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    private static final String CURRENT_DIRECTORY = System.getProperty("user.dir");
    private static final java.nio.file.Path FILE_PATH = java.nio.file.Paths.get(CURRENT_DIRECTORY);
    private static final TaskList FILE_TASK_LIST = new TaskList();

    /**
     * Initialises the task list according to the contents of the file.
     * Returns the task list.
     *
     * @return Returns the task list initialised based on the contents of the file.
     */
    public static TaskList initialiseFile() {
        try {
            //get the file, else create file if it does not exist.
            File taskFile = getTaskFile();
            Scanner fileList = new Scanner(taskFile);

            //read and extract the data in the file to store it in the task array list.
            readAndExtractFile(fileList);
        } catch (IOException | DukeException e) {
            e.printStackTrace();
        }
        clearOutput();
        return FILE_TASK_LIST;
    }

    /**
     * Clears the output and start a fresh command line screen.
     */
    private static void clearOutput() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Reads the file if it exists or creates the file if it does not.
     * Returns a File type of the file containing the existing task lists.
     *
     * @return Returns a File type of the file containing the existing task lists.
     * @throws IOException If the file cannot be read or created.
     */
    private static File getTaskFile() throws IOException {
        File taskFile = new File(FILE_PATH + "/taskLists.txt");
        if (taskFile.createNewFile()) {
            System.out.println("A new file has been created at " + FILE_PATH);
        } else {
            System.out.println(taskFile + " accessed.");
        }
        return taskFile;
    }

    /**
     * Reads and initialises the task list based on the content of the file.
     *
     * @param fileList The content of the file.
     * @throws DukeException If there is an error initialising the task list.
     */
    private static void readAndExtractFile(Scanner fileList) throws DukeException {
        while (fileList.hasNextLine()) {
            String data = fileList.nextLine();
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
            command.setData(FILE_TASK_LIST);
            command.executeFromFile();
            if (isMarkedDone) {
                taskCommand = "done " + FILE_TASK_LIST.getSize();
                command = Parser.parseCommand(taskCommand);
                command.setData(FILE_TASK_LIST);
                command.executeFromFile();
            }
        }
    }

    /**
     * Updates and overwrite the existing file with a new task list.
     * @param taskList A new updated task list.
     * @throws IOException If the write operation to the file fails.
     */
    public static void updateFile(TaskList taskList) throws IOException {
        FileWriter taskWriter = new FileWriter(FILE_PATH + "/taskLists.txt", false);
        for (Task task : taskList.getEntireList()) {
            String taskAbbreviation = getTaskAbbreviation(task);
            String taskStatus = getTaskStatus(task);
            String dataWritten = taskAbbreviation + "|" + taskStatus + "|" + task.getDescription() + System.lineSeparator();
            taskWriter.write(dataWritten);
        }
        taskWriter.close();
    }

    /**
     * Returns the task status in a string.
     * "1" if the task is marked as done and "0" otherwise.
     *
     * @param task Task whose status is queried.
     * @return Returns the task status in a string format.
     */
    private static String getTaskStatus(Task task) {
        if (task.getStatusIcon().equals("[X] ")) {
            return "1";
        }
        return "0";
    }

    /**
     * Returns the abbreviation of the task type.
     *
     * @param task Task whose task type is queried.
     * @return Returns the abbreviation of the task according to its task type.
     */
    private static String getTaskAbbreviation(Task task) {
        if (task instanceof Todo) {
            return "T";
        } else if (task instanceof Deadline) {
            return "D";
        }
        return "E";
    }
}
