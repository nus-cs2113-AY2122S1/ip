public abstract class DukeException extends Exception{
    protected String errorMessage;

    public String getErrorMessage() {
        return this.errorMessage;
    }
}
