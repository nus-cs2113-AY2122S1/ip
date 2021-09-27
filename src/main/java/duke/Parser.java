package duke;

import duke.DukeExceptions.DukeException;
import duke.DukeExceptions.EmptyCommand;
import duke.DukeExceptions.InvalidCommandException;
import duke.DukeExceptions.InvalidValueException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class Parser {
    private TaskList taskList;
    private Ui ui;
    private String[] command;

    Parser(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    public String parse(String userInput) throws InvalidCommandException, EmptyCommand {
        try {
            if (userInput.trim().equals(""))
                throw new EmptyCommand();
            command = userInput.split(" ");
            switch (command[0].trim().toLowerCase()) {
            case "help":
                return parseHelp();
            case "bye":
                return parseBye();
            case "done":
                return parseDone(userInput);
            case "delete":
                return parseDelete(userInput);
            case "todo":
                return parseTodo(userInput);
            case "deadline":
                return parseDeadline(userInput);
            case "event":
                return parseEvent(userInput);
            case "list":
                return parseList();
            case "find":
                return parseFind(userInput);
            default:
                throw new InvalidCommandException();
            }
        } catch (DukeException e) {
            return e.printMessage();
        }
    }
    public String parseHelp() {
        return ui.helpList();
    }

    public String parseBye() {
        return ui.byeMessage();
    }

    public String parseDone(String command) throws InvalidValueException {
        validate(command);
        Task completedTask = taskList.markAsDone(command);
        return ui.doneMessage(completedTask);
    }

    public String parseDelete(String command) throws InvalidValueException {
        validate(command);
        int index = getIndex(command);
        Task targetTask = taskList.deleteTask(index);
        return ui.deleteMessage(taskList.getList(), index, targetTask);
    }

    public String parseTodo(String command) throws InvalidValueException {
        if (command.split(" ").length == 1)
            throw new InvalidValueException("Todo: Missing Description, Please Try Again");
        taskList.addTask(new ToDo(taskList.getItem(command)));
        return ui.acknowledgeAddition(taskList.getList());
    }

    public String parseDeadline(String command) throws InvalidValueException {
        if (command.split(" ").length == 1)
            throw new InvalidValueException("Deadline: Missing Description / Time");
        taskList.addTask(new Deadline(taskList.getDescription(command), taskList.getMoreDetails(command)));
        return ui.acknowledgeAddition(taskList.getList());
    }

    public String parseEvent(String command) throws InvalidValueException {
        if (command.split(" ").length == 1)
            throw new InvalidValueException("Event: Missing Description / Time");
        taskList.addTask(new Event(taskList.getDescription(command), taskList.getMoreDetails(command)));
        return ui.acknowledgeAddition(taskList.getList());
    }

    public String parseList() {
        return ui.printList(taskList);
    }

    private static int getIndex(String command) {
        String index = command.substring(command.indexOf(" ") + 1);
        return Integer.parseInt(index.trim());
    }

    private boolean isNumeric(String validate) {
        try {
            Integer.parseInt(validate);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private void validate(String command) throws InvalidValueException {
        String[] temp;
        temp = command.split(" ");
        if (temp.length == 1) {
            throw new InvalidValueException("Missing Item Number");
        } else if (temp.length > 2) {
            throw new InvalidValueException("Please be specific :) Expected 1 item number");
        } else if (!isNumeric(temp[1])) {
            throw new InvalidValueException("Oops, could you input item as a number please");
        } else if (getIndex(command) > this.taskList.getList().size() | getIndex(command) < 1) {
            throw new InvalidValueException(String.format("Input Number was more that [1 - %d] tasks in the list.", taskList.getList().size()));
        }
    }

    public String parseFind(String command) throws InvalidValueException {
        if (taskList.getList().size()==0)
            return ui.printList(taskList);
        String keyword = taskList.getItem(command);
        return ui.findResults(keyword, taskList);
    }
}