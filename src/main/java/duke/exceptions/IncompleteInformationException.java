package duke.exceptions;

public class IncompleteInformationException extends DukeException{
    private String taskType;

    public IncompleteInformationException(String taskType) {
        this.taskType = taskType;
    }

    @Override
    public String getMessage(){
        //check task type and print the correct msg
        return String.format("☹ OOPS!!! The check that the description and time fields of %s is not empty", taskType);
    }
}
