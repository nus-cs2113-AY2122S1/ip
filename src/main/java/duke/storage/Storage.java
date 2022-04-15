package duke.storage;

import duke.extract.ExtractDeadlineDescription;
import duke.extract.ExtractDueTime;
import duke.extract.ExtractEventDescription;
import duke.extract.ExtractLocation;
import duke.task.Task;
import duke.exception.AtEmptyException;
import duke.exception.ByEmptyException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static final String SAD_FACE = "\uD83D\uDE41";
    private static final int START_OF_STRING = 0;
    private static final int NUMBER_OF_INFO_PARAM = 2;
    private static final int DUE_TIME_INDEX = 1;
    private static final int LOCATION_INDEX = 1;
    private static final int DESCRIPTION_INDEX = 0;
    private static final String FILEPATH = "data/duke.txt";
    private static final String FOLDERPATH = "data";
    private static final int INDEX_OF_TASK_TYPE = 1;
    private static final int START_OF_DESCRIPTION = 7;
    private static final char TODO_SYMBOL = 'T';
    private static final char DEADLINE_SYMBOL = 'D';
    private static final char EVENT_SYMBOL = 'E';

    public static ArrayList<Task> taskArrayList = new ArrayList<>();

    public Storage(){
    }

    /**
     * Loads a saved copy of the task list
     */
    public static void loadFile() {
        try {
            createFolder();
            createFile();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        } catch (ByEmptyException e) {
            System.out.println(SAD_FACE + e.getMessage());
        } catch (AtEmptyException e) {
            System.out.println(SAD_FACE + e.getMessage());
        }
    }

    private static void createFolder() {
        File folder = new File(FOLDERPATH);
        if (folder.mkdir()) {
            System.out.println("Folder created: " + folder.getName());
        }
    }

    private static void createFile() throws IOException, ByEmptyException, AtEmptyException {
        File f = new File(FILEPATH);
        if (f.createNewFile()) {
            System.out.println("File created: " + f.getName());
        } else {
            readFile(f);
        }
    }

    /**
     * Updates the text file with the current task list
     */
    public static void writeToFile() {
        try {
            FileWriter fw = new FileWriter(FILEPATH);
            for (Task t : taskArrayList) {
                fw.write(t.taskNum + "." + t + System.lineSeparator());
            }
            fw.close();
            System.out.println("File \"duke.txt\" updated");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static void readFile(File f) throws FileNotFoundException, ByEmptyException, AtEmptyException {
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();
            int fullStopPosition = line.indexOf(".");
            String lineWithoutNum = line.substring(fullStopPosition + 1);
            int readNumber = Integer.parseInt(line.substring(START_OF_STRING, fullStopPosition));
            readTaskType(lineWithoutNum, readNumber);
        }
        System.out.println("File Loaded: " + Task.taskCount + " Tasks");
    }

    private static void readTaskType(String lineWithoutNum, int readNumber) throws ByEmptyException, AtEmptyException {
        char taskType = lineWithoutNum.charAt(INDEX_OF_TASK_TYPE);
        switch (taskType) {
        case TODO_SYMBOL:
            taskArrayList.add(new Todo(lineWithoutNum.substring(START_OF_DESCRIPTION)));
            break;
        case DEADLINE_SYMBOL:
            String[] deadlineInfo = splitFileDeadline(lineWithoutNum);
            taskArrayList.add(new Deadline(deadlineInfo[DESCRIPTION_INDEX], deadlineInfo[DUE_TIME_INDEX]));
            break;
        case EVENT_SYMBOL:
            String[] eventInfo = splitFileEvent(lineWithoutNum);
            taskArrayList.add(new Event(eventInfo[DESCRIPTION_INDEX], eventInfo[LOCATION_INDEX]));
        }
        readDoneMark(lineWithoutNum, readNumber);
    }

    private static String[] splitFileDeadline(String userInput) throws StringIndexOutOfBoundsException,
            ByEmptyException {
        String[] deadlineInfo = new String[NUMBER_OF_INFO_PARAM];
        int dividerPosition = userInput.indexOf("(by:");
        ExtractDeadlineDescription.extract(userInput, deadlineInfo, dividerPosition);
        ExtractDueTime.extract(userInput, deadlineInfo, dividerPosition);
        int INDEX_OF_CLOSING_BRACKET = deadlineInfo[DUE_TIME_INDEX].length() - 1;
        deadlineInfo[DUE_TIME_INDEX] = deadlineInfo[DUE_TIME_INDEX].substring(START_OF_STRING, INDEX_OF_CLOSING_BRACKET);
        if (deadlineInfo[DUE_TIME_INDEX].equals("")) {
            throw new ByEmptyException();
        }
        return deadlineInfo;
    }

    private static String[] splitFileEvent(String userInput) throws StringIndexOutOfBoundsException,
            AtEmptyException {
        String[] eventInfo = new String[NUMBER_OF_INFO_PARAM];
        int dividerPosition = userInput.indexOf("(at:");
        ExtractEventDescription.extract(userInput, eventInfo, dividerPosition);
        ExtractLocation.extract(userInput, eventInfo, dividerPosition);
        int INDEX_OF_CLOSING_BRACKET = eventInfo[LOCATION_INDEX].length() - 1;
        eventInfo[LOCATION_INDEX] = eventInfo[LOCATION_INDEX].substring(START_OF_STRING, INDEX_OF_CLOSING_BRACKET);
        if (eventInfo[LOCATION_INDEX].equals("")) {
            throw new AtEmptyException();
        }
        return eventInfo;
    }

    private static void readDoneMark(String lineWithoutNum, int readNumber) {
        int INDEX_OF_DONE_MARK = 4;
        if (lineWithoutNum.charAt(INDEX_OF_DONE_MARK) == 'X') {
            taskArrayList.get(readNumber - 1).isDone = true;
        }
    }
}
