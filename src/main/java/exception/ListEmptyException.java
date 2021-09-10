package exception;

public class ListEmptyException extends Exception{
    public ListEmptyException(){
        super("List is empty!");
    }
}
