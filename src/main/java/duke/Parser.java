package duke;

import duke.DukeExceptions.EmptyCommand;
import duke.DukeExceptions.IllegalToDoException;
import duke.DukeExceptions.InvalidCommandException;
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
            case "bye":
                return parseBye();
            case "done":
                return parseDone(userInput);
            case "delete":
                return parseDelete(getIndex(userInput));
            case "todo":
                return parseTodo(userInput);
            case "deadline":
                return parseDeadline(userInput);
            case "event":
                return parseEvent(userInput);
            case "list":
                return parseList();
            default:
                throw new InvalidCommandException("No command detected, check if you input commands correctly :)");
            }
        } catch (InvalidCommandException e) {
            return e.printMessage();
        }
    }

    public String parseBye() {
        return ui.byeMessage();
    }

    public String parseDone(String command) {
        assert command.split(" ").length == 2 : "Format: [done] [number]";
        Task completedTask = taskList.markAsDone(command);
        return ui.doneMessage(completedTask);
    }

    public String parseDelete(int index) {
        Task targetTask = taskList.deleteTask(index);
        return ui.deleteMessage(taskList.getList(), index, targetTask);
    }

    public String parseTodo(String command) {
        taskList.addTask(new ToDo(taskList.getTodo(command)));
        return ui.acknowledgeAddition(taskList.getList());
    }

    public String parseDeadline(String command) {
        taskList.addTask(new Deadline(taskList.getDescription(command), taskList.getDate(command)));
        return ui.acknowledgeAddition(taskList.getList());
    }

    public String parseEvent(String command) {
        taskList.addTask(new Event(taskList.getDescription(command), taskList.getDate(command)));
        return ui.acknowledgeAddition(taskList.getList());
    }

    public String parseList() {
        return ui.printList(taskList);
    }

    private static int getIndex(String command) {
        String index = command.substring(command.indexOf(" ") + 1);
        return Integer.parseInt(index.trim());
    }
}

//    String item = "";
//    String command = getCommand(userInput);
//        if (isEvent(command)) {
//        item = notToDoItem(userInput);
//    } else if (isDeadline(command)) {
//        item = notToDoItem(userInput);
//    } else if (isInvalidCommand(command)) {
//        throw new InvalidCommandException();
//    } else if (userInput.length() > 3) {
//        item = getRequiredSubstring(userInput, " ", 1);
//        if (item.trim().equals("") || item.equalsIgnoreCase("todo")) {
//            throw new IllegalToDoException();
//        }
//    } else if (isEmpty(command)) {
//        throw new EmptyCommand();
//    }
//        return item;
