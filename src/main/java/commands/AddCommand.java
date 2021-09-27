package commands;

import duke.DefaultException;
import task.*;

/**
 * A Command class that contains methods for adding tasks to the ArrayList.
 */
public class AddCommand extends Command{

    private static final int EXPECTED_LENGTH_FOR_EVENT_AND_DEADLINE = 2;
    private static final String PROMPT_CORRECT_FORMAT = "Sorry, you're missing some arguments," +
            " do type 'help' if you're unsure :)";
    public static final String ADDED_TO_LIST = "I've added this to your list :D";
    private final String[] parsedOutput;
    private final TaskType type;

    public AddCommand(String[] parsedOutput, TaskType type) {
        this.parsedOutput = parsedOutput;
        this.type = type;
    }

    /**
     * Creates a task object and adds it to the ArrayList depending on the type of task.
     *
     * @return A CommandResult that tells the Ui to print the status of the execution
     * and the task added.
     * @throws DefaultException Throws a DefaultException if something unexpected happens.
     */
    @Override
    public CommandResult execute() throws  DefaultException {
        switch(type){
        case TODO:
            tasks.addTask(new ToDo(parsedOutput[0]));
            return new CommandResult(ADDED_TO_LIST,tasks.getTask(Task.getTotalTasks()-1),
                    PrintOptions.WITH_TASK_AND_NUMBER_OF_TASK);
        case EVENT:
            if(parsedOutput.length < EXPECTED_LENGTH_FOR_EVENT_AND_DEADLINE) {
                return new CommandResult(PROMPT_CORRECT_FORMAT,PrintOptions.DEFAULT);
            }
            tasks.addTask(new Event(parsedOutput[0], parsedOutput[1]));
            return new CommandResult(ADDED_TO_LIST,tasks.getTask(Task.getTotalTasks()-1),
                    PrintOptions.WITH_TASK_AND_NUMBER_OF_TASK);
        case DEADLINE:
            if(parsedOutput.length < EXPECTED_LENGTH_FOR_EVENT_AND_DEADLINE) {
                return new CommandResult(PROMPT_CORRECT_FORMAT,PrintOptions.DEFAULT);
            }
            tasks.addTask(new Deadline(parsedOutput[0], parsedOutput[1]));
            return new CommandResult(ADDED_TO_LIST,tasks.getTask(Task.getTotalTasks()-1),
                    PrintOptions.WITH_TASK_AND_NUMBER_OF_TASK);
        default :
            throw new DefaultException();
        }
    }
}
