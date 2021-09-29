package commands;

import static constants.Message.INDENTATION;
import duke.DefaultException;
import task.Task;
import task.Event;
import task.Deadline;
import task.ToDo;
import task.TaskType;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * A Command class that contains methods for adding tasks to the ArrayList.
 */
public class AddCommand extends Command{

    private static final int EXPECTED_LENGTH_FOR_DEADLINE = 2;
    private static final int EXPECTED_LENGTH_FOR_EVENT = 3;
    public static final String DEADLINE_FORMAT_ERROR = "Please type the deadline in the format : \n" +
            INDENTATION + "'deadline (description) /by yyyy-MM-ddThh:mm' :)";
    public static final String EVENT_FORMAT_ERROR = "Please type the event in the format: \n" +
            INDENTATION + "'event (description) /at yyyy-MM-ddThh:mm /to yyyy-MM-ddThh:mm' :)";
    private static final String DATE_TIME_FORMAT_ERROR_FOR_DEADLINE = "Please write the date and time in the" +
            " format : 'yyyy-mm-ddThh:mm'  :)";
    private static final String DATE_TIME_FORMAT_ERROR_FOR_EVENT = "Please write the date and time in the format : \n"
            + INDENTATION + "'yyyy-mm-ddThh:mm /to yyy-mm-ddThh:mm'  :)";
    public static final String ADDED_TO_LIST = "I've added this to your list :D";
    public static final String TODO_COMMAND = "todo";
    public static final String EVENT_COMMAND = "event";
    public static final String DEADLINE_COMMAND = "deadline";

    private final String[] parsedOutputs;
    private final TaskType type;

    public AddCommand(String[] parsedOutputs, TaskType type) {
        this.parsedOutputs = parsedOutputs;
        this.type = type;
    }

    /**
     * Creates a task object and adds it to the ArrayList depending on the type of task.
     *
     * @return A CommandResult that tells the Ui to print the status of the execution
     * and the task added (if successful).
     * @throws DefaultException Throws an exception if something unexpected happens.
     */
    @Override
    public CommandResult execute() throws  DefaultException {
        switch(type){
        case TODO:
            tasks.addTask(new ToDo(parsedOutputs[0]));
            return new CommandResult(ADDED_TO_LIST,tasks.getTask(Task.getTotalTasks()-1),
                    PrintOptions.WITH_TASK_AND_NUMBER_OF_TASK);
        case EVENT:
            return getCommandResultForEvent();
        case DEADLINE:
            return getCommandResultForDeadline();
        default :
            throw new DefaultException();
        }
    }

    /**
     * Tries to add a deadline to the ArrayList.
     *
     * @return A CommandResult that tells the Ui to print the status of the execution
     * and the task added (if successful).
     */
    private CommandResult getCommandResultForDeadline() {
        if(parsedOutputs.length < EXPECTED_LENGTH_FOR_DEADLINE) {
            return new CommandResult(DEADLINE_FORMAT_ERROR,PrintOptions.DEFAULT);
        }
        try {
            tasks.addTask(new Deadline(parsedOutputs[0], LocalDateTime.parse(parsedOutputs[1])));
            return new CommandResult(ADDED_TO_LIST,tasks.getTask(Task.getTotalTasks()-1),
                    PrintOptions.WITH_TASK_AND_NUMBER_OF_TASK);
        } catch (DateTimeParseException error) {
            return new CommandResult(DATE_TIME_FORMAT_ERROR_FOR_DEADLINE,PrintOptions.DEFAULT);
        }
    }

    /**
     * Tries to add an event to the ArrayList.
     *
     * @return A CommandResult that tells the Ui to print the status of the execution
     * and the task added (if successful).
     */
    private CommandResult getCommandResultForEvent() {
        if(parsedOutputs.length < EXPECTED_LENGTH_FOR_EVENT) {
            return new CommandResult(EVENT_FORMAT_ERROR,PrintOptions.DEFAULT);
        }
        try {
            tasks.addTask(new Event(parsedOutputs[0], LocalDateTime.parse(parsedOutputs[1]),
                    LocalDateTime.parse(parsedOutputs[2])));
            return new CommandResult(ADDED_TO_LIST,tasks.getTask(Task.getTotalTasks()-1),
                    PrintOptions.WITH_TASK_AND_NUMBER_OF_TASK);
        } catch (DateTimeParseException error) {
            return new CommandResult(DATE_TIME_FORMAT_ERROR_FOR_EVENT,PrintOptions.DEFAULT);
        }
    }


}
