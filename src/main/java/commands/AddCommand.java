package commands;

import duke.DefaultException;
import duke.DukeException;
import task.*;

public class AddCommand extends Command{

    private static final int EXPECTED_LENGTH_FOR_EVENT_AND_DEADLINE = 2;
    public static final String ADDED_TO_LIST = "I've added this to your list :D";
    private final String[] parsedOutput;
    private final TaskType type;

    public AddCommand(String[] parsedOutput, TaskType type) {
        this.parsedOutput = parsedOutput;
        this.type = type;
    }

    public CommandResult execute()
            throws StringIndexOutOfBoundsException, DukeException, DefaultException {
        switch(type){
        case TODO:
            tasks.addTask(new ToDo(parsedOutput[0]));
            return new CommandResult(ADDED_TO_LIST,tasks,PrintOptions.WITH_TASK_AND_NUMBER_OF_TASK);
        case EVENT:
            if(parsedOutput.length < EXPECTED_LENGTH_FOR_EVENT_AND_DEADLINE) {
                throw new DukeException();
            }
            tasks.addTask(new Event(parsedOutput[0], parsedOutput[1]));
            return new CommandResult(ADDED_TO_LIST,tasks,PrintOptions.WITH_TASK_AND_NUMBER_OF_TASK);
        case DEADLINE:
            if(parsedOutput.length < EXPECTED_LENGTH_FOR_EVENT_AND_DEADLINE) {
                throw new DukeException();
            }
            tasks.addTask(new Deadline(parsedOutput[0], parsedOutput[1]));
            return new CommandResult(ADDED_TO_LIST,tasks.getTask(Task.getTotalTasks()-1),
                    PrintOptions.WITH_TASK_AND_NUMBER_OF_TASK);
        default :
            throw new DefaultException();
        }
    }
}
