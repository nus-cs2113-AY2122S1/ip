package processors;

import exceptions.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private static final String FILEPATH = "data/SavedTask.txt";
    private static final String DIVIDER = "|";

    private static final Integer ZERO = 0;
    private static final Integer ARRAY_INDEX_FINDER = 1;

    public Ui ui = new Ui();

    /**
     * Used to call loadingTasks method and check for any exceptions that may be presented
     * and greets user based on whether programme has been previously ran
     * @param taskList lists of task to load from the local saved file
     */
    public void loadTasks (ArrayList<Task> taskList) {
        Boolean isFirstTime = false;
        try {
            isFirstTime = loadingTasks(taskList);
        } catch (IOException e) {
            ui.printIOException(e);
        } catch (SecurityException e) {
            ui.printSecurityException(e);
        }

        if (isFirstTime) {
            ui.printGreetings();
        } else {
            ui.printLoadMessage();
            ui.printLoadMessageComplete();
            ui.printListMessage(taskList);
        }
    }

    /**
     * Reads from local saved file and parse the task into taskList
     * @param taskList lists of task to load from the local saved file
     * @return true if it is the first time the user runs the programme, otherwise returns false
     * @throws IOException if an error has occurred while creating the file
     * @throws SecurityException if file cannot be accessed
     */
    public Boolean loadingTasks(ArrayList<Task> taskList) throws IOException, SecurityException {
        File file = new File(FILEPATH);
        try {
            if (file.exists()) {
                Scanner fileScan = new Scanner(file);
                while (fileScan.hasNext()) {
                    try {
                        parseTasks(fileScan.nextLine(), taskList);
                    } catch (DukeException e) {
                        ui.printDukeException(e);
                        ui.printCorruptedLoadMessage();
                        return file.createNewFile();
                    }
                }
                return false;
            } else {
                return file.getParentFile().mkdirs();
            }
        } catch (IOException e) {
            throw new IOException("Something went wrong during file creation :( ");
        } catch (SecurityException e) {
            throw new SecurityException("File could not be accessed");
        }
    }

    /**
     * Functions loads tasks by parsing the string input, it then adds the Task into the taskList by
     * calling the add method in ArrayList
     * @param line Input String to be parsed
     * @param list Input List for task to be saved into
     * @throws DukeException Either when line has been corrupted or when task status cannot be recognized
     */
    public void parseTasks(String line, ArrayList<Task> list) throws DukeException {
        int dividerPosition1 = line.indexOf(DIVIDER) + ARRAY_INDEX_FINDER;
        int dividerPosition2 = line.indexOf(DIVIDER, dividerPosition1) + ARRAY_INDEX_FINDER;
        int dividerPosition3 = line.indexOf(DIVIDER, dividerPosition2) + ARRAY_INDEX_FINDER;
        String name, by, isDone;
        try {
            name = line.substring(dividerPosition1, dividerPosition2 - 1).trim();
            by = line.substring(dividerPosition2, dividerPosition3 - 1).trim();
            isDone = line.substring(dividerPosition3).trim();
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("Line corrupted, deleting corrupted line");
        }
        String description = name;
        String date = by;
        String status = isDone;

        if (line.startsWith("T")) {
            list.add(new Todo(description));
            try {
                checkStatus(status, list);
            } catch (DukeException e) {
                e.printStatement();
            }
        } else if (line.startsWith("D")) {
            list.add(new Deadline(description, date));
            try {
                checkStatus(status, list);
            } catch (DukeException e) {
                e.printStatement();
            }
        } else if (line.startsWith("E")) {
            list.add(new Event(description, date));
            try {
                checkStatus(status, list);
            } catch (DukeException e) {
                e.printStatement();
            }
        } else {
            throw new DukeException("Task Status Syntax Not Recognised, Unable to Parse Request");
        }
    }

    /**
     * Function saves the lists in taskList into the local save file
     * @param taskList list of tasks to save to the local save file
     * @throws IOException when an error has occurred while trying to save the file
     */
    public void saveTasks(TaskList taskList) throws IOException {
        FileWriter fileWrite = new FileWriter(FILEPATH);
        fileWrite.close();
        for (Task task : taskList.taskList) {
            try {
                task.saveTask(FILEPATH);
            } catch (IOException e) {
                throw new IOException("Error Occurred While Saving File");
            }
        }
    }

    /**
     * Functions checks whether the isDone attribute of the Task is valid and sets the attribute to Done
     * when the attribute is true
     * @param status the isDone value of the Task
     * @param list the lists of Task currently in the programme
     * @throws DukeException when the attribute of the Task is not valid
     */
    public void checkStatus(String status, ArrayList<Task> list) throws DukeException {
        if (status.equals("true")) {
            list.get(getLastIndex(list)).setIsDone();
        } else if (!status.equals("false")) {
            throw new DukeException("Invalid Status");
        }
    }

    /**
     * Function returns the last index of the list that is currently occupied
     * @param list the lists of Task currently in the programme
     * @return the integer value of the last index that is occupied
     */
    public int getLastIndex(ArrayList<Task> list) {
        int index = list.size() - ARRAY_INDEX_FINDER;
        return Math.max(index, ZERO);
    }
}
