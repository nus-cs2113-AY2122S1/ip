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

public class ProcessManager {
    /* ---- CONSTANTS ---- */
    private static final String EVENT_KEYWORD = "/at";
    private static final String DEADLINE_KEYWORD = "/by";
    private static final String DONE_DELETE_KEYWORD = " ";

    private static final Integer EVENT_DIVIDER = 6;
    private static final Integer DEADLINE_DIVIDER = 9;
    private static final Integer TODO_DIVIDER = 5;
    private static final Integer TASK_DESCRIPTION_DIVIDER = 1;
    private static final Integer TASK_DATE_DIVIDER = 4;
    private static final Integer ARRAY_INDEX_FINDER = 1;
    private static final Integer ZERO = 0;

    private static final String ADD_TASK_REPLY = "     Got it. I've added this task:\n";
    private static final String TASK_COMPLETED = "     Nice! I've marked this task as done:\n";
    private static final String LINE = "________________________________________________________________";
    private static final String LINE_DIVIDER = "________________________________________________________________\n";
    private static final String GAP = "     ";
    private static final String DELETED_TASK = "     Noted. I've removed this task:\n";
    private static final String FILEPATH = "data/SavedTask.txt";
    /* ---- --------- ---- */
    public ArrayList<Task> taskList = new ArrayList<>();

    /* ---- File Function ---- */
    public void loadTasks() {
        File file = new File(FILEPATH);
        try {
            if (file.exists()) {
                System.out.println("Welcome back to Duke!");
                System.out.println("Give me a moment while I set things up for you");
                System.out.println("Loading Tasks...");
                Scanner fileScan = new Scanner(file);
                while (fileScan.hasNext()) {
                    try {
                        parseTasks(fileScan.nextLine());
                    } catch (DukeException e) {
                        e.printStatement();
                    }
                }
                System.out.println("Task Successfully Imported\n");
            } else {
                file.getParentFile().mkdirs();
                System.out.println("I am your very own schedule assistant here to enhanced your everyday life");
                System.out.println("Type help to see what i can do!");
            }
        } catch (IOException e) {
            System.out.println("Something went wrong during file creation :( ");
        } catch (SecurityException e) {
            System.out.println("File could not be accessed");
        }
    }

    public void parseTasks(String line) throws DukeException {
        int dividerPosition1 = line.indexOf("|") + 1;
        int dividerPosition2 = line.indexOf("|", dividerPosition1) + 1;
        int dividerPosition3 = line.indexOf("|", dividerPosition2) + 1;
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
        Integer dividerPosition = line.indexOf(EVENT_KEYWORD);
        if (dividerPosition - TASK_DESCRIPTION_DIVIDER - EVENT_DIVIDER < 0) {
            throw new EventException("Event Request Does Not Contain A Description");
        }
        String description = line.substring(EVENT_DIVIDER, dividerPosition - TASK_DESCRIPTION_DIVIDER);
        if (dividerPosition + TASK_DATE_DIVIDER > line.length()) {
            throw new EventException("Event Request Does Not Contain A Date");
        }
        String date = line.substring(dividerPosition + TASK_DATE_DIVIDER);
        taskList.add(new Event(description, date));
        String output = taskAddedMessage();
        System.out.println(output);
    }

    public void handleDeadlineRequest(String line) throws DeadlineException {
        if (!line.contains(DEADLINE_KEYWORD)) {
            throw new DeadlineException("Deadline Request Does Not Contain /by");
        }
        Integer dividerPosition = line.indexOf(DEADLINE_KEYWORD);
        if (dividerPosition - TASK_DESCRIPTION_DIVIDER - DEADLINE_DIVIDER < 0) {
            throw new DeadlineException("Deadline Request Does Not Contain A Description");
        }
        String description = line.substring(DEADLINE_DIVIDER, dividerPosition - TASK_DESCRIPTION_DIVIDER);
        if (dividerPosition + TASK_DATE_DIVIDER > line.length()) {
            throw new DeadlineException("Deadline Request Does Not Contain A Date");
        }
        String date = line.substring(dividerPosition + TASK_DATE_DIVIDER);
        taskList.add(new Deadline(description, date));
        String output = taskAddedMessage();
        System.out.println(output);
    }

