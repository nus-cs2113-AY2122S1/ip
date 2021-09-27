package commands;

import constants.Message;
import duke.DefaultException;
import task.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command{

    private static final int EXPECTED_LENGTH_FOR_DEADLINE = 2;
    private static final int EXPECTED_LENGTH_FOR_EVENT = 3;
    private static final String PROMPT_CORRECT_FORMAT = "Sorry, you're missing some arguments," +
            " do type 'help' if you're unsure :)";
    private static final String DATE_TIME_FORMAT_ERROR_FOR_DEADLINE = "Please write the date and time in the" +
            " format : 'yyyy-mm-ddThh:mm:ss'  :)";
    private static final String DATE_TIME_FORMAT_ERROR_FOR_EVENT = "Please write the date and time in the format : \n" +
            Message.INDENTATION + "'yyyy-mm-ddThh:mm:ss /to yyy-mm-ddThh:mm'  :)";
    public static final String ADDED_TO_LIST = "I've added this to your list :D";
    public static final String TODO_COMMAND = "todo";
    public static final String EVENT_COMMAND = "event";
    public static final String DEADLINE_COMMAND = "deadline";

    private final String[] parsedOutput;
    private final TaskType type;

    public AddCommand(String[] parsedOutput, TaskType type) {
        this.parsedOutput = parsedOutput;
        this.type = type;
    }

    public CommandResult execute() throws DefaultException, DateTimeParseException {
        switch(type){
        case TODO:
            tasks.addTask(new ToDo(parsedOutput[0]));
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

    private CommandResult getCommandResultForDeadline() {
        if(parsedOutput.length < EXPECTED_LENGTH_FOR_DEADLINE) {
            return new CommandResult(PROMPT_CORRECT_FORMAT,PrintOptions.DEFAULT);
        }
        try {
            tasks.addTask(new Deadline(parsedOutput[0], LocalDateTime.parse(parsedOutput[1])));

        } catch (DateTimeParseException error) {
            return new CommandResult(DATE_TIME_FORMAT_ERROR_FOR_DEADLINE,PrintOptions.DEFAULT);

        }
        return new CommandResult(ADDED_TO_LIST,tasks.getTask(Task.getTotalTasks()-1),
                PrintOptions.WITH_TASK_AND_NUMBER_OF_TASK);
    }

    private CommandResult getCommandResultForEvent() {
        if(parsedOutput.length < EXPECTED_LENGTH_FOR_EVENT) {
            return new CommandResult(PROMPT_CORRECT_FORMAT,PrintOptions.DEFAULT);
        }
        try {
            tasks.addTask(new Event(parsedOutput[0], LocalDateTime.parse(parsedOutput[1]),
                    LocalDateTime.parse(parsedOutput[2])));

        } catch (DateTimeParseException error) {
            return new CommandResult(DATE_TIME_FORMAT_ERROR_FOR_EVENT,PrintOptions.DEFAULT);

        }
        return new CommandResult(ADDED_TO_LIST,tasks.getTask(Task.getTotalTasks()-1),
                PrintOptions.WITH_TASK_AND_NUMBER_OF_TASK);
    }


}
