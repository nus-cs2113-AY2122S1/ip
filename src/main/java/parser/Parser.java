package parser;

import exceptions.DeadlineException;
import exceptions.EventException;
import tasklist.Task;

import java.util.ArrayList;

public class Parser {

    /**
     * Parses the user input to extract the command which is the first word.
     * @param userLine input given by user or line extracted from saved file
     * @return String[] a split between the command and task description from user
     */
    public static String[] parseCommand(String userLine) {
        return userLine.toLowerCase().split(" ", 2);
    }

    /**
     * Parses the user input to extract the task to be marked as done.
     * @param userLine input given by user or line extracted from saved file
     * @param taskList main list keeping track of user's tasks
     * @return taskNum int of the task in the list to be marked as done
     * @throws ArithmeticException if the taskNum is negative or is more than the size of list
     */
    public static int parseDoneTask(String userLine, ArrayList<Task> taskList) {
        String[] extractDoneTask = userLine.toLowerCase().split(" ", 2);
        int taskNum = Integer.parseInt(extractDoneTask[1]);
        if (taskNum <= 0 || taskNum > taskList.size()) {
            throw new ArithmeticException();
        }
        return taskNum;
    }

    /**
     * Parses the user input to extract the task to be deleted.
     * @param userLine input given by user or line extracted from saved file
     * @param taskList main list keeping track of user's tasks
     * @return taskNum int of the task in the list to be deleted
     * @throws ArithmeticException if the taskNum is negative or is more than the size of list
     */
    public static int parseDeleteTask(String userLine, ArrayList<Task> taskList) {
        String[] extractDeleteTask = userLine.toLowerCase().split(" ", 2);
        int taskNum = Integer.parseInt(extractDeleteTask[1]);
        if (taskNum <= 0 || taskNum > taskList.size()) {
            throw new ArithmeticException();
        }
        return taskNum;
    }

    /**
     * Parses the user input to extract the task description when todo command is called.
     * @param userLine input given by user or line extracted from saved file
     * @return str task description for todo task
     * @throws NumberFormatException if input is missing the description
     */
    public static String parseTodoTask(String userLine) {
        String[] todoInputs = userLine.split(" ", 2);
        if (todoInputs.length < 2) {
            throw new NumberFormatException();
        }
        return todoInputs[1];
    }

    /**
     * Parses the user input to extract the task description and deadline date & time when
     * deadline command is called.
     * @param userLine input given by user or line extracted from saved file
     * @return String[] where index 0 is the task description and index 1 is deadline date & time
     * @throws NumberFormatException if input is missing the description and deadline date & time
     * @throws DeadlineException if input does not contain '/by' key to split the description and
     * deadline date & time
     */
    public static String[] parseDeadlineTask(String userLine) throws DeadlineException {
        String[] deadlineInputs = userLine.split(" ", 2);
        if (deadlineInputs.length < 2) {
            throw new NumberFormatException();
        }
        if (!userLine.contains("/by")) {
            throw new DeadlineException();
        }
        return deadlineInputs[1].split(" /by ");
    }

    /**
     * Parses the user input to extract the task description and event date & time
     * when event command is called.
     * @param userLine input given by user or line extracted from saved file
     * @return String[] where index 0 is the task description and index 1 is event date & time
     * @throws NumberFormatException if input is missing the description and event date & time
     * @throws EventException if input does not contain '/by' key to split the description and
     * event date & time
     */
    public static String[] parseEventTask(String userLine) throws EventException {
        String[] eventInputs = userLine.split(" ", 2);
        if (eventInputs.length < 2) {
            throw new NumberFormatException();
        }
        if (!userLine.contains("/at")) {
            throw new EventException();
        }
        return eventInputs[1].split(" /at ");
    }

}
