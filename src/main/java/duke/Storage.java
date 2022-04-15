package duke;

import duke.parser.ParseFromFileFormat;
import duke.parser.ParseToFileFormat;
import duke.parser.Parser;
import duke.task.Task;
import duke.task.TaskList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Controls the persistent storage of tasks.
 */
public class Storage {

    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private final Ui ui;
    private final String filePath;
    private final String fileDirectory;

    /**
     * Creates a Storage object.
     *
     * @param filePath The relative path to the file.
     * @param fileDirectory The relative path to the directory containing the file.
     * @param ui The Ui object handling the user output.
     */
    public Storage(String filePath, String fileDirectory, Ui ui) {
        this.filePath = filePath;
        this.fileDirectory = fileDirectory;
        this.ui = ui;
    }

    /**
     * Save the data into the file.
     *
     * @param tasks The ArrayList of tasks to be saved.
     */
    public void saveData(ArrayList<Task> tasks) {
        try {
            writeToFile(tasks);
        } catch (IOException exception) {
            ui.printIoExceptionErrorMessage(exception);
        }
    }

    /**
     * Write each line of the data into the file.
     *
     * @param tasks The ArrayList of tasks to be saved.
     * @throws IOException Indicated that there is an IOException.
     */
    private void writeToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        for (Task task : tasks) {
            Parser fileFormatter = new ParseToFileFormat(task);
            String taskData = fileFormatter.getFileFormat();
            fileWriter.write(taskData);
        }
        fileWriter.close();
    }

    /**
     * Load saved data.
     *
     * @param tasks The TaskList object that is managing all the tasks.
     */
    public void loadData(TaskList tasks) {
        try {
            File file = loadFile();
            Scanner scanner = new Scanner(file);
            while(scanner.hasNext()) {
                Parser fileFormatHandler = new ParseFromFileFormat(scanner.nextLine());

                switch (fileFormatHandler.getCommand()) {
                case COMMAND_TODO:
                    Task todoTask = tasks.addTodo(fileFormatHandler.getDescription());
                    if (fileFormatHandler.isDone()) {
                        todoTask.setDone();
                    }
                    break;
                case COMMAND_DEADLINE:
                    Task deadlineTask = tasks.addDeadline(fileFormatHandler.getDescription(), fileFormatHandler.getFormattedTimeField());
                    if (fileFormatHandler.isDone()) {
                        deadlineTask.setDone();
                    }
                    break;
                case COMMAND_EVENT:
                    Task eventTask = tasks.addEvent(fileFormatHandler.getDescription(), fileFormatHandler.getFormattedTimeField());
                    if (fileFormatHandler.isDone()) {
                        eventTask.setDone();
                    }
                    break;
                }
            }
        } catch (IOException exception) {
            ui.printIoExceptionErrorMessage(exception);
        }
    }

    /**
     * Load the file for reading.
     *
     * @return The file of saved data.
     * @throws IOException Indicated that there is an IOException.
     */
    private File loadFile() throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            File dir = new File(fileDirectory);
            dir.mkdir();
            File newFile = new File(filePath);
            newFile.createNewFile();
            return newFile;
        } else {
            return file;
        }
    }
}
