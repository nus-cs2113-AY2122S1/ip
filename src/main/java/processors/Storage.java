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

    public void loadTasks (TaskList tasklist) {
        Boolean isSuccessful = false;
        try {
            isSuccessful = loadingTasks(tasklist);
        } catch (IOException e) {
            ui.printIOException(e);
        } catch (SecurityException e) {
            ui.printSecurityException(e);
        }
        if (isSuccessful) {
            ui.printGreetings();
        }
    }

    public Boolean loadingTasks(TaskList tasklist) throws IOException, SecurityException {
        File file = new File(FILEPATH);
        try {
            if (file.exists()) {
                ui.printLoadMessage();
                Scanner fileScan = new Scanner(file);
                while (fileScan.hasNext()) {
                    try {
                        parseTasks(fileScan.nextLine(), tasklist.taskList);
                    } catch (DukeException e) {
                        ui.printDukeException(e);
                        ui.printCorruptedLoadMessage();
                        return file.createNewFile();
                    }
                }
                ui.printLoadMessageComplete();
            } else {
                return file.getParentFile().mkdirs();
            }
        } catch (IOException e) {
            throw new IOException("Something went wrong during file creation :( ");
        } catch (SecurityException e) {
            throw new SecurityException("File could not be accessed");
        }
        return true;
    }

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
            throw new DukeException("Task Syntax Corrupted, Unable to Parse Request");
        }
    }

    public void saveTasks(TaskList tasklist) throws IOException {
        FileWriter fileWrite = new FileWriter(FILEPATH);
        fileWrite.close();
        for (Task task : tasklist.taskList) {
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
