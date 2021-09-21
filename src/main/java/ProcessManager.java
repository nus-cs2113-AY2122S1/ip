import exceptions.EventException;
import exceptions.TodoException;
import exceptions.DoneException;
import exceptions.DeadlineException;
import exceptions.DeleteException;
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
import ui.Ui;

public class ProcessManager {
    private static final String EVENT_KEYWORD = "/at";
    private static final String DEADLINE_KEYWORD = "/by";
    private static final String DONE_DELETE_KEYWORD = " ";
    private static final String DIVIDER = "|";

    private static final Integer KEYWORD_LENGTH = 2;
    private static final Integer EVENT_DIVIDER = 6;
    private static final Integer DEADLINE_DIVIDER = 9;
    private static final Integer TODO_DIVIDER = 5;
    private static final Integer TASK_DESCRIPTION_DIVIDER = 1;
    private static final Integer TASK_DATE_DIVIDER = 4;
    private static final Integer ARRAY_INDEX_FINDER = 1;
    private static final Integer ZERO = 0;
    private static final Integer DONE_LENGTH = 4;

    private static final String FILEPATH = "data/SavedTask.txt";

    public Ui ui = new Ui();
    public ArrayList<Task> taskList = new ArrayList<>();

    /* ---- File Function ---- */

    public void loadTasks() throws IOException, SecurityException {
        File file = new File(FILEPATH);
        try {
            if (file.exists()) {
                ui.printLoadMessage();
                Scanner fileScan = new Scanner(file);
                while (fileScan.hasNext()) {
                    try {
                        parseTasks(fileScan.nextLine());
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

    public void parseTasks(String line) throws DukeException {
        int dividerPosition1 = line.indexOf(DIVIDER) + ARRAY_INDEX_FINDER;
        int dividerPosition2 = line.indexOf(DIVIDER, dividerPosition1) + ARRAY_INDEX_FINDER;
        int dividerPosition3 = line.indexOf(DIVIDER, dividerPosition2) + ARRAY_INDEX_FINDER;
        String description = line.substring(dividerPosition1, dividerPosition2 - 1).trim();
        String date = line.substring(dividerPosition2, dividerPosition3 - 1).trim();
        String status = line.substring(dividerPosition3).trim();
        if (line.startsWith("T")) {
            taskList.add(new Todo(description));
            try {
                checkStatus(status);
            } catch (DukeException e) {
                e.printStatement();
            }
        } else if (line.startsWith("D")) {
            taskList.add(new Deadline(description, date));
            try {
                checkStatus(status);
            } catch (DukeException e) {
                e.printStatement();
            }
        } else if (line.startsWith("E")) {
            taskList.add(new Event(description, date));
            try {
                checkStatus(status);
            } catch (DukeException e) {
                e.printStatement();
            }
        } else {
            throw new DukeException("Task Syntax Corrupted, Unable to Parse Request");
        }
    }

    public void saveTasks() throws IOException {
        FileWriter fileWrite = new FileWriter(FILEPATH);
        fileWrite.close();
        for (Task task : taskList) {
            try {
                task.saveTask(FILEPATH);
            } catch (IOException e) {
                throw new IOException("Error Occurred While Saving File");
            }
        }
    }
    /* ---- ------------- ---- */

    /* ---- Handle Functions ---- */

    public void handleEventRequest(String line) throws EventException {
        if (!line.contains(EVENT_KEYWORD)) {
            throw new EventException("Event Request Does Not Contain /at");
        }
        int dividerPosition = line.indexOf(EVENT_KEYWORD);

        String description = line.substring(EVENT_DIVIDER, dividerPosition).trim();
        int descriptionLength = description.length();
        if (descriptionLength == ZERO) {
            throw new EventException("Event Request Does Not Contain A Description");
        }

        if (isDateExist(line.length(), dividerPosition)) {
            throw new EventException("Event Request Does Not Contain A Date");
        }
        String date = line.substring(dividerPosition + TASK_DATE_DIVIDER);

        taskList.add(new Event(description, date));
        ui.printTaskMessage(taskList.get(getLastIndex()), taskList.size());
    }

    public void handleDeadlineRequest(String line) throws DeadlineException {
        if (!line.contains(DEADLINE_KEYWORD)) {
            throw new DeadlineException("Deadline Request Does Not Contain /by");
        }
        int dividerPosition = line.indexOf(DEADLINE_KEYWORD);

        String description = line.substring(DEADLINE_DIVIDER, dividerPosition).trim();
        int descriptionLength = description.length();
        if (descriptionLength == ZERO) {
            throw new DeadlineException("Deadline Request Does Not Contain A Description");
        }

        if (isDateExist(line.length(), dividerPosition)) {
            throw new DeadlineException("Deadline Request Does Not Contain A Date");
        }
        String date = line.substring(dividerPosition + TASK_DATE_DIVIDER);

        taskList.add(new Deadline(description, date));

        ui.printTaskMessage(taskList.get(getLastIndex()), taskList.size());
    }

    public void handleToDoRequest(String line) throws TodoException {
        if (line.length() == TASK_DATE_DIVIDER) {
            throw new TodoException("Todo Request Does Not Contain A Description");
        }
        String description = line.substring(TODO_DIVIDER);

        taskList.add(new Todo(description));

        ui.printTaskMessage(taskList.get(getLastIndex()), taskList.size());
    }

    public void handleDoneRequest(String line) throws DoneException {
        if (line.length() == TASK_DATE_DIVIDER) {
            throw new DoneException("Request Does Not Contain A Number");
        }

        if (line.length() == DONE_LENGTH) {
            throw new DoneException("Done Request Does Not Contain A Number");
        }
        int dividerPosition = line.indexOf(DONE_DELETE_KEYWORD);

        String number = line.substring(dividerPosition + TASK_DESCRIPTION_DIVIDER);
        if (tryParse(number)) {
            throw new DoneException("Done Request Has Invalid Number");
        }
        int numberInTaskArray = Integer.parseInt(number) - ARRAY_INDEX_FINDER;

        if (isIndexExist(numberInTaskArray)) {
            throw new DoneException("Index Of Task Does Not Exist");
        }

        taskList.get(numberInTaskArray).setIsDone();

        ui.printDoneMessage(taskList.get(getLastIndex()));
    }

    public void handleListRequest() {
        ui.printListMessage(taskList);
    }

    public void handleDeleteRequest(String line) throws DeleteException {
        if (line.length() == TASK_DATE_DIVIDER) {
            throw new DeleteException("Request Does Not Contain A Number");
        }
        int dividerPosition = line.indexOf(DONE_DELETE_KEYWORD);

        String number = line.substring(dividerPosition + TASK_DESCRIPTION_DIVIDER);
        if (tryParse(number)) {
            throw new DeleteException("Request Has Invalid Number");
        }
        int numberInTaskArray = Integer.parseInt(number) - ARRAY_INDEX_FINDER;

        if (isIndexExist(numberInTaskArray)) {
            throw new DeleteException("Array Out Of Bonds");
        }

        ui.printDeleteMessage(taskList.get(getLastIndex()), taskList.size());

        taskList.remove(numberInTaskArray);
    }

    /* --- Function --- */

    public boolean tryParse(String text) {
        try {
            Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }

    public int getLastIndex() {
        int index = taskList.size() - ARRAY_INDEX_FINDER;
        return Math.max(index, ZERO);
    }

    public void checkStatus(String status) throws DukeException {
        if (status.equals("true")) {
            taskList.get(getLastIndex()).setIsDone();
        } else if (!status.equals("false")) {
            throw new DukeException("Invalid Status");
        }
    }

    public boolean isDateExist(Integer length, Integer index) {
        return length == index + KEYWORD_LENGTH + ARRAY_INDEX_FINDER;
    }

    public boolean isIndexExist(Integer index) {
        return taskList.isEmpty() || getLastIndex() < index || index < ZERO;
    }
}