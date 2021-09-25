package shima.storage;

import shima.design.UserInterface;
import shima.exception.ShimaException;

import shima.task.Task;
import shima.task.ToDo;
import shima.task.Deadline;
import shima.task.Event;
import shima.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    public static final String LIST_ALERT_MSG = "\nHello user! I have helped you written down the to-do list from my previous record!";
    public static final String STORAGE_ERROR_MSG = "There is an error occurs when I try to read data from the shimaStorage.txt file, please help me fix it :(";
    public static final String UNEXPECTED_ERROR_MSG = "An unexpected error occurs when I try to know the type of the task ";
    public static final String CLASS_TYPE_MISMATCH_MSG = "An unexpected error occurs when I try to append the storage file... Class Type Mismatch!";
    public static final String WRONG_REPLY_MSG = "I do not understand your reply, please try again :(";
    public static final String IOEXCEPTION_MSG = "Unfortunately somethings have messed up, I have received this information";
    public String filePath;
    private static final String DELIMITER = "Ø";
    private File file;
    private UserInterface ui;
    private static final String STORAGE_MESSAGE = "Welcome to my storage :P, this is how I memorize all your tasks!\n" +
            "Alert! Please do not delete anything inside this file, else I will get memory loss :(\n";

    public Storage(String filePath, UserInterface ui) {
        this.filePath = filePath;
        this.file = new File(this.filePath);
        this.ui = ui;
    }

    /**
     * Creates the storage file
     *
     * @throws IOException Throws this exception when error occurs during the write file process
     */
    public void createFile() throws IOException {
        FileWriter createFile = new FileWriter(filePath);
        createFile.write(STORAGE_MESSAGE);
        createFile.close();
    }

    /**
     * Reads the data from the storage file, will be called when the program starts
     *
     * @param tasks The list class object that stores all the tasks
     * @throws ShimaException.StorageException Throws this exception when the data stored in the storage file is invalid
     * @throws IOException                     Throws this exception when error occurs during the write file process
     */
    public void readFromStorage(TaskList tasks) throws ShimaException.StorageException, IOException {
        try {
            Scanner sc = new Scanner(file);
            //Skips the first two lines of storage descriptions in the file
            sc.nextLine();
            sc.nextLine();
            while (sc.hasNext()) {
                String[] tasksData = sc.nextLine().split(DELIMITER);
                readData(tasks, tasksData);
            }
            //Displays the to-do list if it is not empty
            if (tasks.size() > 0) {
                System.out.println(LIST_ALERT_MSG);
                tasks.printToDoList();
            }
        } catch (FileNotFoundException ex) {
            //Creates a file called shimaStorage.txt if the file is not found
            createFile();
        }
    }

    /**
     * Handles the error when the data stored in the storage file contains invalid value
     *
     * @param tasks The list class object that stores all the tasks
     */
    public void handleStorageError(TaskList tasks) {
        Scanner readInput = new Scanner(System.in);
        System.out.println();
        System.out.println("Take Note:");
        ui.showMessage(STORAGE_ERROR_MSG);
        readUserInput(tasks, readInput);
    }

    /**
     * Updates the storage file whenever tasks are updated
     *
     * @param tasks The list class object that stores all the tasks
     * @throws IOException Throws this exception when error occurs during the write file process
     */
    public void updateStorage(TaskList tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(file); //Clears all the texts in the file
        fileWriter.write(STORAGE_MESSAGE);
        for (Task t : tasks.getTasks()) {
            String taskToSave = "";
            String symbolForDone = (t.getDone()) ? "Y" : "N";
            boolean canSave = true;
            if (t instanceof Deadline) {
                taskToSave = t.getClassType() + DELIMITER + symbolForDone + DELIMITER + t.getTask() + DELIMITER + t.getTime() +
                        System.lineSeparator();
            } else if (t instanceof Event) {
                taskToSave = t.getClassType() + DELIMITER + symbolForDone + DELIMITER + t.getTask() + DELIMITER + t.getTime() +
                        System.lineSeparator();
            } else if (t instanceof ToDo) {
                taskToSave = t.getClassType() + DELIMITER + symbolForDone + DELIMITER + t.getTask() + System.lineSeparator();
            } else {
                ui.showMessage(UNEXPECTED_ERROR_MSG + t);
                canSave = false;
            }
            if (canSave) {
                fileWriter.write(taskToSave);
            }
        }
        fileWriter.close();
    }

    /**
     * Appends the newly created task to the storage file
     *
     * @param tasks The list class object that stores all the tasks
     * @throws IOException Throws this exception when error occurs during the write file process
     */
    public void saveTaskToFile(TaskList tasks) throws IOException {
        FileWriter fileEditor = new FileWriter(file, true); //Appends the existing file
        Task currentTask = tasks.get(tasks.size() - 1);
        if (currentTask instanceof Deadline || currentTask instanceof Event) {
            String taskToSave = currentTask.getClassType() + DELIMITER + "N" + DELIMITER + currentTask.getTask() + DELIMITER +
                    currentTask.getTime() + System.lineSeparator();
            fileEditor.write(taskToSave);
        } else if (currentTask instanceof ToDo) {
            String taskToSave = currentTask.getClassType() + DELIMITER + "N" + DELIMITER + currentTask.getTask() + System.lineSeparator();
            fileEditor.write(taskToSave);
        } else {
            ui.showMessage(CLASS_TYPE_MISMATCH_MSG);
        }
        fileEditor.close();
    }

    /**
     * Reads data from the file if the file exists and contains lines
     *
     * @param tasks The list class object that stores all the tasks
     * @throws ShimaException.StorageException Throws StorageException if the data stored is incorrect
     */
    private void readData(TaskList tasks, String[] tasksData) throws ShimaException.StorageException {
        Task currentTask;
        switch (tasksData[0]) {
        case "T":
            tasks.add(new ToDo(tasksData[2]));
            currentTask = tasks.get(tasks.size() - 1);
            //Updates the longestTaskDescription to ensure that the frames for to-do list can be printed correctly
            TaskList.longestTaskDescription = Math.max(currentTask.getTask().length(), TaskList.longestTaskDescription);
            break;
        case "D":
            tasks.add(new Deadline(tasksData[2], tasksData[3]));
            currentTask = tasks.get(tasks.size() - 1);

            TaskList.longestTaskDescription = Math.max("(by: )".length() + currentTask.toString().length(),
                    TaskList.longestTaskDescription);
            break;
        case "E":
            tasks.add(new Event(tasksData[2], tasksData[3]));
            currentTask = tasks.get(tasks.size() - 1);
            TaskList.longestTaskDescription = Math.max(currentTask.getTask().length() + "(at: )".length() + currentTask.getTime().length(),
                    TaskList.longestTaskDescription);
            break;
        default:
            throw new ShimaException.StorageException();
        }
        //If the task is done, set it to done
        if (tasksData[1].equals("Y")) {
            currentTask.setDone();
        }
    }

    /**
     * Reads the user input and reacts accordingly
     *
     * @param tasks     The list class object that stores all the tasks
     * @param readInput The scanner for input
     */
    private void readUserInput(TaskList tasks, Scanner readInput) {
        System.out.print("\nDo you wish to continue by clearing all the previous data stored in the storage file? (Y/N) ");
        String answer = readInput.nextLine();
        if (answer.equalsIgnoreCase("Y")) {
            try {
                updateStorage(tasks);
            } catch (IOException e) {
                ui.showMessage(IOEXCEPTION_MSG);
                e.printStackTrace();
            }
        } else if (answer.equalsIgnoreCase("N")) {
            System.exit(1);
        } else {
            ui.showMessage(WRONG_REPLY_MSG);
            readUserInput(tasks, readInput);
        }
    }
}