    public void handleToDoRequest(String line) throws TodoException {
        if (line.length() == TASK_DATE_DIVIDER) {
            throw new TodoException("Todo Request Does Not Contain A Description");
        }
        String description = line.substring(TODO_DIVIDER);
        taskList.add(new Todo(description));
        String output = taskAddedMessage();
        System.out.println(output);
    }

    public void handleDoneRequest(String line) throws DoneException {
        if (line.length() == TASK_DATE_DIVIDER) {
            throw new DoneException("Request Does Not Contain A Number");
        }
        if (line.length() == 4) {
            throw new DoneException("Done Request Does Not Contain A Number");
        }
        int dividerPosition = line.indexOf(DONE_DELETE_KEYWORD);
        String number = line.substring(dividerPosition + TASK_DESCRIPTION_DIVIDER);
        if (tryParse(number)) {
            throw new DoneException("Done Request Has Invalid Number");
        }
        int numberInTaskArray = Integer.parseInt(number) - TASK_DESCRIPTION_DIVIDER;
        if (getLastIndex() < numberInTaskArray || numberInTaskArray < ZERO) {
            throw new DoneException("Index Of Task Does Not Exist");
        }
        taskList.get(numberInTaskArray).setIsDone();
        String output = doneMessage(numberInTaskArray);
        System.out.println(output);
    }


    public void handleListRequest() {
        String output = LINE_DIVIDER;
        for (int number = 0; taskList.size() > number; number++) {
            String record = GAP + (number + ARRAY_INDEX_FINDER) + "." + taskList.get(number).toString() + "\n";
            output = output.concat(record);
        }
        if (taskList.size() == ZERO) {
            output = output.concat(GAP + "Nothing Has Been Added To The List Yet\n");
        }
        output = output.concat(LINE);
        System.out.println(output);
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
        int numberInTaskArray = Integer.parseInt(number) - TASK_DESCRIPTION_DIVIDER;
        if (getLastIndex() < numberInTaskArray || numberInTaskArray < ZERO) {
            throw new DeleteException("Array Out Of Bonds");
        }
        String output = deleteMessage(numberInTaskArray);
        System.out.println(output);
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
    /*--- -------- --- */

    /*--- Messages --- */
    public String taskAddedMessage() {
        return LINE_DIVIDER + ADD_TASK_REPLY
                + GAP + taskList.get(getLastIndex()).toString() + "\n"
                + getAddTaskReturn(getLastIndex() + ARRAY_INDEX_FINDER) + LINE;
    }

    public String doneMessage(Integer numberInTaskArray) {
        return LINE_DIVIDER + TASK_COMPLETED + GAP + "[X] "
                + taskList.get(numberInTaskArray).getDescription() + "\n" + LINE;
    }

    public String deleteMessage(Integer numberInTaskArray) {
        return LINE_DIVIDER + DELETED_TASK + GAP + taskList.get(numberInTaskArray).toString() + "\n"
                + getAddTaskReturn(getLastIndex()) + LINE;
    }

    public void goodbyeMessage() {
        String output = "File has been saved!\n"
                + LINE_DIVIDER + GAP + "Bye. Hope to see you again soon!\n" + LINE;
        System.out.println(output);
    }

    public void welcomeMessage() {
        String output = LINE_DIVIDER
                + "     Hello! I'm\n"
                + "     ____        _\n"
                + "    |  _ \\ _   _| | _____\n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    |____/ \\__,_|_|\\_\\___|\n"
                + LINE;
        System.out.println(output);
    }

    public String getAddTaskReturn(int number) {
        return "     Now you have " + (number) + " tasks in the list.\n";
    }

    public String help() {
        return LINE_DIVIDER
                + "     Duke can do the follow instructions\n"
                + "     1. Record Todo Task: todo (description)\n"
                + "     2. Record Deadline Task: task (description) /by (date)\n"
                + "     3. Record Event Task: event (description) /at (date)\n"
                + "     4. List Down Recorded Tasks: list\n"
                + "     5. Set Task After Completion: done (index on list)\n"
                + "     6. Exit From Program: bye\n" + LINE;
    }
    /* --- ---- --- --- */
}