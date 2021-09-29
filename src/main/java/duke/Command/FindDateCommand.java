package duke.Command;

import duke.ErrorHandling.CommandException;
import duke.ErrorHandling.ErrorStaticString;
import duke.TaskList.TaskList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represent a command that filter out task with a specific date and print to user
 */
public class FindDateCommand extends Command{

    private final TaskList taskList;

    /**
     * Constructor for FindDateCommand
     *
     * @param taskInput Command input by user to process
     * @param taskList Tasklist to interact with list of task
     */
    public FindDateCommand(String taskInput, TaskList taskList){
        super(taskInput);
        this.taskList = taskList;
    }

    /**
     * Filter out date to search and convert to LocalDate object
     * Pass searching of date to TaskList
     *
     * @throws CommandException if list is empty or if date to search is empty
     */
    @Override
    public void executeCommand() throws CommandException {
        if(taskList.getListSize() == 0){
            throw new CommandException(ErrorStaticString.ERROR_EMPTY_LIST);
        }
        taskInput = taskInput.replaceFirst(COMMAND_DATE_TASK,EMPTY_STRING).trim();
        if(taskInput.isEmpty()){
            throw new CommandException(ErrorStaticString.ERROR_EMPTY_DATE_INPUT);
        }
        try {
            LocalDate localDate = LocalDate.parse(convertStringToDate());
            taskList.printDate(localDate);
        }catch (DateTimeParseException e ){
            System.out.println(ErrorStaticString.ERROR_DATE_TIME_PARSE);
        }
    }

    /**
     * Find first instance of a date by finding a string in a pattern:4int-2int-2int
     *
     * @param stringToSearch String that contain date
     * @return Null if no date is found or return a matcher object that found date
     */
    private Matcher findDate(String stringToSearch){
        Pattern pattern = Pattern.compile(DATE_PATTERN);
        Matcher matcher = pattern.matcher(stringToSearch);
        if(matcher.find()){
            return matcher;
        }
        return null;
    }

    /**
     * Extract string containing date
     *
     * @return string containing date or Empty String if no date found
     */
    private String convertStringToDate(){
        Matcher dateMatcher = findDate(taskInput);
        if(dateMatcher != null){
            int startIndexOfDate = dateMatcher.start();
            int endIndexOfDate = dateMatcher.end();
            return taskInput.substring(startIndexOfDate,endIndexOfDate);
        }
        return EMPTY_STRING;
    }
}
