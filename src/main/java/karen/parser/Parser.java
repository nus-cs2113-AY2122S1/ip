package karen.parser;

import karen.exception.ErrorCheck;
import karen.exception.IncorrectDescriptionFormatException;
import karen.exception.NoDescriptionException;
import karen.command.Command;
import karen.program.ProgramManager;
import karen.tasklist.TaskList;
import karen.tasklist.task.Deadline;
import karen.tasklist.task.Event;
import karen.tasklist.task.Task;
import karen.tasklist.task.ToDo;
import karen.ui.Ui;

import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Parser {
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";
    private static final String BYE_COMMAND = "bye";
    private static final String DEADLINE_SEPARATOR = " /by ";
    private static final String EVENT_SEPARATOR = " /at ";
    private static final String TASK_DONE_STATUS = "X";

    private static TaskList taskList;
    private static ProgramManager programManager;

    public Parser(TaskList taskList, ProgramManager programManager) {
        this.taskList = taskList;
        this.programManager = programManager;
    }

    /**
     * Returns an Event object to be added into the TaskList.
     *
     * This method takes in the raw user input as a parameter and then parses
     * it to obtain the description of the Event task to be done, as well as
     * the date to be done. An event object is then constructed using the parsed
     * information and returned.
     *
     * @param rawUserInput raw input given by the user that is trimmed with no leading spaces
     * @return the Event object constructed from the user inputs
     * @throws NoDescriptionException if an Event command is given without any task description
     * @throws IncorrectDescriptionFormatException if an Event command is given with incorrect formatting
     */
    public Event parseEvent(String rawUserInput) throws NoDescriptionException, IncorrectDescriptionFormatException {
        ErrorCheck.checkTaskExceptions(EVENT_COMMAND, EVENT_SEPARATOR, rawUserInput);

        String fullTaskDescription = parseFullTaskDescription(rawUserInput);

        String[] descriptionWords = fullTaskDescription.split(EVENT_SEPARATOR, 2);
        String at = descriptionWords[1].trim();

        int endIndex = fullTaskDescription.indexOf(EVENT_SEPARATOR);
        String eventTaskDescription = fullTaskDescription.substring(0, endIndex);

        Event event = new Event(eventTaskDescription, at);
        return event;
    }

    /**
     * Returns a Deadline object to be added into the TaskList.
     *
     * This method takes in the raw user input as a parameter and then parses
     * it to obtain the description of the Deadline task to be done, as well as
     * the date to be done. A Deadline object is then constructed using the parsed
     * information and returned.
     *
     * @param rawUserInput raw input given by the user that is trimmed with no leading spaces
     * @return the Deadline object constructed from the user inputs
     * @throws NoDescriptionException if a Deadline command is given without any task description
     * @throws IncorrectDescriptionFormatException if a Deadline command is given with incorrect formatting
     */
    public Deadline parseDeadline(String rawUserInput) throws NoDescriptionException, IncorrectDescriptionFormatException {
        ErrorCheck.checkTaskExceptions(DEADLINE_COMMAND, DEADLINE_SEPARATOR, rawUserInput);

        String fullTaskDescription = parseFullTaskDescription(rawUserInput);

        String[] descriptionWords = fullTaskDescription.split(DEADLINE_SEPARATOR, 2);
        String by = descriptionWords[1].trim();

        int endIndex = fullTaskDescription.indexOf(DEADLINE_SEPARATOR);
        String deadlineTaskDescription = fullTaskDescription.substring(0, endIndex);

        Deadline deadline = new Deadline(deadlineTaskDescription, by);
        return deadline;
    }

    /**
     * Returns a ToDo object to be added into the TaskList.
     *
     * This method takes in the raw user input as a parameter and then parses
     * it to obtain the ToDo task description. A ToDo object is then constructed
     * using the task description and returned.
     *
     * @param rawUserInput raw input given by the user that is trimmed with no leading spaces
     * @return the Deadline object constructed from the user inputs
     * @throws NoDescriptionException if a ToDo command is given without any task description
     * @throws IncorrectDescriptionFormatException if a ToDo command is given with incorrect formatting
     */
    public ToDo parseToDo(String rawUserInput) throws NoDescriptionException, IncorrectDescriptionFormatException {
        ErrorCheck.checkTaskExceptions(TODO_COMMAND, "", rawUserInput);
        String todoTaskDescription = parseFullTaskDescription(rawUserInput);
        ToDo todo = new ToDo(todoTaskDescription);
        return todo;
    }

    /**
     * Returns the index of the task in TaskList that is to be marked as done.
     *
     * This method takes in the raw user input as a parameter and then parses
     * it to obtain the index of the task in the TaskList to be marked as done.
     *
     * @param rawUserInput raw input given by the user that is trimmed with no leading spaces
     * @return index of the task in the TaskList to be marked as done
     * @throws NoDescriptionException if a Done Command is given without any task number
     * @throws IncorrectDescriptionFormatException if a Done Command is given with incorrect formatting
     */
    public int parseDone(String rawUserInput) throws NoDescriptionException, IncorrectDescriptionFormatException {
        ErrorCheck.checkCommandDescriptionExceptions("done", rawUserInput);
        String[] inputWords = rawUserInput.split(" ");
        int doneIndex = Integer.parseInt(inputWords[1]) - 1;
        return doneIndex;
    }

    /**
     * Returns the index of the task that is to be removed from TaskList.
     *
     * This method takes in the raw user input as a parameter and then parses
     * it to obtain the index of the task in the TaskList to be removed.
     *
     * @param rawUserInput raw input given by the user that is trimmed with no leading spaces
     * @return index of the task in the TaskList to be removed
     * @throws NoDescriptionException if a Delete Command is given without any task number
     * @throws IncorrectDescriptionFormatException if a Delete Command is given with incorrect formatting
     */
    public int parseDelete(String rawUserInput) throws NoDescriptionException, IncorrectDescriptionFormatException {
        ErrorCheck.checkCommandDescriptionExceptions("delete", rawUserInput);
        String[] inputWords = rawUserInput.split(" ");
        //find the index of the task to delete and the task itself
        int deleteIndex = Integer.parseInt(inputWords[1]) - 1;
        return deleteIndex;
    }

    /**
     * This method takes in the raw user input as a parameter and then parses
     * it to check if it is a valid Bye Command.
     *
     * @param rawUserInput raw input given by the user that is trimmed with no leading spaces
     * @throws NoDescriptionException if a Bye Command is given with incorrect formatting
     * @throws IncorrectDescriptionFormatException if a Bye Command is given with incorrect formatting
     */
    public void parseBye(String rawUserInput) throws NoDescriptionException, IncorrectDescriptionFormatException {
        ErrorCheck.checkCommandDescriptionExceptions("bye", rawUserInput);
    }

    /**
     * Returns the type of input command given by the user as a String
     *
     * This method takes in the raw user input as a parameter and then parses
     * it to obtain the type of command given by the user to be executed in
     * the program as a String.
     *
     * @param rawUserInput raw input given by the user that is trimmed with no leading spaces
     * @return the input command type given by user as a String.
     */
    public String parseCommand(String rawUserInput) {
        String[] inputWords = rawUserInput.toLowerCase().split(" ");
        String inputCommand = inputWords[0];
        return inputCommand;
    }

    /**
     * Returns the full task description of the Task command given by the user
     *
     * This method takes in the raw user input as a parameter when one of the
     * Task commands has been given by user. The raw user input is then parsed
     * to obtain the full description of the task. If it is a ToDo task, the
     * full description is just the task description. If it is a
     * Deadline or Event task, the full description includes both the task
     * description as well as the date to be done.
     *
     * @param rawUserInput raw input given by the user that is trimmed with no leading spaces
     * @return full description of the task as a String
     */
    public String parseFullTaskDescription(String rawUserInput) {
        int startIndex = rawUserInput.indexOf(" ") + 1;
        String FullTaskDescription = rawUserInput.substring(startIndex);
        return FullTaskDescription;
    }

    /**
     * This method parses the raw user input to find out the input command given by user.
     * The input command is then executed using the Command Object. This method also catches
     * any exceptions thrown during the parsing and execution of commands, and prints their
     * respective error messages.
     *
     * @param rawUserInput raw input given by the user that is trimmed with no leading spaces
     */
    public void parseInput(String rawUserInput) {
        String inputCommand = parseCommand(rawUserInput);
        Command command = new Command(inputCommand, this.programManager, taskList);
        try {
            switch (inputCommand) {
            case LIST_COMMAND:
                command.executeListCommand();
                break;
            case DONE_COMMAND:
                int doneIndex = parseDone(rawUserInput);
                command.executeDoneCommand(doneIndex);
                break;
            case TODO_COMMAND:
                ToDo todo = parseToDo(rawUserInput);
                command.executeToDoCommand(todo);
                break;
            case DEADLINE_COMMAND:
                Deadline deadline = parseDeadline(rawUserInput);
                command.executeDeadlineCommand(deadline);
                break;
            case EVENT_COMMAND:
                Event event = parseEvent(rawUserInput);
                command.executeEventCommand(event);
                break;
            case DELETE_COMMAND:
                int deleteIndex = parseDelete(rawUserInput);
                command.executeDeleteCommand(deleteIndex);
                break;
            case BYE_COMMAND:
                parseBye(rawUserInput);
                command.executeByeCommand();
                break;
            default:
                Ui.printInvalidCommandMessage();
                break;
            }
        } catch (NoDescriptionException e) {
            Ui.printNoDescriptionMessage();
        } catch (IncorrectDescriptionFormatException e) {
            Ui.printIncorrectDescriptionFormatMessage();
        } catch (NumberFormatException e) {
            Ui.printNumberFormatMessage();
        } catch (IndexOutOfBoundsException e) {
            Ui.printIndexOutOfBoundsMessage();
        } catch (IOException e) {
            Ui.printIOExceptionMessage();
        }
    }

    /**
     * Returns a Task object in the form of either a Deadline, an Event or a ToDo.
     *
     * This method takes in data from the storage file as a String and then parses it
     * to obtain the task type, the task status, the task description, as well as the
     * task date if any. The Task object is then constructed accordingly with respect
     * to the task type and parsed information, which will then be returned.
     *
     * @param fileData a String containing information about a task in the storage file
     * @return Task object in the form of either a Deadline, an Event or a ToDo.
     */
    public static Task parseFileData(String fileData) {
        String splitData[] = fileData.split(",");

        String taskType = splitData[0].toLowerCase();
        String taskStatus = splitData[1];
        String taskDescription = splitData[2].trim();

        Task task = new Task(taskDescription);

        switch (taskType) {
        case TODO_COMMAND:
            task = new ToDo(taskDescription);
            break;
        case DEADLINE_COMMAND:
            String by = splitData[3].trim();
//            fullTaskDescription = String.format("%s /by %s", taskDescription, by);
            task = new Deadline(taskDescription, by);
            break;
        case EVENT_COMMAND:
            String at = splitData[3].trim();
//            fullTaskDescription = String.format("%s /at %s", taskDescription, at);
            task = new Event(taskDescription, at);
            break;
        default:
            Ui.printIOExceptionMessage();
            break;
        }
        if (taskStatus.equals(TASK_DONE_STATUS)) {
            task.markAsDone();
        }
        return task;
    }
}
