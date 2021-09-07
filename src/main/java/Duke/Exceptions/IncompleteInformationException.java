package Duke.Exceptions;

public class IncompleteInformationException extends DukeException{
    private String taskType;
    private String description;

    public IncompleteInformationException(String taskType, String description) {
        this.taskType = taskType;
        this.description = description;
    }

    @Override
    public String getMessage(){
        return String.format("☹ OOPS!!! The %s of the %s cannot be empty",description,taskType);
    }
}
