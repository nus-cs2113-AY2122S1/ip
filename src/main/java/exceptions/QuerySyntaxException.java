package exceptions;

public class QuerySyntaxException extends CommandSyntaxException {

    public QuerySyntaxException(String message) {
        super("QuerySyntaxException: " + message);
    }
}
