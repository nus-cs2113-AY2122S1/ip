public class CommandException extends Exception{

    private String errorMessage;

    public CommandException(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public void handleException(){
        System.out.println(errorMessage);
    }
}
