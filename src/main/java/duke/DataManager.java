package duke;

import duke.command.Command;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class DataManager {
    private static final String fileName = ".\\DukeData.txt";
    private static final int TASK_TYPE_INDEX = 0;
    private static final int DONE_INDEX = 1;
    private static final int DESCRIPTION_INDEX = 2;
    private static final int TOTAL_DATA_PARTS = 3;

    /**
     * Adds all entries in DukeData.txt.
     */
    public static void load() {
        try {
            FileInputStream fis = new FileInputStream(fileName);
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                data = processData(data);
                String[] dataParts = splitToParts(data);
                addTaskEntry(dataParts);
            }
            sc.close();
            UserInterface.showLoadSuccess();
        } catch (DukeBlankDescriptionsException | DukeInvalidTaskIndexException e) {
            UserInterface.showLoadError();
        } catch (IOException e) {
            UserInterface.showMissingDataFile();
        }
    }

    /**
     * Returns an array of Strings each representing a characteristic of the Task class.
     *
     * @param data String to be split.
     * @return Split data.
     */
    private static String[] splitToParts(String data) {
        String[] dataParts = data.split(",");
        for (int i = 0; i < TOTAL_DATA_PARTS; i++) {
            dataParts[i] = dataParts[i].trim();
        }
        return dataParts;
    }

    /**
     * Returns a processed String that can be easily split into parts later.
     *
     * @param data String to be processed.
     * @return A processed String.
     */
    private static String processData(String data) {
        data = data.replace(']', ',');
        data = data.replace('[', ' ');
        return data;
    }

    /**
     * Returns lateral location of the specified position.
     *
     * @param dataParts Strings to be passed into TaskManager functions.
     * @throws DukeBlankDescriptionsException If dataParts[DESCRIPTION_INDEX] is blank.
     * @throws DukeInvalidTaskIndexException  If wrong Task index is passed into TaskManager.setDone(int taskIndex).
     */
    private static void addTaskEntry(String[] dataParts) throws DukeBlankDescriptionsException,
            DukeInvalidTaskIndexException {
        if (dataParts[TASK_TYPE_INDEX].equals("T")) {
            TaskManager.addTask(Command.ADD_TODO, dataParts[DESCRIPTION_INDEX]);
            if (dataParts[DONE_INDEX].equals("X")) {
                TaskManager.setDone(TaskManager.getNumOfTasks());
            }
        } else if (dataParts[TASK_TYPE_INDEX].equals("D")) {
            processDescription(dataParts, "(by:", "/by");
            TaskManager.addTask(Command.ADD_DEADLINE, dataParts[DESCRIPTION_INDEX]);
            if (dataParts[DONE_INDEX].equals("X")) {
                TaskManager.setDone(TaskManager.getNumOfTasks());
            }
        } else {
            processDescription(dataParts, "(at:", "/at");
            TaskManager.addTask(Command.ADD_EVENT, dataParts[DESCRIPTION_INDEX]);
            if (dataParts[DONE_INDEX].equals("X")) {
                TaskManager.setDone(TaskManager.getNumOfTasks());
            }
        }
    }

    /**
     * Changes description field in dataParts.
     *
     * @param dataParts   Contains Strings that needs to be processed.
     * @param target      Substring to be replaced.
     * @param replacement The replacement.
     */
    private static void processDescription(String[] dataParts, String target, String replacement) {
        dataParts[DESCRIPTION_INDEX] = dataParts[DESCRIPTION_INDEX].replace(")", "");
        dataParts[DESCRIPTION_INDEX] = dataParts[DESCRIPTION_INDEX].replace(target, replacement);
    }

    /**
     * Transfers all current Tasks in TaskManager (in their toString() format) into DukeData.txt.
     */
    public static void save() {
        FileWriter writer;
        try {
            writer = new FileWriter(fileName);
        } catch (IOException e) {
            UserInterface.showSaveError();
            return;
        }
        BufferedWriter buffer = new BufferedWriter(writer);
        try {
            Iterator<Task> i = TaskManager.createIterator();
            while (i.hasNext()) {
                buffer.write(String.valueOf(i.next()));
                buffer.write("\n");
            }
            buffer.close();
        } catch (IOException e) {
            UserInterface.showSaveError();
            return;
        }
        UserInterface.showSaveSuccess();
    }
}
