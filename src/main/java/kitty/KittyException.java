package kitty;

public class KittyException extends Exception{
    public KittyException(String exception) {
        super(exception);
        System.out.println("Error: " + exception);
    }
}
