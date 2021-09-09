package FridayExceptions;

public class MissingDateException extends Exception{
    private String type;

    public MissingDateException(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
