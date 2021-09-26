package parser;

import exceptions.DeadlineException;
import exceptions.EventException;
import tasklist.Task;

import java.util.ArrayList;

public class Parser {
    public static String[] parseUserInput(String userLine) {
        return userLine.toLowerCase().split(" ", 2);
    }

    public static int parseDoneTask(String userLine, ArrayList<Task> taskList) {
        String[] extractDoneTask = userLine.toLowerCase().split(" ", 2);
        int taskNum = Integer.parseInt(extractDoneTask[1]);
        if (taskNum < 0 || taskNum > taskList.size()) {
            throw new ArithmeticException();
        }
        return taskNum;
    }

    public static int parseDeleteTask(String userLine, ArrayList<Task> taskList) {
        String[] extractDeleteTask = userLine.toLowerCase().split(" ", 2);
        int taskNum = Integer.parseInt(extractDeleteTask[1]);
        if (taskNum < 0 || taskNum > taskList.size()) {
            throw new ArithmeticException();
        }
        return taskNum;
    }

    public static String parseTodoTask(String userLine) {
        String[] todoInputs = userLine.split(" ", 2);
        if (todoInputs.length < 2) {
            throw new NumberFormatException();
        }
        return todoInputs[1];
    }

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

    public static String parseFindTaskKey(String userLine) {
        String[] userFindInputs = userLine.split(" ");
        return userFindInputs[1];
    }

}
