package task;

import error.exception.DukeEmptyTaskDescriptionException;
import error.exception.DukeInvalidDescriptionFormatException;
import task.subtask.Task;
import task.subtask.Todo;
import task.subtask.Deadline;
import task.subtask.Event;

import java.util.Scanner;

public class TaskManager extends Duke {
    private static final int INDEX_AFTER_TODO = 4;
    private static final int INDEX_AFTER_DEADLINE = 8;
    private static final int INDEX_AFTER_EVENT = 5;

    /**
     * Extracts the description, creates a todo Task object and stores the task in the list
     *
     * @param input is the command given by the user
     * @return the todo object created under Task
     * @throws DukeEmptyTaskDescriptionException if no description is present after todo command
     */
    public static Task getTodoDetails(String input) throws DukeEmptyTaskDescriptionException {
        if (input.substring(INDEX_AFTER_TODO).isBlank()) {
            throw new DukeEmptyTaskDescriptionException();
        }

        String todoDescription = input.substring(INDEX_AFTER_TODO).trim();

        Task todo = new Todo(todoDescription);
        list.add(count, todo);
        count++;

        return todo;
    }

    /**
     * Extracts the description and day/date, creates a deadline Task object and stores the task in the list
     *
     * @param input is the command given by the user
     * @return the deadline object created under Task
     * @throws DukeInvalidDescriptionFormatException if /by is not present in the command
     * @throws DukeEmptyTaskDescriptionException     if no description is present after deadline command
     */
    public static Task getDeadlineDetails(String input) throws DukeInvalidDescriptionFormatException, DukeEmptyTaskDescriptionException {
        if (input.substring(INDEX_AFTER_DEADLINE).isBlank()) {
            throw new DukeEmptyTaskDescriptionException();
        }

        if (!input.contains("/by")) {
            throw new DukeInvalidDescriptionFormatException();
        }

        // To extract description between the eight-letter word "deadline" and "/by"
        int endIndex = input.indexOf("/");
        String deadlineDescription = input.substring(INDEX_AFTER_DEADLINE, endIndex).trim();
        String deadlineDate = getDateFromCommand(input);

        Task deadline = new Deadline(deadlineDescription, deadlineDate);
        list.add(count, deadline);
        count++;

        return deadline;
    }

    /**
     * Extracts the description and the time, creates an event Task object and stores the task in the list
     *
     * @param input is the command given by the user
     * @return the event object created under Task
     * @throws DukeInvalidDescriptionFormatException if /at is not present in the command
     * @throws DukeEmptyTaskDescriptionException     if no description is present after event command
     */
    public static Task getEventDetails(String input) throws DukeInvalidDescriptionFormatException, DukeEmptyTaskDescriptionException {
        if (input.substring(INDEX_AFTER_EVENT).isBlank()) {
            throw new DukeEmptyTaskDescriptionException();
        }

        if (!input.contains("/at")) {
            throw new DukeInvalidDescriptionFormatException();
        }

        // To extract description between the five-letter word "event" and "/at"
        int endIndex = input.indexOf("/");
        String eventDescription = input.substring(INDEX_AFTER_EVENT, endIndex).trim();
        String eventDate = getDateFromCommand(input);

        Task event = new Event(eventDescription, eventDate);
        list.add(count, event);
        count++;

        return event;
    }

    public static String getUserInput(Scanner in) {
        String input;
        input = in.nextLine();

        return input;
    }

    /**
     * @param input is the command given by the user
     * @return the substring after the three-letter words /by or /at
     */
    protected static String getDateFromCommand(String input) {
        int startIndex = input.indexOf("/");

        return input.substring(startIndex + 3).trim();
    }

    /**
     * Extracts the index from the done command
     *
     * @param input is the command given by the user
     * @return the index of the task present in the task list
     */
    public static int getIndex(String input) {
        String[] temp = input.split(" ");
        int index = Integer.parseInt(temp[1]);
        index = index - 1;

        return index;
    }

    /**
     * Extracts the first word present in the command
     *
     * @param input is the command given by the user
     * @return the lowercase form of the first word present in the command
     */
    public static String getFirstWordFromCommand(String input) {
        return input.toLowerCase().split(" ")[0];
    }
}
