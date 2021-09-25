package Duke.BackEnd;

import Duke.Exception.DukeException;
import Duke.SaveFile.DataSaver;
import Duke.TaskTypes.Deadline;
import Duke.TaskTypes.Event;
import Duke.TaskTypes.Task;
import Duke.TaskTypes.Todo;

import java.util.ArrayList;

import static Duke.UI.DukeConstants.*;
import static Duke.UI.UserInterface.*;

public class DukeBackEnd {
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int num = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isValidDoneInstruction(String inWord, ArrayList<Task> taskList) {
        if (!inWord.contains(" ")) {
            return false;
        }

        String[] commands = DukeParser.parseDoneInstruction(inWord);
        if (commands.length != 2) {
            return false;
        }

        if(isNumeric(commands[1])) {
            int taskDoneIndex = Integer.parseInt(commands[1]);
            return taskDoneIndex > 0 && taskDoneIndex <= taskList.size();
        }

        return false;
    }

    public static void printTaskDone(String inWord, ArrayList<Task> taskList) throws DukeException {
        if (!isValidDoneInstruction(inWord, taskList)) {
            throw new DukeException();
        }

        String[] commands = DukeParser.parseDoneInstruction(inWord);
        int taskDoneIndex = Integer.parseInt(commands[1]);
        taskList.get(taskDoneIndex - 1).markAsDone();
        taskDoneMessage(taskDoneIndex, taskList);
    }

    public static void manageDoneInstruction(String inWord, ArrayList<Task> taskList) {
        try {
            printTaskDone(inWord, taskList);
        } catch (DukeException invalidDoneException){
            DukeException.invalidDoneException();
        }
    }

    public static boolean checkValidEvent(String inWord) {
        if (!inWord.contains(" ")) {
            return false;
        }

        //split inWord by the first whitespace(s) into 2 separate strings
        String[] commands = DukeParser.parseEventInstruction(inWord);
        if (commands.length != 2 || !inWord.contains(EVENT_KEYWORD)) {
            return false;
        }

        String[] description = commands[1].split(EVENT_KEYWORD, 2);
        if (description.length != 2) {
            return false;
        }

        String descriptionDetails = description[0].trim();
        String descriptionAt = description[1].trim();
        return !descriptionDetails.isEmpty() && !descriptionAt.isEmpty();
    }

    public static void printEvent(String inWord, ArrayList<Task> taskList) throws DukeException {
        //split inWord by the first whitespace(s) into 2 separate strings
        String[] commands = DukeParser.parseEventInstruction(inWord);

        if (checkValidEvent(inWord)) {
            Event newItem = DukeParser.parseEventDescription(commands);
            taskList.add(newItem);
            eventMessage(newItem, taskList);
        } else {
            throw new DukeException();
        }
    }

    public static void manageEvent(String inWord, ArrayList<Task> taskList) {
        try {
            printEvent(inWord, taskList);
            DataSaver.manageSave(taskList);
        } catch (DukeException invalidEventException) {
            DukeException.invalidEventException();
        }
    }

    public static boolean checkValidTodo(String inWord) {
        if (!inWord.contains(" ")) {
            return false;
        }

        //split inWord by the first whitespace(s) into 2 separate strings
        String[] commands = DukeParser.parseTodoInstruction(inWord);
        String details = commands[1];
        boolean isNonEmptyDetails = !details.isEmpty();
        return commands.length == 2 && isNonEmptyDetails;
    }

    public static void printTodo(String inWord, ArrayList<Task> taskList) throws DukeException {
        //split inWord by the first whitespace(s) into 2 separate strings
        String[] commands = DukeParser.parseTodoInstruction(inWord);

        if(!checkValidTodo(inWord)) {
            throw new DukeException();
        }

        String description = commands[1];
        Todo newItem = new Todo(description);
        taskList.add(newItem);
        todoMessage(newItem, taskList);
    }

    public static void manageTodo(String inWord, ArrayList<Task> taskList) {
        try {
            printTodo(inWord, taskList);
            DataSaver.manageSave(taskList);
        } catch (DukeException emptyTodoException) {
            DukeException.emptyTodoException();
        }
    }

    public static boolean checkValidDeadline(String inWord) {
        if (!inWord.contains(" ")) {
            return false;
        }

        //split inWord by the first whitespace(s) into 2 separate strings
        String[] commands = DukeParser.parseDeadlineInstruction(inWord);
        if (commands.length != 2 || !inWord.contains(DEADLINE_KEYWORD)) {
            return false;
        }

        String[] description = commands[1].split(DEADLINE_KEYWORD, 2);
        if (description.length != 2) {
            return false;
        }

        String descriptionDetails = description[0].trim();
        String descriptionBy = description[1].trim();
        return !descriptionDetails.isEmpty() && !descriptionBy.isEmpty();
    }

    public static void printDeadline(String inWord, ArrayList<Task> taskList) throws DukeException {
        //split inWord by the first whitespace(s) into 2 separate strings
        String[] commands = DukeParser.parseDeadlineInstruction(inWord);

        if (checkValidDeadline(inWord)) {
            Deadline newItem = DukeParser.parseDeadlineDescription(commands);
            taskList.add(newItem);
            deadlineMessage(newItem, taskList);
        } else {
            throw new DukeException();
        }
    }

    public static void manageDeadline(String inWord, ArrayList<Task> taskList) {
        try {
            printDeadline(inWord, taskList);
            DataSaver.manageSave(taskList);
        } catch (DukeException invalidDeadlineException) {
            DukeException.invalidDeadlineException();
        }
    }

    public static boolean isValidDeleteInstruction(String inWord, ArrayList<Task> taskList) {
        if (!inWord.contains(" ")) {
            return false;
        }

        String[] commands = DukeParser.parseDeleteInstruction(inWord);
        if (commands.length != 2) {
            return false;
        }

        if(isNumeric(commands[1])) {
            int taskDoneIndex = Integer.parseInt(commands[1]);
            return taskDoneIndex > 0 && taskDoneIndex <= taskList.size();
        }
        return false;
    }

    public static void printDelete(String inWord, ArrayList<Task> taskList) throws DukeException {
        if (!isValidDeleteInstruction(inWord, taskList)) {
            throw new DukeException();
        }

        String[] commands = inWord.split(" ");
        int taskDeleteIndex = Integer.parseInt(commands[1]);
        deleteMessage(taskDeleteIndex, taskList);

    }

    public static void manageDelete(String inWord, ArrayList<Task> taskList) {
        try {
            printDelete(inWord, taskList);
            DataSaver.manageSave(taskList);
        } catch (DukeException invalidDeleteException) {
            DukeException.invalidDeleteException();
        }
    }


}
