package task;

import error.exception.DukeEmptyTaskDescriptionException;
import error.exception.DukeInvalidDescriptionFormatException;
import task.subtask.Todo;
import task.subtask.Deadline;
import task.subtask.Event;

import java.util.Scanner;

public class TaskManager extends Duke {
    private static final int INDEX_AFTER_TODO = 4;
    private static final int INDEX_AFTER_DEADLINE = 8;
    private static final int INDEX_AFTER_EVENT = 5;

    public static Task getTodoDetails(String input) throws DukeEmptyTaskDescriptionException {
        // Throw exception if no description is followed by todo
        if (input.substring(INDEX_AFTER_TODO).isEmpty()) {
            throw new DukeEmptyTaskDescriptionException();
        }

        // To extract description starting after the four letter word "todo"
        String todoDescription = input.substring(INDEX_AFTER_TODO).trim();

        Task todo = new Todo(todoDescription);
        list.add(count,todo);
        count++;

        return todo;
    }

    public static Task getDeadlineDetails(String input) throws DukeInvalidDescriptionFormatException, DukeEmptyTaskDescriptionException {
        // Throw exception if no description is followed by deadline
        if (input.substring(INDEX_AFTER_DEADLINE).isEmpty()) {
            throw new DukeEmptyTaskDescriptionException();
        }

        // Throw exception if /by is not present in the deadline description
        if (!input.contains("/by")) {
            throw new DukeInvalidDescriptionFormatException();
        }

        // To extract description between the eight-letter word "deadline" and "/by"
        int endIndex = input.indexOf("/");
        String deadlineDescription = input.substring(INDEX_AFTER_DEADLINE, endIndex);
        String deadlineDate = getDateFromCommand(input);

        Task deadline = new Deadline(deadlineDescription, deadlineDate);
        list.add(count,deadline);
        count++;

        return deadline;
    }

    public static Task getEventDetails(String input) throws DukeInvalidDescriptionFormatException, DukeEmptyTaskDescriptionException {
        // Throw exception if no description is followed by event
        if (input.substring(INDEX_AFTER_EVENT).isEmpty()) {
            throw new DukeEmptyTaskDescriptionException();
        }

        // Throw exception if /at is not present in the event description
        if (!input.contains("/at")) {
            throw new DukeInvalidDescriptionFormatException();
        }

        // To extract description between the five-letter word "event" and "/at"
        int endIndex = input.indexOf("/");
        String eventDescription = input.substring(INDEX_AFTER_EVENT, endIndex);
        String eventDate = getDateFromCommand(input);

        Task event = new Event(eventDescription, eventDate);
        list.add(count,event);
        count++;

        return event;
    }

    protected static String getDateFromCommand(String input) {
        int startIndex = input.indexOf("/");

        // Extract substring after the three-letter words /by or /at
        return input.substring(startIndex + 3).trim();
    }


    public static String getUserInput(Scanner in) {
        String input;
        input = in.nextLine();

        return input;
    }

    public static int getIndex(String input) {
        String[] temp = input.split(" ");
        int index = Integer.parseInt(temp[1]);
        index = index - 1;

        return index;
    }

    public static String getFirstWordFromCommand(String input) {
        return input.toLowerCase().split(" ")[0];
    }
}
