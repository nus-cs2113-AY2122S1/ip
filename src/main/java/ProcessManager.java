import exceptions.DeadlineException;
import exceptions.DoneException;
import exceptions.EventException;
import exceptions.TodoException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

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
    /* ---- --------- ---- */
    public Task[] toDo = new Task[100];

    public void handleEventRequest(String line, Integer trackIndex) throws EventException {
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
    }

    public void handleDeadlineRequest(String line, Integer trackIndex) throws DeadlineException {
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
    }

    public void handleToDoRequest(String line, Integer trackIndex) throws TodoException {
        if (line.length() == 4) {
            throw new TodoException("Todo Request Does Not Contain A Description");
        }
        String description = line.substring(TODO_DIVIDER);
        toDo[trackIndex] = new Todo(description);
        String output = LINE_DIVIDER + ADD_TASK_REPLY
                + GAP + toDo[trackIndex].toString() + "\n"
                + getAddTaskReturn(trackIndex) + LINE;
        System.out.println(output);
    }

    public void handleDoneRequest(String line, Integer trackIndex) throws DoneException {
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

    public void handleListRequest(Integer trackIndex) {
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

    /*--- Function --- */
    public boolean tryParse(String text) {
        try {
            Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return true;
        }
        return false;
    }
    /*--- -------- --- */

    /*--- Messages --- */
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
    /*--- -------- --- */
}