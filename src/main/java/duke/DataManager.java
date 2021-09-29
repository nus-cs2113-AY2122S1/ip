package duke;

import duke.command.Command;
import duke.exception.DukeBlankDescriptionsException;
import duke.exception.DukeCorruptedDataException;
import duke.exception.DukeInvalidTaskIndexException;
import duke.exception.DukeMissingDataException;
import duke.task.Task;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Loads data in DukeData.txt into the program on start up.
 * If DukeData.txt cannot be found, one will be created.
 * It will update/save into DukeData.txt after every user command. The save will be silent.
 * Only error message will be sent.
 * A final save will be done when the program is terminating.
 */
public class DataManager {
    private static final String FILENAME = "./DukeData.txt";
    private static final int DESCRIPTION_INDEX = 2;
    private static final String NEWLINE = "\n";

    /**
     * Adds all entries in DukeData.txt into TaskManager.
     */
    public static void load() {
        boolean hasCorruptedLines = false;
        try {
            FileInputStream fis = new FileInputStream(FILENAME);
            Scanner sc = new Scanner(fis);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                data = Parser.processFileData(data);
                String[] dataParts;
                try {
                    dataParts = Parser.splitToDataParts(data);
                    addTaskEntry(dataParts);
                } catch (DukeMissingDataException | ArrayIndexOutOfBoundsException | DukeBlankDescriptionsException
                        | DukeInvalidTaskIndexException | DukeCorruptedDataException e) {
                    hasCorruptedLines = true;
                }
            }
            sc.close();
            if (hasCorruptedLines) {
                UserInterface.showFileCorrupted();
            } else {
                UserInterface.showLoadSuccess();
            }
        } catch (IOException e) {
            UserInterface.showMissingDataFile();
        }
    }

    /**
     * Adds Task entry into DUKE program that is currently running.
     *
     * @param dataParts Strings to be passed into TaskManager functions.
     * @throws DukeBlankDescriptionsException If dataParts[DESCRIPTION_INDEX] is blank.
     * @throws DukeInvalidTaskIndexException  If wrong Task index is passed into TaskManager.setDone(int taskIndex).
     * @throws DukeCorruptedDataException If data entries being loaded from DukeData.txt contain foreign content.
     */
    private static void addTaskEntry(String[] dataParts) throws DukeBlankDescriptionsException
            , DukeInvalidTaskIndexException, DukeCorruptedDataException {
        if (Parser.isTodoEntry(dataParts)) {
            TaskManager.addTask(Command.ADD_TO_DO, dataParts[DESCRIPTION_INDEX]);
            if (Parser.isDoneEntry(dataParts)) {
                TaskManager.setDone(TaskManager.getNumOfTasks());
            }
        } else if (Parser.isDeadlineEntry(dataParts)) {
            Parser.processDeadlineDescription(dataParts);
            TaskManager.addTask(Command.ADD_DEADLINE, dataParts[DESCRIPTION_INDEX]);
            if (Parser.isDoneEntry(dataParts)) {
                TaskManager.setDone(TaskManager.getNumOfTasks());
            }
        } else if (Parser.isEventEntry(dataParts)) {
            Parser.processEventDescription(dataParts);
            TaskManager.addTask(Command.ADD_EVENT, dataParts[DESCRIPTION_INDEX]);
            if (Parser.isDoneEntry(dataParts)) {
                TaskManager.setDone(TaskManager.getNumOfTasks());
            }
        } else {
            throw new DukeCorruptedDataException();
        }
    }

    /**
     * Transfers all current Tasks in TaskManager (in their toString() format) into DukeData.txt.
     * Will notify user whether the save was successful or not.
     */
    public static void save() {
        FileWriter writer;
        try {
            writer = new FileWriter(FILENAME);
            BufferedWriter buffer = new BufferedWriter(writer);
            Iterator<Task> i = TaskManager.createIterator();
            while (i.hasNext()) {
                buffer.write(String.valueOf(i.next()));
                buffer.write(NEWLINE);
            }
            buffer.close();
            UserInterface.showSaveSuccess();
        } catch (IOException e) {
            UserInterface.showSaveError();
        }

    }

    /**
     * Transfers all current Tasks in TaskManager (in their toString() format) into DukeData.txt.
     * Successful saves are silent.
     * User will be notified if save was not successful.
     */
    public static void saveWithoutSuccessMessage() {
        FileWriter writer;
        try {
            writer = new FileWriter(FILENAME);
            BufferedWriter buffer = new BufferedWriter(writer);
            Iterator<Task> i = TaskManager.createIterator();
            while (i.hasNext()) {
                buffer.write(String.valueOf(i.next()));
                buffer.write(NEWLINE);
            }
            buffer.close();
        } catch (IOException e) {
            UserInterface.showSaveErrorWithLine();
        }
    }
}
