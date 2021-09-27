package duke.Command;

import duke.ErrorHandling.CommandException;
import duke.ErrorHandling.ErrorStaticString;
import duke.TaskList.TaskList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindDateCommand extends Command{

    private final TaskList taskList;

    public FindDateCommand(String taskInput, TaskList taskList){
        super(taskInput);
        this.taskList = taskList;
    }

    @Override
    public void executeCommand() throws CommandException {
        if(taskList.getListSize() == 0){
            throw new CommandException(ErrorStaticString.ERROR_EMPTY_LIST);
        }
        taskInput = taskInput.replaceFirst(COMMAND_DATE_TASK,EMPTY_STRING).trim();
        if(taskInput.isEmpty()){
            throw new CommandException(ErrorStaticString.ERROR_EMPTY_DATE_SEARCH_TASK);
        }
        try {
            LocalDate localDate = LocalDate.parse(convertStringToDate());
            taskList.printDate(localDate);
        }catch (DateTimeParseException e ){
            System.out.println(ErrorStaticString.ERROR_DATE_SEARCH);
        }
    }

    private Matcher findDate(String stringToSearch){
        Pattern pattern = Pattern.compile(DATE_PATTERN);
        Matcher matcher = pattern.matcher(stringToSearch);
        if(matcher.find()){
            return matcher;
        }
        return null;
    }

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
