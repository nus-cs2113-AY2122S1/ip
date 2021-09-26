package storage;

import FridayExceptions.EmptyTaskNameException;
import FridayExceptions.IncompleteCommandException;
import FridayExceptions.MissingDateException;
import FridayExceptions.MissingKeyWordException;
import ui.MessagePrinter;
import tasks.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoadData {
    private static final String FILEPATH = "data/friday.txt";
    private static final String doneIndicator = "X";
    private static final String delimiter = "\\|";
    private static final int todoLength = 3;
    private static final int taskTypeIndex = 0;
    private static final int taskDoneIndex = 1;
    private static final int taskNameIndex = 2;
    private static final int dateIndex = 3;
    /**
     * Function to scan data from saved file upon initiating Friday
     * Check if fle exists; if it doesn't, create new in main
     * Works in loading from saved file
     */
    public static void loadData() throws FileNotFoundException {
        MessagePrinter.loadingData();
        // read from file using Scanner
        File data = new File(FILEPATH);
        Scanner s = new Scanner(data);
        while (s.hasNext()) {
            // read and parse data into Task array.
            // Data stored in format type | isDone | taskname | date (if exists)
            String[] splitString = s.nextLine().split(delimiter);
            boolean isDone = false;
            if (splitString[taskDoneIndex].trim().equals(doneIndicator)) {
                isDone = true;
            }
            try {
                // if splitString only has 3 items; means its a todo
                if (splitString.length == todoLength) {
                    TaskList.addToDo(splitString[taskTypeIndex]
                            + " "
                            + splitString[taskNameIndex], isDone, true
                    );
                } else { // means it's a deadline or event
                    if (splitString[taskTypeIndex].trim().equals("deadline")) {
                        TaskList.addDeadline(splitString[taskTypeIndex]
                                + " " + splitString[taskNameIndex]
                                + " /by " + splitString[dateIndex], isDone, true
                        );
                    } else {
                        TaskList.addEvent(splitString[taskTypeIndex]
                                + " " + splitString[taskNameIndex]
                                + " /at " + splitString[dateIndex], isDone, true
                        );
                    }
                }
            } catch (EmptyTaskNameException e) {
                MessagePrinter.emptyTaskName();
            } catch (IncompleteCommandException e) {
                MessagePrinter.incompleteCommand();
            } catch (MissingKeyWordException e) {
                MessagePrinter.missingKeyWord(e.getKeyword());
            } catch (MissingDateException e) {
                MessagePrinter.missingDate(e.getType());
            }
        }
        MessagePrinter.dataLoaded();
    }
}
