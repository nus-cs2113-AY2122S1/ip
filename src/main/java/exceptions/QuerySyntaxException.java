package exceptions;

public class QuerySyntaxException extends CommandSyntaxException {

    /**
     * Exception thrown when the command 'find' is called but the query is invalid
     * E.g. 'find /type completed' is not valid because 'completed' is not a valid Task Type
     * @param message Additional message to be displayed when exception is thrown
     */
    public QuerySyntaxException(String message) {
        super("QuerySyntaxException: " + message);
    }
}
