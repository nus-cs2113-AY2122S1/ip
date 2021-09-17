package kate.storage;

import kate.common.Message;
import kate.exception.FileCorruptedException;
import kate.exception.InvalidDateTimeException;
import kate.parser.Parser;
import kate.task.Task;
import kate.tasklist.TaskList;
import kate.ui.KateUI;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    private static final String DIRECTORY_NAME_DATA = "data";
    private static final String FILE_NAME_KATE = "kate.txt";
    private static final String PATH_KATE = DIRECTORY_NAME_DATA + "/" + FILE_NAME_KATE;

    private static final File dataDir = new File(DIRECTORY_NAME_DATA);
    private static final File dataFile = new File(PATH_KATE);

    public Storage() {
    }

    /**
     * Creates the data folder and file if it doesn't exist
     * The file contents will be wiped out for every instance of the program
     *
     * @param ui KateUI object to interact with UI
     */
    public void createDataFile(KateUI ui) {
        try {
            dataDir.mkdirs();
            dataFile.createNewFile();
        } catch (IOException e) {
            ui.printMessage("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Loads the task contents from data/kate.txt
     *
     * @param ui    KateUI object to interact with UI
     * @param tasks TaskList object to add tasks from file to program
     * @throws FileCorruptedException If file content has been tampered and cannot be parsed
     * @throws IOException            If any IO operation fails
     */
    public void loadDataFile(KateUI ui, TaskList tasks) throws FileCorruptedException, IOException {
        try {
            createDataFile(ui);
            Scanner scanner = new Scanner(dataFile);
            while (scanner.hasNext()) {
                Parser.processData(tasks, scanner.nextLine());
            }
        } catch (IOException e) {
            ui.printMessage(Message.TEXT_INDENTATION + "I can't seem to create the file... Please try again :(\n");
            throw new IOException();
        } catch (FileCorruptedException e) {
            ui.printMessage(Message.TEXT_INDENTATION + "Did you tamper with the data file? It is CORRUPTED!\n");
            throw new FileCorruptedException();
        }
    }

    /**
     * Appends a task to ./data/kate.txt
     *
     * @param ui   KateUI object to interact with UI
     * @param task TaskList object to add tasks from program to file
     */
    public void appendTaskToFile(KateUI ui, Task task) {
        try {
            FileWriter file = new FileWriter(PATH_KATE, true);
            String taskInfo = task.getTaskInfoForFile() + "\n";
            file.write(taskInfo);
            file.close();
        } catch (IOException e) {
            ui.printMessage("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Update the contents of task information to ./data/kate.txt
     *
     * @param ui    KateUI object to interact with UI
     * @param tasks TaskList object to update tasks from program to file
     */
    public void updateTasksToFile(KateUI ui, TaskList tasks) {
        try {
            FileWriter file = new FileWriter(PATH_KATE);
            StringBuilder taskInfo = new StringBuilder();
            for (int i = 0; i < tasks.getTaskSize(); ++i) {
                Task curTask = tasks.getCurrentTask(i);
                taskInfo.append(curTask.getTaskInfoForFile()).append("\n");
            }
            file.write(String.valueOf(taskInfo));
            file.close();

        } catch (IOException e) {
            ui.printMessage("Something went wrong: " + e.getMessage());
        }
    }

}
