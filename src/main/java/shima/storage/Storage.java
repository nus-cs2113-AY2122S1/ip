package shima.storage;

import shima.Shima;
import shima.design.ToDoList;
import shima.design.Default;
import shima.exception.ShimaException;
import shima.task.Deadline;
import shima.task.Event;
import shima.task.Task;
import shima.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static final String FILEPATH = "./shimaStorage.txt";
    public static final String DELIMITER = "Ø";
    public static File file = new File(FILEPATH);
    public static String STORAGE_MESSAGE = "Welcome to my storage :P, this is how I memorize all your tasks!\n" +
            "Alert! Please do not delete anything inside this file, else I will get memory loss :(\n";

    /**
     * Creates the storage file
     *
     * @throws IOException Throws this exception when error occurs during the write file process
     */
    public static void createFile() throws IOException {
        FileWriter createFile = new FileWriter(FILEPATH);
        createFile.write(STORAGE_MESSAGE);
        createFile.close();
    }

    /**
     * Reads the data from the storage file
     *
     * @param tasks The array list that stores all the tasks
     * @throws ShimaException.StorageException Throws this exception when the data stored in the storage file is invalid
     * @throws IOException                    Throws this exception when error occurs during the write file process
     */
    public static void readFromStorage(ArrayList<Task> tasks) throws ShimaException.StorageException, IOException {
        try {
            Scanner sc = new Scanner(Storage.file);
            //Skips the first two lines of storage descriptions in the file
            int skipTwoLines = 0;
            while (sc.hasNext()) {
                if (skipTwoLines < 2) {
                    sc.nextLine();
                    skipTwoLines++;
                } else {
                    //Starts reading data
                    readData(tasks, sc);
                }
            }
            //Displays the to-do list if it is not empty
            if (tasks.size() > 0) {
                System.out.println("\nHello user! I have helped you written down the to-do list from my previous record!");
                ToDoList.printToDoList(tasks, Shima.longestTaskDescription);
            }
        } catch (FileNotFoundException ex) {
            //Creates a file called shimaStorage.txt if the file is not found
            Storage.createFile();
        }
    }

    /**
     * Reads data from the file if the file exists and contains lines
     *
     * @param tasks The array list that stores all the tasks
     * @param sc The scanner which scans the file
     * @throws ShimaException.StorageException
     */
    private static void readData(ArrayList<Task> tasks, Scanner sc) throws ShimaException.StorageException {
        String[] tasksData = sc.nextLine().split(DELIMITER);
        Task currentTask = tasks.get(tasks.size() - 1);;
        switch (tasksData[0]) {
        case "T":
            tasks.add(new ToDo(tasksData[2]));
            //Updates the longestTaskDescription to ensure that the frames for to-do list can be printed correctly
            Shima.longestTaskDescription = Math.max(currentTask.getTask().length(), Shima.longestTaskDescription);
            break;
        case "D":
            tasks.add(new Deadline(tasksData[2], tasksData[3]));
            Shima.longestTaskDescription = Math.max(currentTask.getTask().length() + "(by: )".length() + currentTask.getTime().length(),
                    Shima.longestTaskDescription);
            break;
        case "E":
            tasks.add(new Event(tasksData[2], tasksData[3]));
            Shima.longestTaskDescription = Math.max(currentTask.getTask().length() + "(at: )".length() + currentTask.getTime().length(),
                    Shima.longestTaskDescription);
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
     * Handles the error when the data stored in the storage file is invalid
     *
     * @param tasks The array list that stores all the tasks
     */
    public static void handleStorageError(ArrayList<Task> tasks) {
        Scanner readInput = new Scanner(System.in);
        System.out.println();
        System.out.println("Take Note:");
        Default.showMessage("There is an error occurs when I try to read data from the shimaStorage.txt file, please help me fix it :(");
        readUserInput(tasks, readInput);
    }

    /**
     * Reads the user input and reacts accordingly
     *
     * @param tasks The array list that stores all the tasks
     * @param readInput The scanner for input
     */
    private static void readUserInput(ArrayList<Task> tasks, Scanner readInput) {
        System.out.print("\nDo you wish to continue by clearing all the previous data stored in the storage file? (Y/N) ");
        String answer = readInput.nextLine();
        if (answer.equalsIgnoreCase("Y")) {
            try {
                Storage.updateStorage(tasks);
            } catch (IOException e) {
                Default.showMessage("Unfortunately somethings have messed up, I have received this information:");
                e.printStackTrace();
            }
        } else if (answer.equalsIgnoreCase("N")) {
            System.exit(1);
        } else {
            Default.showMessage("I do not understand your reply, please try again :(");
            readUserInput(tasks, readInput);
        }
    }

    /**
     * Updates the storage file whenever tasks are updated
     *
     * @param tasks The array list that stores all the tasks
     * @throws IOException Throws this exception when error occurs during the write file process
     */
    public static void updateStorage(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.write(STORAGE_MESSAGE);
        for (Task t : tasks) {
            String taskToSave = "";
            String symbolForDone = (t.getDone()) ? "Y" : "N";
            boolean doNotSave = false;
            if (t instanceof Deadline) {
                taskToSave = t.getClassType() + DELIMITER + symbolForDone + DELIMITER + t.getTask() + DELIMITER + t.getTime() + System.lineSeparator();
            } else if (t instanceof Event) {
                taskToSave = t.getClassType() + DELIMITER + symbolForDone + DELIMITER + t.getTask() + DELIMITER + t.getTime() + System.lineSeparator();
            } else if (t instanceof ToDo) {
                taskToSave = t.getClassType() + DELIMITER + symbolForDone + DELIMITER + t.getTask() + System.lineSeparator();
            } else {
                Default.showMessage("An unexpected error occurs when I try to know the type of the task " + t);
                doNotSave = true;
            }
            if (!doNotSave) {
                fw.write(taskToSave);
            }
        }
        fw.close();
    }

    /**
     * Append the newly created task to the storage file
     *
     * @param tasks The array list that stores all the tasks
     * @throws IOException Throws this exception when error occurs during the write file process
     */
    public static void saveTaskToFile(ArrayList<Task> tasks) throws IOException {
        //append mode
        FileWriter fw = new FileWriter(file, true);
        Task currentTask = tasks.get(tasks.size() - 1);
        if (currentTask instanceof Deadline || currentTask instanceof Event) {
            String taskToSave = currentTask.getClassType() + DELIMITER + "N" + DELIMITER + currentTask.getTask() + DELIMITER + currentTask.getTime() + System.lineSeparator();
            fw.write(taskToSave);
        } else if (currentTask instanceof ToDo) {
            String taskToSave = currentTask.getClassType() + DELIMITER + "N" + DELIMITER + currentTask.getTask() + System.lineSeparator();
            fw.write(taskToSave);
        } else {
            Default.showMessage("An unexpected error occurs when I try to append the storage file... Class Type Mismatch!");
        }
        fw.close();
    }
}
