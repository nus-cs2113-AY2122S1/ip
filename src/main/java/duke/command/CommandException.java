package duke.command;

public class CommandException extends Exception{
    private String errorMessage;

    public CommandException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString(){
        return errorMessage;
    }
}
