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

    public void loadTasks(ProcessManager processManager) throws IOException, SecurityException {
        File file = new File(FILEPATH);
        try {
            if (file.exists()) {
                ui.printLoadMessage();
                Scanner fileScan = new Scanner(file);
                while (fileScan.hasNext()) {
                    try {
                        parseTasks(fileScan.nextLine(), processManager.taskList);
                    } catch (DukeException e) {
                        e.printStatement();
                    }
                }
                ui.printLoadMessageComplete();
            } else {
                file.getParentFile().mkdirs();
                ui.printGreetings();
            }
        } catch (IOException e) {
            throw new IOException("Something went wrong during file creation :( ");
        } catch (SecurityException e) {
            throw new SecurityException("File could not be accessed");
        }
    }

    public void parseTasks(String line, ArrayList<Task> list) throws DukeException {
        int dividerPosition1 = line.indexOf(DIVIDER) + ARRAY_INDEX_FINDER;
        int dividerPosition2 = line.indexOf(DIVIDER, dividerPosition1) + ARRAY_INDEX_FINDER;
        int dividerPosition3 = line.indexOf(DIVIDER, dividerPosition2) + ARRAY_INDEX_FINDER;
        String description = line.substring(dividerPosition1, dividerPosition2 - 1).trim();
        String date = line.substring(dividerPosition2, dividerPosition3 - 1).trim();
        String status = line.substring(dividerPosition3).trim();
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
            throw new DukeException("Task Syntax Corrupted, Unable to Parse Request");
        }
    }

    public void saveTasks(ProcessManager processManager) throws IOException {
        FileWriter fileWrite = new FileWriter(FILEPATH);
        fileWrite.close();
        for (Task task : processManager.taskList) {
            try {
                task.saveTask(FILEPATH);
            } catch (IOException e) {
                throw new IOException("Error Occurred While Saving File");
            }
        }
    }

    public void checkStatus(String status, ArrayList<Task> list) throws DukeException {
        if (status.equals("true")) {
            list.get(getLastIndex(list)).setIsDone();
        } else if (!status.equals("false")) {
            throw new DukeException("Invalid Status");
        }
    }

    public int getLastIndex(ArrayList<Task> list) {
        int index = list.size() - ARRAY_INDEX_FINDER;
        return Math.max(index, ZERO);
    }
}
