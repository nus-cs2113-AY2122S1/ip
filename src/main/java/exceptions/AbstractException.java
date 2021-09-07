package exceptions;

abstract class AbstractException extends Exception {
    protected String statement;

    public AbstractException(String description) {
        statement = description;
    }

    public void printStatement() {
        System.out.println(statement);
    }
}
