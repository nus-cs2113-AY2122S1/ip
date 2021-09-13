package duke;

import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDos;

import java.io.IOException;

public class Parser {
    static private final String COMMAND_LIST = "list";
    static private final String COMMAND_TODO = "todo";
    static private final String COMMAND_EVENT = "event";
    static private final String COMMAND_DEADLINE = "deadline";
    static private final String COMMAND_DONE = "done";
    static private final String COMMAND_DELETE = "delete";
    static private final String COMMAND_EXIT = "bye";

    static private final String INVALID_COMMAND = "Yo check your typing man. I don't get it.";

    static private final String COMMAND_DEADLINE_SEPARATOR = "/by";
    static private final String COMMAND_EVENT_SEPARATOR = "/at";

    static private final String SPACE_SEPARATOR = " ";
    static private final String EMPTY_STRING = "";

    static private final int EVENT_DEADLINE_ARGUMENT_COUNT = 2;


    private static ArrayList<Task> userTasks;
    private static TaskStorage storage;

    public Parser() {
        //userTasks = new ArrayList<>();
        try {
            storage = new TaskStorage();
            userTasks = storage.tasksFromFile();
        } catch (IOException e) {
            System.out.println("Something went wrong in file creation :(");
        }
    }


    public static String parse(String command) throws DukeException {
        String[] words = command.split(SPACE_SEPARATOR);

        if (words[0].equals(COMMAND_LIST)) {
            return parseListCommand();
        } else if (words[0].equals(COMMAND_TODO)) {
            return parseTodoCommand(command);
        } else if (words[0].equals(COMMAND_EVENT)) {
            return parseEventCommand(command);
        } else if (words[0].equals(COMMAND_DEADLINE)) {
            return parseDeadlineCommand(command);
        } else if (words[0].equals(COMMAND_DONE)) {
            return parseDoneCommand(command);
        } else if (words[0].equals(COMMAND_DELETE)) {
            return parseDeleteCommand(command);
        }
        else {
            throw new DukeException(INVALID_COMMAND);
        }
    }

    private static String parseDeleteCommand(String command) throws DukeException{
        String msg;
        String detail = command.substring(COMMAND_DELETE.length()).trim();

        if (detail.length() <= 0) {
            throw new DukeException("Noating to delete!");
        }

        try {
            int taskDeleteNumber = Integer.parseInt(detail);

            Task temp = storage.storageDeleteTask(taskDeleteNumber);
            msg = "Noted. I have removed this thing:\n "
                    + "\t" + temp.toString();

            return msg;
        } catch (NumberFormatException e) {
            throw new DukeException("Uhm that definitely not a dumber bro. Pick again.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Task number not exist!");
        } catch (IOException e) {
            throw new DukeException("Cannot delete task in memory");
        }
    }

    public boolean isExit(String command) {
        return (command.equals(COMMAND_EXIT));
    }

    private static String parseListCommand() {
        String msg = "";

        if (userTasks.size() == 0) {
            msg = "Nothing.";
            return msg;
        }

        for (int i = 0; i < userTasks.size() - 1; i++) {
            msg += (i + 1) + "."
                    + userTasks.get(i).toString() + '\n' + '\t';
        }
        msg = msg + userTasks.size() + "."
                + userTasks.get(userTasks.size() - 1).toString();

        return msg;
    }

    private static String parseTodoCommand(String command) throws DukeException {
        String msg;
        String detail = command.substring(COMMAND_TODO.length()).trim();

        if (detail.length() <= 0) {
            throw new DukeException("Bro please let me know what thing you gonna do");
        }

        try {
            storage.storageAddTask(new ToDos(detail, false));

            msg = "Gotcha. Do this while you're at it:\n"
                    + "\t\t" + userTasks.get(userTasks.size() - 1).toString() + '\n'
                    + "\tNow you have " + (userTasks.size())
                    + " tasks in the list.";

            return msg;
        } catch (IOException e) {
            throw new DukeException("Err I cannot add this to the memory");
        }
    }

    private static String parseDeadlineCommand(String command) throws DukeException {
        String msg;
        String detail = command.substring(COMMAND_DEADLINE.length()).trim();
        String[] contentAndDate = detail.split(COMMAND_DEADLINE_SEPARATOR);

        if (detail.length() <= 0) {
            throw new DukeException("Invalid format. Enter by this format:\n"
                    + "\t\t\"deadline [description] /by [deadline]\"");
        }

        for (int i = 0; i < contentAndDate.length; i++) {
            if (contentAndDate.length != EVENT_DEADLINE_ARGUMENT_COUNT
                    || contentAndDate[i].equals(EMPTY_STRING)) {
                throw new DukeException("Invalid format. Enter by this format:\n"
                        + "\t\t\"deadline [description] /by [deadline]\"");
            }
        }

        for (int i = 0; i < contentAndDate.length; i++) {
            contentAndDate[i] = contentAndDate[i].trim();
        }

        try {
            storage.storageAddTask(new Deadline(contentAndDate[0], contentAndDate[1], false));

            msg = "Gotcha. I beg you to do this:\n"
                    + "\t\t" + userTasks.get(userTasks.size() - 1).toString() + '\n'
                    + "\tNow you have " + (userTasks.size())
                    + " tasks in the list.";

            return msg;
        } catch (IOException e) {
            throw new DukeException("Err I cannot add this to the memory");
        }
    }

    private static String parseEventCommand(String command) throws DukeException {
        String msg;
        String detail = command.substring(COMMAND_EVENT.length()).trim();
        String[] contentAndDate = detail.split(COMMAND_EVENT_SEPARATOR);

        if (detail.length() <= 0) {
            throw new DukeException("Invalid format. Enter by this format:\n"
                    + "\t\t\"event [description] /at [date]\"");
        }

        for (int i = 0; i < contentAndDate.length; i++) {
            if (contentAndDate.length != EVENT_DEADLINE_ARGUMENT_COUNT
                    || contentAndDate[i].equals(EMPTY_STRING)) {
                throw new DukeException("Invalid format. Enter by this format:\n"
                        + "\t\t\"event [description] /at [date]\"");
            }
        }

        for (int i = 0; i < contentAndDate.length; i++) {
            contentAndDate[i] = contentAndDate[i].trim();
        }

        try {
            storage.storageAddTask(new Event(contentAndDate[0], contentAndDate[1], false));

            msg = "Gotcha. You wanna attend this:\n"
                    + "\t\t" + userTasks.get(userTasks.size() - 1).toString() + '\n'
                    + "\tNow you have " + (userTasks.size())
                    + " tasks in the list.";

            return msg;
        } catch (IOException e) {
            throw new DukeException("Err I cannot add this to the memory");
        }
    }

    private static String parseDoneCommand(String command) throws DukeException {
        String msg;
        String detail = command.substring(COMMAND_DONE.length()).trim();

        if (detail.length() <= 0) {
            throw new DukeException("Bro don't you say you've done all that.\n" +
                    "\tPick a number with your done task!");
        } else {
            try {
                int taskDoneNumber = Integer.parseInt(detail);

                storage.storageSetTaskDone(taskDoneNumber);

                msg = "Good job. You may now enjoy the rest of "
                        + "your suffering:\n"
                        + "\t" + userTasks.get(taskDoneNumber - 1).toString();

                return msg;
            } catch (NumberFormatException e) {
                throw new DukeException("Uhm that definitely not a dumber bro. Pick again.");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("Task number not exist!");
            } catch (IOException e) {
                throw new DukeException("Err I cannot set this as done in memory");
            }
        }
    }
}
