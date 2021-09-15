package duke.exceptions;

public class IncompleteInformationException extends DukeException{
    private String taskType;
    private String field;

    public IncompleteInformationException(String taskType, String description) {
        this.taskType = taskType;
        this.field = description;
    }

    @Override
    public String getMessage(){
        return String.format("☹ OOPS!!! The %s field of the %s cannot be empty",field,taskType);
    }
}
