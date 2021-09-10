package exception;

public class TooManyTasksException extends Exception{
    public TooManyTasksException(){
        super("Too many tasks in program!");
    }
}
