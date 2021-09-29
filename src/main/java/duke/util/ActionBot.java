package duke.util;

import duke.exception.DukeException;
import duke.exception.EmptyListException;
import duke.exception.InvalidIndexException;
import duke.exception.NoKeywordException;
import duke.task.*;
import duke.ui.PrintBot;

import static duke.Duke.*;
import static duke.ui.ErrorReport.alarm;

/* This class manipulates the task list in the
 * way required by the user.
 */
public class ActionBot implements DukeActions {

    static final int DESCRIPTION = 0;
    static final int TIME = 1;
    //the escape character of stored data
    static final String STORAGE_ESCAPE = "\\|";

    public static String[] getDetails(String taskInput, String keyword) {
        String[] details = taskInput.split(keyword,2);

        details[TIME] = details[TIME].replace(keyword, "");

        String[] cleanDetails = new String[2];
        cleanDetails[0] = details[0].trim();
        cleanDetails[1] = details[1].trim();
        return cleanDetails;
    }

    @Override
    public void addTask(Task t) {
        tasks.add(t);
    }

    public Todo addTodo(String taskInput) {
        Todo t = new Todo(taskInput);
        duke.addTask(t);
        return t;
    }

    public Deadline addDeadline (String taskInput, String keyword) throws NoKeywordException {
        try {
            String[] details = getDetails(taskInput, keyword);
            Deadline d = new Deadline(details[DESCRIPTION], details[TIME]);
            duke.addTask(d);
            return d;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoKeywordException();
        }
    }

    public Event addEvent (String taskInput, String keyword) throws NoKeywordException {
        try {
            String[] details = getDetails(taskInput, keyword);
            Event e = new Event(details[DESCRIPTION], details[TIME]);
            duke.addTask(e);
            return e;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new NoKeywordException();
        }
    }

    public static Task getTask(int i) throws InvalidIndexException,EmptyListException {
        if (tasks.isEmpty()) {
            throw new EmptyListException();
        }
        if (i == -1 || i >= tasks.size()) {
            throw new InvalidIndexException();
        }

        return tasks.get(i);
    }

    @Override
    public void markDone(int id, boolean isDone) throws EmptyListException, InvalidIndexException {
        if (tasks.isEmpty()) {
            throw new EmptyListException();
        }

        int i = id -1;
        //index in string
        Task t = getTask(i);
        if (isDone) {
            t.setDone(true);
        }
    }

    @Override
    public void loadData(String data) {
        String[] details = data.split(STORAGE_ESCAPE,3);

        String type = details[0].trim();
        String status = details[1].trim();
        boolean isDone;
        isDone = status.equals("1");
        String taskInput = details[2].trim();

        switch (type) {
        case "T":
            Todo t = addTodo(taskInput);
            t.setDone(isDone);
            break;
        case "D":
            try {
                Deadline d = addDeadline(taskInput, STORAGE_ESCAPE);
                d.setDone(isDone);
            } catch (ArrayIndexOutOfBoundsException e) {
                alarm(Alarm.EMPTY_DEADLINE);
            } catch (NoKeywordException e) {
                alarm(Alarm.NO_DDL_KEYWORD);
            }
            break;
        case "E":
            try {
                Event e = duke.addEvent(taskInput, STORAGE_ESCAPE);
                e.setDone(isDone);
            } catch (ArrayIndexOutOfBoundsException e) {
                alarm(Alarm.EMPTY_EVENT);
            } catch (NoKeywordException e) {
                alarm(Alarm.NO_EVENT_KEYWORD);
            }
            break;
        default:
            PrintBot.print("Error reading file. Presence of invalid command. ");
            break;
        }
    }

    public void delete(int id) throws InvalidIndexException, EmptyListException {
        int i = id - 1;
        Task t = getTask(i);
        tasks.remove(i);
        print.delete(t);
    }
}
