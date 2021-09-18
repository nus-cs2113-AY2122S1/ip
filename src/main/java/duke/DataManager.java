package duke;

import duke.command.Command;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class DataManager {
    private static final String FILENAME = ".\\DukeData.txt";
    private static final int DESCRIPTION_INDEX = 2;
    private static final String NEWLINE = "\n";

    /**
     * Adds all entries in DukeData.txt.
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
                } catch (DukeMissingDataException e) {
                    hasCorruptedLines = true;
                    continue;
                }
                addTaskEntry(dataParts);
            }
            sc.close();
            if (hasCorruptedLines) {
                UserInterface.showFileCorrupted();
            } else {
                UserInterface.showLoadSuccess();
            }
        } catch (DukeBlankDescriptionsException | DukeInvalidTaskIndexException e) {
            UserInterface.showLoadError();
        } catch (IOException e) {
            UserInterface.showMissingDataFile();
        }
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
        if (Parser.isTodoEntry(dataParts)) {
            TaskManager.addTask(Command.ADD_TODO, dataParts[DESCRIPTION_INDEX]);
            if (Parser.isDoneEntry(dataParts)) {
                TaskManager.setDone(TaskManager.getNumOfTasks());
            }
        } else if (Parser.isDeadlineEntry(dataParts)) {
            Parser.processDeadlineDescription(dataParts);
            TaskManager.addTask(Command.ADD_DEADLINE, dataParts[DESCRIPTION_INDEX]);
            if (Parser.isDoneEntry(dataParts)) {
                TaskManager.setDone(TaskManager.getNumOfTasks());
            }
        } else {
            Parser.processEventDescription(dataParts);
            TaskManager.addTask(Command.ADD_EVENT, dataParts[DESCRIPTION_INDEX]);
            if (Parser.isDoneEntry(dataParts)) {
                TaskManager.setDone(TaskManager.getNumOfTasks());
            }
        }
    }
    
    

    /**
     * Transfers all current Tasks in TaskManager (in their toString() format) into DukeData.txt.
     */
    public static void save() {
        FileWriter writer;
        try {
            writer = new FileWriter(FILENAME);
        } catch (IOException e) {
            UserInterface.showSaveError();
            return;
        }
        BufferedWriter buffer = new BufferedWriter(writer);
        try {
            Iterator<Task> i = TaskManager.createIterator();
            while (i.hasNext()) {
                buffer.write(String.valueOf(i.next()));
                buffer.write(NEWLINE);
            }
            buffer.close();
        } catch (IOException e) {
            UserInterface.showSaveError();
            return;
        }
        UserInterface.showSaveSuccess();
    }
}
