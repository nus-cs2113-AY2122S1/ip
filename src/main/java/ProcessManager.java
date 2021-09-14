import exceptions.DeadlineException;
import exceptions.DoneException;
import exceptions.EventException;
import exceptions.TodoException;
import exceptions.DukeException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ProcessManager {
    /* ---- CONSTANTS ---- */
    private static final String EVENT_KEYWORD = "/at";
    private static final String DEADLINE_KEYWORD = "/by";
    private static final String DONE_KEYWORD = " ";

    private static final Integer EVENT_DIVIDER = 6;
    private static final Integer DEADLINE_DIVIDER = 9;
    private static final Integer TODO_DIVIDER = 5;
    private static final Integer TASK_DESCRIPTION_DIVIDER = 1;
    private static final Integer TASK_DATE_DIVIDER = 4;

    private static final String ADD_TASK_REPLY = "     Got it. I've added this task:\n";
    private static final String TASK_COMPLETED = "     Nice! I've marked this task as done: \n";
    private static final String LINE = "    ____________________________________________________________";
    private static final String LINE_DIVIDER = "    ____________________________________________________________\n";
    private static final String GAP = "     ";

    private static final String FILEPATH = "data/SavedTask.txt";

    /* ---- --------- ---- */
    public Task[] toDo = new Task[100];
    public Integer trackIndex = 0;

    /* ---- File Function ---- */
    public void loadTasks() {
        File file = new File(FILEPATH);
        try {
            if (file.exists()) {
                System.out.println("Welcome back to Duke!");
                System.out.println("Give me a moment while I set things up for you");
                Scanner fileScan = new Scanner(file);
                while (fileScan.hasNext()) {
                    try {
                        parseTasks(fileScan.nextLine());
                    } catch (DukeException e) {
                        e.printStatement();
                    }
                }
            } else {
                file.getParentFile().mkdirs();
            }
        } catch (IOException e) {
            System.out.println("Something went wrong during file creation :( ");
        } catch (SecurityException e) {
            System.out.println("File could not be accessed");
        } finally {
            System.out.println("Loading Tasks...");
            System.out.println("Task Successfully Imported\n");
        }
    }

    //Data Format T | <Description> | <Date, null> | <status>
    public void parseTasks(String line) throws DukeException {
        int dividerPosition1 = line.indexOf("|") + 1;
        int dividerPosition2 = line.indexOf("|", dividerPosition1) + 1;
        int dividerPosition3 = line.indexOf("|", dividerPosition2) + 1;
        String description = line.substring(dividerPosition1, dividerPosition2 - 1).trim();
        String date = line.substring(dividerPosition2, dividerPosition3 - 1).trim();
        String status = line.substring(dividerPosition3).trim();
        if (line.startsWith("T")) {
            toDo[trackIndex] = new Todo(description);
            try {
                checkStatus(status);
            } catch (DukeException e) {
                e.printStatement();
            }
            trackIndex++;
        } else if (line.startsWith("D")) {
            toDo[trackIndex] = new Deadline(description, date);
            try {
                checkStatus(status);
            } catch (DukeException e) {
                e.printStatement();
            }
            trackIndex++;
        } else if (line.startsWith("E")) {
            toDo[trackIndex] = new Event(description, date);
            try {
                checkStatus(status);
            } catch (DukeException e) {
                e.printStatement();
            }
            trackIndex++;
        } else {
            throw new DukeException("Task Syntax Corrupted, Unable to Parse Request");
        }
    }

    public void saveTasks() throws IOException {
        FileWriter fileWrite = new FileWriter(FILEPATH);
        fileWrite.close();
        for (int i = 0; trackIndex > i; i++) {
            try {
                toDo[i].saveTask(FILEPATH);
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
        toDo[trackIndex] = new Event(description, date);
        String output = LINE_DIVIDER + ADD_TASK_REPLY
                + GAP + toDo[trackIndex].toString() + "\n"
                + getAddTaskReturn(trackIndex) + LINE;
        System.out.println(output);
        trackIndex++;
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
        toDo[trackIndex] = new Deadline(description, date);
        String output = LINE_DIVIDER + ADD_TASK_REPLY
                + GAP + toDo[trackIndex].toString() + "\n"
                + getAddTaskReturn(trackIndex) + LINE;
        System.out.println(output);
        trackIndex++;
    }

    public void handleToDoRequest(String line) throws TodoException {
        if (line.length() == 4) {
            throw new TodoException("Todo Request Does Not Contain A Description");
        }
        String description = line.substring(TODO_DIVIDER);
        toDo[trackIndex] = new Todo(description);
        String output = LINE_DIVIDER + ADD_TASK_REPLY
                + GAP + toDo[trackIndex].toString() + "\n"
                + getAddTaskReturn(trackIndex) + LINE;
        System.out.println(output);
        trackIndex++;
    }

    public void handleDoneRequest(String line) throws DoneException {
        if (line.length() == 4) {
            throw new DoneException("Done Request Does Not Contain A Number");
        }
        int dividerPosition = line.indexOf(DONE_KEYWORD);
        String number = line.substring(dividerPosition + TASK_DESCRIPTION_DIVIDER);
        if (tryParse(number)) {
            throw new DoneException("Done Request Has Invalid Number");
        }
        int numberInTaskArray = Integer.parseInt(number) - TASK_DESCRIPTION_DIVIDER;
        if (trackIndex <= numberInTaskArray) {
            throw new DoneException("Array Out Of Bonds");
        }
        toDo[numberInTaskArray].setIsDone();
        String output = LINE_DIVIDER + TASK_COMPLETED + GAP + "[X] " 
                + toDo[numberInTaskArray].getDescription() + "\n" + LINE;
        System.out.println(output);
    }

    public void handleListRequest() {
        String output = LINE_DIVIDER;
        for (int number = 0; trackIndex > number; number++) {
            String record = GAP + (number + 1) + "." + toDo[number].toString() + "\n";
            output = output.concat(record);
        }
        if (trackIndex == 1) {
            output = output.concat("Nothing Has Been Added To The List Yet");
        }
        output = output.concat(LINE);
        System.out.println(output);
    }
    /* ---- -------- ---- */

    /* ---- Function ---- */
    public boolean tryParse(String text) {
        try {
            Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }

    public void checkStatus(String status) throws DukeException {
        if (status.equals("true")) {
            toDo[trackIndex].setIsDone();
        } else if (status.equals("false")) {
            toDo[trackIndex].setIsDone();
        } else {
            throw new DukeException("Invalid Status");
        }
    }
    /*--- -------- --- */


    /* ---- Messages ---- */
    public void goodbyeMessage() {
        String output = LINE_DIVIDER + GAP + "Bye. Hope to see you again soon!\n" + LINE;
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
                + "     What can I do for you?\n"
                + LINE;
        System.out.println(output);
    }

    public String getAddTaskReturn(Integer trackIndex) {
        return "     Now you have " + (trackIndex + 1) + " tasks in the list.\n";
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
    /* ---- -------- ---- */
}