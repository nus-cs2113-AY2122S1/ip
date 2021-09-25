package Duke.Task;

import Duke.BackEnd.DukeParser;
import Duke.Commands.*;
import Duke.Exception.DukeException;
import Duke.TaskTypes.Deadline;
import Duke.TaskTypes.Event;
import Duke.TaskTypes.Task;
import Duke.TaskTypes.Todo;
import Duke.UI.UserInterface;


import static Duke.RunFile.DukeProgram.taskList;
import static Duke.UI.UserInterface.taskDoneMessage;

public class TaskList {

    public static int listSize() {
        return taskList.size();
    }

    public static void printTaskDone(String inWord) throws DukeException {
        if (DoneCommand.isValidDoneInstruction(inWord, taskList)) {
            String[] commands = DukeParser.parseDoneInstruction(inWord);
            int taskDoneIndex = Integer.parseInt(commands[1]);
            taskList.get(taskDoneIndex - 1).markAsDone();
            taskDoneMessage(taskDoneIndex, taskList);
        } else {
            throw new DukeException();
        }
    }

    public static void addTask(Task newTask) {
        taskList.add(newTask);
        UserInterface.taskAddedMessage(newTask, taskList);
    }

    public static void deleteTask(String inWord) throws DukeException {
        if (DeleteCommand.isValidDeleteInstruction(inWord, taskList)) {
            String[] commands = DukeParser.parseDeleteInstruction(inWord);
            int taskDeleteIndex = Integer.parseInt(commands[1]);
            Task deletedTask = taskList.get(taskDeleteIndex - 1);
            taskList.remove(taskDeleteIndex - 1);
            UserInterface.deleteMessage(taskDeleteIndex, deletedTask, taskList);
        } else {
            throw new DukeException();
        }
    }

    public static void addEvent(String inWord) throws DukeException {
        String[] commands = DukeParser.parseEventInstruction(inWord);

        if (AddEventCommand.isValidEvent(inWord)) {
            Event newEvent = DukeParser.parseEventDescription(commands);
            if (newEvent != null) {
                addTask(newEvent);
            }
        } else {
            throw new DukeException();
        }
    }

    public static void addTodo(String inWord) throws DukeException {
        String[] commands = DukeParser.parseTodoInstruction(inWord);

        if(AddTodoCommand.checkValidTodo(inWord)) {
            String description = commands[1];
            Todo newTodo = new Todo(description);
            addTask(newTodo);
        } else {
            throw new DukeException();
        }
    }

    public static void addDeadline(String inWord) throws DukeException {
        String[] commands = DukeParser.parseDeadlineInstruction(inWord);

        if (AddDeadlineCommand.checkValidDeadline(inWord)) {
            Deadline newDeadline = DukeParser.parseDeadlineDescription(commands);
            if (newDeadline != null) {
                addTask(newDeadline);
            }
        } else {
            throw new DukeException();
        }
    }
}
