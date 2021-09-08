package handleException;

public class DukeEmptyDescriptionException extends Exception{
    private String typeOfTask;

    public String getTypeOfTask() {
        return typeOfTask;
    }

    public DukeEmptyDescriptionException(String typeOfTask) {
        this.typeOfTask = typeOfTask;
    }
}
