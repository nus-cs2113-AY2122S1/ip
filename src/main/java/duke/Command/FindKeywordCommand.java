package duke.Command;

import duke.ErrorHandling.CommandException;
import duke.ErrorHandling.ErrorStaticString;
import duke.TaskList.TaskList;

/**
 * Represent a command that filter out tasks containing a word and print to user
 */
public class FindKeywordCommand extends Command{

    private final TaskList taskList;

    /**
     * Constructor for FindKeywordCommand
     *
     * @param taskInput Command input by user to process
     * @param taskList Tasklist to interact with list of task
     */
    public FindKeywordCommand(String taskInput, TaskList taskList){
        super(taskInput);
        this.taskList = taskList;
    }

    /**
     * Filter out the word to search and pass the searching of the word to TaskList
     *
     * @throws CommandException if list is empty or if word to search is empty
     */
    @Override
    public void executeCommand() throws CommandException{
        if(taskList.getListSize() == 0){
            throw new CommandException(ErrorStaticString.ERROR_EMPTY_LIST);
        }
        taskInput = taskInput.replaceFirst(COMMAND_FIND_WORD,EMPTY_STRING).trim();
        if(taskInput.isEmpty()){
            throw new CommandException(ErrorStaticString.ERROR_EMPTY_WORD_INPUT);
        }
        taskList.printWord(taskInput);
    }
}
