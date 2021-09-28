package duke;

import duke.DukeExceptions.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.time.format.DateTimeParseException;

public class Parser {
    private final TaskList taskList;
    private final Ui ui;

    /**
     * Constructor to create Parser object.
     *
     * @param taskList TaskList that is currently in use.
     * @param ui       Ui object.
     */
    Parser(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Breakdown user input to run user commands.
     *
     * @param userInput String input from user.
     * @return Executed command ui.
     * @throws InvalidCommandException if there are no recognisable command.
     * @throws EmptyCommand            if the input is empty.
     */
    public String parse(String userInput) throws InvalidCommandException, EmptyCommand {
        try {
            if (userInput.trim().equals(""))
                throw new EmptyCommand();
            String[] command = userInput.split(" ");
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
            return e.toString();
        } catch (DateTimeParseException e) {
            return ui.dateTimeFormat();
        } catch (StringIndexOutOfBoundsException e) {
            return new InvalidValueException("Please check if you input Date / Time").toString();
        }
    }

    /**
     * execute <help> command
     *
     * @return list of all commands available
     */
    public String parseHelp() {
        return ui.helpList();
    }

    /**
     * Execute <bye> command.
     *
     * @return "bye" message.
     */
    public String parseBye() {
        return ui.byeMessage();
    }

    /**
     * Execute <done> command.
     *
     * @param command Input from user.
     * @return "done" message.
     * @throws InvalidValueException if user input is in the wrong format.
     */
    public String parseDone(String command) throws InvalidValueException {
        validate(command);
        String statusMessage = taskList.markAsDone(command);
        return ui.doneMessage(statusMessage);
    }

    /**
     * Execute <delete> command.
     *
     * @param command Input from user.
     *                * @return "deleted" message.
     *                * @throws InvalidValueException if user input is in the wrong format.
     */
    public String parseDelete(String command) throws InvalidValueException {
        validate(command);
        int index = getIndex(command);
        Task targetTask = taskList.deleteTask(index);
        return ui.deleteMessage(taskList.getList(), index, targetTask);
    }

    /**
     * Execute <Todo> command.
     *
     * @param command Input from user.
     * @return String acknowledging new addition.
     * @throws InvalidValueException if user input is in the wrong format.
     */
    public String parseTodo(String command) throws InvalidValueException {
        if (command.split(" ").length == 1)
            throw new InvalidValueException("Todo: Missing Description, Please Try Again");
        taskList.addTask(new ToDo(TaskList.getItem(command)));
        return ui.acknowledgeAddition(taskList.getList());
    }

    /**
     * Execute <deadline> command.
     *
     * @param command Input from user.
     * @return String acknowledging new addition.
     * @throws InvalidValueException if user input is in the wrong format.
     */
    public String parseDeadline(String command) throws InvalidValueException {
        if (command.split(" ").length == 1)
            throw new InvalidValueException("Deadline: Missing Description / Time");
        if (command.split("/").length == 3)
            throw new InvalidValueException("Please input Date in the following format: <dd/mm/yyyy>");
        taskList.addTask(new Deadline(taskList.getDescription(command), taskList.getMoreDetails(command)));
        return ui.acknowledgeAddition(taskList.getList());
    }

    /**
     * Execute <event> command.
     *
     * @param command Input from user.
     * @return String acknowledging new addition.
     * @throws InvalidValueException if user input is in the wrong format.
     */
    public String parseEvent(String command) throws InvalidValueException {
        if (command.split(" ").length == 1)
            throw new InvalidValueException("Event: Missing Description / Time");
        if (command.split("/").length == 3)
            throw new InvalidValueException("Please input Date in the following format: <dd/mm/yyyy>");
        taskList.addTask(new Event(taskList.getDescription(command), taskList.getMoreDetails(command)));
        return ui.acknowledgeAddition(taskList.getList());
    }

    /**
     * Execute "list" command.
     *
     * @return List in form of String.
     */
    public String parseList() {
        return ui.printList(taskList);
    }

    /**
     * Reetrieve index in command as integer.
     *
     * @param command Input from user.
     * @return Extracted Integer.
     */
    private static int getIndex(String command) {
        String index = command.substring(command.indexOf(" ") + 1);
        return Integer.parseInt(index.trim());
    }

    /**
     * Checks if input is numeric.
     *
     * @param validate Input that requires validation.
     * @return true for input that is numeric and false for non numeric.
     */
    private boolean isNumeric(String validate) {
        try {
            Integer.parseInt(validate);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Throw all possible Exceptions that might crash the programme.
     *
     * @param command Input from user.
     * @throws InvalidValueException if user input is in the wrong format.
     */
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

    /**
     * Execute "find" command.
     *
     * @param command Input from user.
     * @return List of all items found with keyword.
     */
    public String parseFind(String command) {
        if (taskList.getList().size() == 0)
            return ui.printList(taskList);
        String keyword = TaskList.getItem(command);
        return ui.findResults(keyword, taskList);
    }
}